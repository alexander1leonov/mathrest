package org.alexander1leonov.mathrest.controller;

import org.alexander1leonov.mathrest.controller.client.waittime.WaitTimeRestClient;
import org.alexander1leonov.mathrest.domain.TimeResult;
import org.alexander1leonov.mathrest.domain.WaitTimeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@RestController
@RequestMapping("time")
public class TimeRestController {
    private static final Logger LOG = LoggerFactory.getLogger(TimeRestController.class);

    @Autowired
    private WaitTimeRestClient waitTimeRestClient;

    @Value("${time.format}")
    private String timeFormat;
    @Value("${time.defaultTimezone}")
    private String defaultTimezone;
    @Value("${waittime.localTime.format}")
    private String localTimeFormat;

    private DateFormat sourceDateTimeFormat;
    private DateFormat targetTimeFormat;

    @PostConstruct
    public void init() {
        sourceDateTimeFormat = new SimpleDateFormat(localTimeFormat);
        targetTimeFormat = new SimpleDateFormat(timeFormat);
        targetTimeFormat.setTimeZone(TimeZone.getTimeZone(defaultTimezone));
    }

    @RequestMapping(path = "/now", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TimeResult> now() {
        return ResponseEntity.ok(buildTime(getCurrentDate()));
    }

    private TimeResult buildTime(Date date) {
        TimeResult timeResult = new TimeResult();
        timeResult.setTime(targetTimeFormat.format(date));
        LOG.debug("buildTime(): result={}", timeResult.getTime());
        return timeResult;
    }

    private Date getCurrentDate() {
        try {
            Date waitTimeCurrentDate = getWaitTimeCurrentDate();
            if (waitTimeCurrentDate != null) {
                LOG.debug("getCurrentDate(): returning {}", sourceDateTimeFormat.format(waitTimeCurrentDate));
                return waitTimeCurrentDate;
            }
        } catch (ParseException e) {
            LOG.error("getCurrentDate(): {}", e.getLocalizedMessage());
            LOG.debug("getCurrentDate(): {}", e);
        }

        LOG.debug("getCurrentDate(): returning local server datetime");
        return new Date();
    }

    private Date getWaitTimeCurrentDate() throws ParseException {
        WaitTimeResult waitTimeResult = waitTimeRestClient.getCurrent();
        if (waitTimeResult == null) {
            LOG.debug("getWaitTimeCurrentDate(): waitTimeResult is null");
            return null;
        } else {
            String localTimeString = waitTimeResult.getCurrent().getLocalTime();
            LOG.debug("getWaitTimeCurrentDate(): localTimeString={}", localTimeString);
            return sourceDateTimeFormat.parse(localTimeString);
        }
    }
}
