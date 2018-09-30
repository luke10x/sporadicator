package com.discoverops.sporadicator.domain;

public interface AsyncClient {
    public FutureResponse executeAsync(Request request);
}
