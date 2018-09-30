package com.discoverops.sporadicator.http;

import com.discoverops.sporadicator.domain.Request;
import com.discoverops.sporadicator.http.factory.HttpClientFactory;
import com.discoverops.sporadicator.http.factory.HttpRequestFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpSyncClient {

    @Autowired
    HttpRequestFactory httpRequestFactory;

    @Autowired
    HttpClientFactory httpClientFactory;

    public HttpResponse execute(Request request) throws IOException {
        HttpUriRequest httpRequest = httpRequestFactory.create(request);
        HttpClient httpClient = httpClientFactory.create();
        return httpClient.execute(httpRequest);
    }
}
