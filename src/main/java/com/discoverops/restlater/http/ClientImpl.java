package com.discoverops.restlater.http;

import com.discoverops.restlater.domain.FutureResponse;
import com.discoverops.restlater.http.factory.HttpClientFactory;
import com.discoverops.restlater.http.factory.HttpRequestFactory;

import com.discoverops.restlater.domain.Client;
import com.discoverops.restlater.domain.Request;
import com.discoverops.restlater.domain.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("httpClient")
public class ClientImpl implements Client {

    @Autowired
    HttpRequestFactory httpRequestFactory;

    @Autowired
    HttpClientFactory httpClientFactory;

    @Autowired
    ThreadPool threadPool;

    @Override
    public FutureResponse executeAsync(Request request) {

        HttpUriRequest apacheHttpRequest = httpRequestFactory.create(request);

        HttpClient apacheHttpClient = httpClientFactory.create();

        FutureResponse futureResponse = new FutureResponse(() -> {
            HttpResponse apacheHttpResponse = apacheHttpClient.execute(apacheHttpRequest);
            return new Response(apacheHttpResponse.getEntity().getContent());
        });

        threadPool.execute(futureResponse);

        return futureResponse;
    }
}