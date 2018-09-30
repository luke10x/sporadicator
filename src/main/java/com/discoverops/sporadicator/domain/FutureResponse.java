package com.discoverops.sporadicator.domain;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureResponse extends FutureTask<Response> {
    private final UUID uuid;

    public FutureResponse(Callable<Response> callable) {
        super(callable);
        uuid = UUID.randomUUID();
    }

    public UUID getUUID() {
        return uuid;
    }
}
