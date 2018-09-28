package com.discoverops.restlater.rest.contract;

import java.util.UUID;

public class ConnectionAccepted {
    private final UUID connectionUUID;

    public ConnectionAccepted(UUID connectionUUID) {
        this.connectionUUID = connectionUUID;
    }

    public String getStatus() {
        return "Your query is now being processed";
    }
    public UUID getConnectionUUID() {
        return connectionUUID;
    }
}
