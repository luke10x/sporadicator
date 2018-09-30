package com.discoverops.sporadicator.http;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * Request class allowing to pass HTTP method as a constructor variable
 */
public class UniversalUriRequest extends HttpEntityEnclosingRequestBase {

    private final String method;

    public UniversalUriRequest(String method) {
        super();
        this.method = method;
    }

    public UniversalUriRequest(String method, URI uri) {
        super();
        this.method = method;
        this.setURI(uri);
    }

    public UniversalUriRequest(String method, String uri) {
        super();
        this.method = method;
        this.setURI(URI.create(uri));
    }

    public String getMethod() {
        return method;
    }
}
