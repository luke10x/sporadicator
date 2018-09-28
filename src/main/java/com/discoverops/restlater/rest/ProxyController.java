package com.discoverops.restlater.rest;

import com.discoverops.restlater.concurrency.ThreadPool;
import com.discoverops.restlater.connection.*;
import com.discoverops.restlater.http.MyUriRequest;
import com.discoverops.restlater.rest.Response.ConnectionAccepted;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.*;

@RestController
@RequestMapping(value="/")
public class ProxyController {

    @Autowired
    FutureResponseRepository futureResponseRepository;

    @Autowired
    ThreadPool threadPool;

    @RequestMapping(value="/**")
    public ConnectionAccepted forward(HttpMethod method, HttpEntity<byte[]> requestEntity) throws IOException {

        UUID uuid = UUID.randomUUID();

        CloseableHttpClient client = HttpClients.createDefault();

        HttpUriRequest request = new MyUriRequest(method.toString(), "http://backend/index.php");

        FutureTask<Response> futureResponse = new FutureTask<>(() -> {
            CloseableHttpResponse httpResponse = client.execute(request);
            return new Response(httpResponse.getEntity().getContent());
        });

        futureResponseRepository.put(uuid, futureResponse);

        threadPool.execute(futureResponse);

        return new ConnectionAccepted(uuid);
    }
}
