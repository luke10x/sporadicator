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

        Request request = requestFactory.create(method);

        FutureResponse futureResponse = client.executeAsync(request);

        futureResponseRepository.save(futureResponse);

        return new ConnectionAccepted(futureResponse.getUUID());
    }
}
