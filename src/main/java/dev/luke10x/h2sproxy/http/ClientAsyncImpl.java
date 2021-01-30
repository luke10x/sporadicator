package dev.luke10x.h2sproxy.http;

import dev.luke10x.h2sproxy.domain.response.FutureResponse;

import dev.luke10x.h2sproxy.domain.AsyncClient;
import dev.luke10x.h2sproxy.domain.request.Request;
import dev.luke10x.h2sproxy.domain.response.Response;

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

        HttpFutureResponse futureResponse = new HttpFutureResponse(() -> {
            HttpResponse httpResponse = httpSyncClient.execute(request);
            return new Response(httpResponse.getEntity().getContent());
        });

        threadPool.execute(futureResponse);

        return futureResponse;
    }
}