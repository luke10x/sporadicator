package com.discoverops.restlater.rest;

import com.discoverops.restlater.domain.*;
import com.discoverops.restlater.http.MyUriRequest;
import com.discoverops.restlater.rest.contract.ConnectionAccepted;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired()
    @Qualifier("httpClient")
    Client client;

    @RequestMapping(value="/**")
    public ConnectionAccepted forward(HttpMethod method, HttpEntity<byte[]> requestEntity) throws IOException {

        HttpUriRequest restRequest = new MyUriRequest(method.toString(), "http://backend/index.php");
        Request request = new Request(restRequest);

        Future<Response> futureResponse = client.executeAsync(request);

        UUID uuid = UUID.randomUUID();

        futureResponseRepository.put(uuid, futureResponse);

        return new ConnectionAccepted(uuid);
    }
}
