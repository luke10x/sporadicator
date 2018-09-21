package com.discoverops.restlater.connection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Scope(value = "singleton")
@Component
public class ConnectionRepository {

    private Map<UUID, HttpURLConnection> connections;

    public ConnectionRepository() {
        connections = new HashMap<UUID, HttpURLConnection>();
    }

    public synchronized void put(UUID id, HttpURLConnection connection)
    {
        connections.put(id, connection);
    }

    public synchronized HttpURLConnection get(UUID id) {
        return connections.get(id);
    }
}
