package com.discoverops.restlater.domain;

import org.apache.http.client.methods.HttpUriRequest;

public class Request {
    private final HttpUriRequest httpRequest;

    public Request(HttpUriRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    @Deprecated
    public HttpUriRequest getHttpRequest() {
        return httpRequest;
    }
}
