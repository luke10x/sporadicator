package com.discoverops.restlater.http;

import com.discoverops.restlater.domain.Client;
import com.discoverops.restlater.domain.Request;
import com.discoverops.restlater.domain.Response;
import com.discoverops.restlater.share.ThreadPool;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@Component("httpClient")
public class ClientImpl implements Client {

    @Autowired
    ThreadPool threadPool;

    @Override
    public Future<Response> executeAsync(Request request) {

        HttpUriRequest httpRequest = request.getHttpRequest();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        FutureTask<Response> futureResponse = new FutureTask<>(() -> {
            CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);
            return new Response(httpResponse.getEntity().getContent());
        });

        threadPool.execute(futureResponse);

        return futureResponse;
    }
}
