package dev.luke10x.http2sqsproxy.domain;

import dev.luke10x.http2sqsproxy.domain.response.FutureResponse;
import dev.luke10x.http2sqsproxy.domain.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;

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
