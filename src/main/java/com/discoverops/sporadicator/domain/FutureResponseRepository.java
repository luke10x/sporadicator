package com.discoverops.sporadicator.domain;

import com.discoverops.sporadicator.domain.response.FutureResponse;
import com.discoverops.sporadicator.domain.response.Response;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;

@Scope(value = "singleton")
@Component
public class FutureResponseRepository {

    private Map<UUID, FutureResponse> futureResponseMap;

    public FutureResponseRepository() {
        futureResponseMap = new HashMap<>();
    }

    public synchronized void save(FutureResponse futureResponse)
    {
        futureResponseMap.put(futureResponse.getUUID(), futureResponse);
    }

    public synchronized Future<Response> get(UUID id) {
        return futureResponseMap.get(id);
    }
}
