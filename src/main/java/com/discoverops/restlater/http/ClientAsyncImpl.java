package com.discoverops.restlater.http;

import com.discoverops.restlater.domain.FutureResponse;

import com.discoverops.restlater.domain.AsyncClient;
import com.discoverops.restlater.domain.Request;
import com.discoverops.restlater.domain.Response;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("httpAsyncClient")
public class ClientAsyncImpl implements AsyncClient {

    @Autowired
    HttpClient httpClient;

    @Autowired
    ThreadPool threadPool;

    @Override
    public FutureResponse executeAsync(Request request) {

        FutureResponse futureResponse = new FutureResponse(() -> {
            HttpResponse httpResponse = httpClient.execute(request);
            return new Response(httpResponse.getEntity().getContent());
        });

        threadPool.execute(futureResponse);

        return futureResponse;
    }
}