package org.alexander1leonov.mathrest.controller;

import org.alexander1leonov.mathrest.domain.TimeResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@RestController
@RequestMapping("time")
public class TimeRestController {

    @Value("${time.format}")
    private String timeFormat;
    @Value("${time.defaultTimezone}")
    private String defaultTimezone;

    private DateFormat dateFormat;

    @PostConstruct
    public void init() {
        dateFormat = new SimpleDateFormat(timeFormat);
        dateFormat.setTimeZone(TimeZone.getTimeZone(defaultTimezone));
    }

    @RequestMapping(path = "/now", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TimeResult> now() {
        return ResponseEntity.ok(buildTime(getCurrentDate()));
    }

    private TimeResult buildTime(Date date) {
        TimeResult timeResult = new TimeResult();
        timeResult.setTime(dateFormat.format(date));
        return timeResult;
    }

    private Date getCurrentDate() {
        //TODO: implement a call to WaitTime API
        return new Date();
    }
}
