package org.alexander1leonov.mathrest.controller.client.waittime;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class WaitTimeResponseErrorHandler extends DefaultResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) {
        //do nothing
    }
}
