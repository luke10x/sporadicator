package com.discoverops.sporadicator.http;

import com.discoverops.sporadicator.domain.response.FutureResponse;

import com.discoverops.sporadicator.domain.AsyncClient;
import com.discoverops.sporadicator.domain.request.Request;
import com.discoverops.sporadicator.domain.response.Response;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("httpAsyncClient")
public class ClientAsyncImpl implements AsyncClient {

    @Autowired
    HttpSyncClient httpSyncClient;

    @Autowired
    ThreadPool threadPool;

    @Override
    public FutureResponse executeAsync(Request request) {

        FutureResponse futureResponse = new FutureResponse(() -> {
            HttpResponse httpResponse = httpSyncClient.execute(request);
            return new Response(httpResponse.getEntity().getContent());
        });

        threadPool.execute(futureResponse);

        return futureResponse;
    }
}