package com.discoverops.restlater.http.factory;

import com.discoverops.restlater.domain.Request;
import com.discoverops.restlater.domain.request.Header;
import com.discoverops.restlater.http.MyUriRequest;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestFactory {
    public HttpUriRequest create(Request request) {
        MyUriRequest httpUriRequest = new MyUriRequest(request.getMethod() , request.getUri());

        httpUriRequest.setEntity(new ByteArrayEntity(request.getBody()));

        for (Header header : request.getHeaders()) {
            if ("content-length".equals(header.getKey().toLowerCase())) continue;
            httpUriRequest.setHeader(header.getKey(), header.getValue());
        }

        return httpUriRequest;
    }
}
