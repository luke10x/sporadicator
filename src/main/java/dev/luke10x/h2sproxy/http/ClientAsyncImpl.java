package dev.luke10x.http2sqsproxy.http;

import dev.luke10x.http2sqsproxy.domain.response.FutureResponse;

import dev.luke10x.http2sqsproxy.domain.AsyncClient;
import dev.luke10x.http2sqsproxy.domain.request.Request;
import dev.luke10x.http2sqsproxy.domain.response.Response;

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