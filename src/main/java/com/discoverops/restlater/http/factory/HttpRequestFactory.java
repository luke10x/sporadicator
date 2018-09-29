package com.discoverops.restlater.http.factory;

import com.discoverops.restlater.domain.Request;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestFactory {
    public HttpUriRequest create(Request request) {
        HttpUriRequest apacheHttpRequest = request.getHttpRequest();
        return apacheHttpRequest;
    }
}
