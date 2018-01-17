package org.alexander1leonov.mathrest.controller.client.waittime;

import org.alexander1leonov.mathrest.domain.WaitTimeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WaitTimeRestClient {
    private static final Logger LOG = LoggerFactory.getLogger(WaitTimeRestClient.class);

    @Autowired
    WaitTimeResponseErrorHandler waitTimeResponseErrorHandler;

    @Value("${waittime.url}")
    private String url;
    @Value("${waittime.headers.apiKey}")
    private String apiKeyHeader;

    public WaitTimeResult getCurrent() {
        LOG.debug("getCurrent(): invoked");
        HttpEntity<String> entity = new HttpEntity<>("parameters", getHeaders());
        RestTemplate restTemplate = buildRestTemplate();
        ResponseEntity<WaitTimeResult> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, WaitTimeResult.class);
        LOG.debug("getCurrent(): response statusCode={}", responseEntity.getStatusCodeValue());
        if (HttpStatus.OK == responseEntity.getStatusCode()) {
            return responseEntity.getBody();
        } else {
            LOG.error("getCurrent(): unable to retrieve localTime from {}", url);
            LOG.error("getCurrent(): response statusCode={}, response={}", responseEntity.getStatusCodeValue(), responseEntity);
            return null;
        }
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        //TODO: add X-apiKey header when the value is obtained
        return headers;
    }

    private RestTemplate buildRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(waitTimeResponseErrorHandler);
        return restTemplate;
    }

}
