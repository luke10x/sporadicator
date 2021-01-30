package dev.luke10x.h2sproxy.http;

import dev.luke10x.h2sproxy.domain.response.FutureResponse;
import dev.luke10x.h2sproxy.domain.response.Response;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class HttpFutureResponse extends FutureTask<Response> implements FutureResponse {

    private final String requestId;

    public HttpFutureResponse(Callable<Response> callable) {
        super(callable);
        requestId = UUID.randomUUID().toString();
    }

    public String getRequestId() {
        return requestId;
    }
}
