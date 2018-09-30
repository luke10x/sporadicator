package com.discoverops.restlater.domain;

import com.discoverops.restlater.domain.request.Header;
import com.discoverops.restlater.http.MyUriRequest;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.List;

public class Request {
    private final String method;
    private final String uri;
    private final HttpUriRequest httpRequest;
    private final List<Header> headers;
    private final byte[] body;

    public Request(String method, String uri, List<Header> headers, byte[] body) {
        this.method = method;
        this.uri = uri;
        this.headers = headers;
        this.body = body;

        httpRequest = new MyUriRequest(method , uri);
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public byte[] getBody() {
        return body;
    }

    @Deprecated
    public HttpUriRequest getHttpRequest() {
        return httpRequest;
    }
}
