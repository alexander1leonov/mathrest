package org.alexander1leonov.mathrest.controller;

import org.alexander1leonov.mathrest.controller.client.waittime.WaitTimeRestClient;
import org.alexander1leonov.mathrest.domain.TimeResponse;
import org.alexander1leonov.mathrest.domain.WaitTimeResponse;
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
    public ResponseEntity<TimeResponse> now() {
        return ResponseEntity.ok(buildTime(getCurrentDate()));
    }

    private TimeResponse buildTime(Date date) {
        TimeResponse timeResponse = new TimeResponse();
        timeResponse.setTime(targetTimeFormat.format(date));
        LOG.debug("buildTime(): result={}", timeResponse.getTime());
        return timeResponse;
    }

    private Date getCurrentDate() {
        WaitTimeResponse waitTimeResponse = waitTimeRestClient.getCurrent();
        Date resultDate;

        if (waitTimeResponse == null) {
            LOG.warn("getWaitTimeCurrentDate(): waitTimeResult is null, returning local server datetime");
            resultDate = new Date();
        } else {
            String localTimeString = waitTimeResponse.getCurrent().getLocalTime();
            LOG.debug("getWaitTimeCurrentDate(): localTimeString={}", localTimeString);
            try {
                resultDate = sourceDateTimeFormat.parse(localTimeString);
            } catch (ParseException e) {
                LOG.error("getWaitTimeCurrentDate(): {}", e.getLocalizedMessage());
                LOG.debug("getWaitTimeCurrentDate(): {}", e);
                LOG.warn("getWaitTimeCurrentDate(): unable to parse local date, returning local server datetime");
                resultDate = new Date();
            }
        }
        return resultDate;
    }
}
