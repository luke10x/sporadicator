package dev.luke10x.http2sqsproxy.domain;

import dev.luke10x.http2sqsproxy.domain.request.Request;
import dev.luke10x.http2sqsproxy.domain.response.FutureResponse;

public interface AsyncClient {
    public FutureResponse executeAsync(Request request);
}
