package dev.luke10x.http2sqsproxy.domain.request;


import java.util.List;

public class Request {
    private final String method;
    private final String uri;
    private final List<Header> headers;
    private final byte[] body;

    public Request(String method, String uri, List<Header> headers, byte[] body) {
        this.method = method;
        this.uri = uri;
        this.headers = headers;
        this.body = body;
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
}
