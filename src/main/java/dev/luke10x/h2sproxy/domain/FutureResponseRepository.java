package dev.luke10x.h2sproxy.domain;

import dev.luke10x.h2sproxy.domain.response.FutureResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FutureResponseRepository {

    private Map<String, FutureResponse> futureResponseMap;

    public FutureResponseRepository() {
        futureResponseMap = new HashMap<>();
    }

    public synchronized void save(FutureResponse futureResponse)
    {
        futureResponseMap.put(futureResponse.getRequestId(), futureResponse);
    }

    public synchronized FutureResponse get(String id) {
        return futureResponseMap.get(id);
    }
}
