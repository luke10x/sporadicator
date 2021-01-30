package dev.luke10x.h2sproxy.rest.contract;

import java.util.UUID;

public class ConnectionAccepted {
    private final String requestId;

    public ConnectionAccepted(String requestId) {
        this.requestId = requestId;
    }

    public String getStatus() {
        return "Your query is now being processed";
    }
    public String getRequestId() {
        return requestId;
    }
}
