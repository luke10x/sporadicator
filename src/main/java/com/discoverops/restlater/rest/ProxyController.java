package com.discoverops.restlater.rest;

import com.discoverops.restlater.domain.*;
import com.discoverops.restlater.rest.contract.ConnectionAccepted;
import com.discoverops.restlater.rest.factory.RequestFactory;
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
    RequestFactory requestFactory;

    @Autowired
    @Qualifier("httpClient")
    Client client;

    @Autowired
    FutureResponseRepository futureResponseRepository;

    @RequestMapping(value="/**")
    public ConnectionAccepted forward(HttpMethod method, HttpEntity<byte[]> requestEntity) throws IOException {

//        HttpUriRequest restRequest = new MyUriRequest(method.toString(), "http://backend/index.php");
//        Request request = new Request(restRequest);

        Request request = requestFactory.create(method);

        Future<Response> futureResponse = client.executeAsync(request);

        UUID uuid = UUID.randomUUID();

        futureResponseRepository.put(uuid, futureResponse);

        return new ConnectionAccepted(uuid);
    }
}
