package com.discoverops.restlater.connection;

import org.apache.http.client.methods.HttpRequestBase;

import java.net.URI;

/**
 * Request class allowing to pass HTTP method as a constructor variable
 */
public class MyUriRequest extends HttpRequestBase {

    private final String method;

    public MyUriRequest(String method) {
        super();
        this.method = method;
    }

    public MyUriRequest(String method, URI uri) {
        super();
        this.method = method;
        this.setURI(uri);
    }

    public MyUriRequest(String method, String uri) {
        super();
        this.method = method;
        this.setURI(URI.create(uri));
    }

    public String getMethod() {
        return method;
    }
}
