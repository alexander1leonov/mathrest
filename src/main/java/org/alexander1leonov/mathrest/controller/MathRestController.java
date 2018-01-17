package org.alexander1leonov.mathrest.controller;

import org.alexander1leonov.mathrest.domain.MathResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("math")
public class MathRestController {
    private static final Logger LOG = LoggerFactory.getLogger(MathRestController.class);

    @RequestMapping(path = "/add", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MathResult> getAdd(@RequestParam Double n1, @RequestParam Double n2) {
        return ResponseEntity.ok(buildSum(n1, n2));
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MathResult> postAdd(@RequestParam Double n1, @RequestParam Double n2) {
        return ResponseEntity.ok(buildSum(n1, n2));
    }

    private MathResult buildSum(Double n1, Double n2) {
        MathResult mathResult = new MathResult();
        mathResult.setNumbers(new Double[]{n1, n2});
        mathResult.setSum(n1 + n2);
        LOG.debug("buildSum(): n1={}, n2={}, sum={}", n1, n2, mathResult.getSum());
        return mathResult;
    }

}
