package org.alexander1leonov.mathrest.controller;

import org.alexander1leonov.mathrest.domain.TimeResult;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("time")
public class TimeRestController {
    private static final Format TIME_FORMAT = new SimpleDateFormat("HH:mm:ss zZ");

    @RequestMapping(path = "/now", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TimeResult> now() {
        return ResponseEntity.ok(buildTime(new Date()));
    }

    private TimeResult buildTime(Date date) {
        TimeResult timeResult = new TimeResult();
        timeResult.setTime(TIME_FORMAT.format(date));
        return timeResult;
    }
}
