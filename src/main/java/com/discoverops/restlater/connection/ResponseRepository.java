package com.discoverops.restlater.connection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Scope(value = "singleton")
@Component
public class ResponseRepository {

    private Map<UUID, StringBuffer> responses;

    public ResponseRepository() {
        responses = new HashMap<UUID, StringBuffer>();
    }

    public synchronized void put(UUID id, StringBuffer response)
    {
        responses.put(id, response);
    }

    public synchronized StringBuffer get(UUID id) {
        return responses.get(id);
    }
}
