package com.discoverops.restlater.connection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;

@Scope(value = "singleton")
@Component
public class FutureResponseRepository {

    private Map<UUID, Future<Response>> futureResponseMap;

    public FutureResponseRepository() {
        futureResponseMap = new HashMap<UUID, Future<Response>>();
    }

    public synchronized void put(UUID id, Future<Response> futureResponse)
    {
        futureResponseMap.put(id, futureResponse);
    }

    public synchronized Future<Response> get(UUID id) {
        return futureResponseMap.get(id);
    }
}
