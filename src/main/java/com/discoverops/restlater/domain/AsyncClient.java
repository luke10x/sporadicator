package com.discoverops.restlater.domain;

public interface AsyncClient {
    public FutureResponse executeAsync(Request request);
}
