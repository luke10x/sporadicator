package com.discoverops.restlater.http.factory;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

@Component
public class HttpClientFactory {
    public CloseableHttpClient create() {
        return HttpClients.createDefault();
    }
}
