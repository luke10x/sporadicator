package com.discoverops.restlater.domain;

import java.util.concurrent.Future;

public interface Client {

    public Future<Response> executeAsync(Request request);

}
