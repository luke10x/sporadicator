package com.discoverops.restlater.domain;

import com.discoverops.restlater.http.MyUriRequest;
import org.apache.http.client.methods.HttpUriRequest;

public class Request {
    private final HttpUriRequest httpRequest;

    public Request(String method, String uri) {
        httpRequest = new MyUriRequest(method , uri);
    }

    @Deprecated
    public HttpUriRequest getHttpRequest() {
        return httpRequest;
    }
}
