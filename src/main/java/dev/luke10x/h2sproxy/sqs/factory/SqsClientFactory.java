package dev.luke10x.h2sproxy.sqs.factory;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

@Component
public class HttpClientFactory {
    public CloseableHttpClient create() {
        return HttpClients.createDefault();
    }
}
