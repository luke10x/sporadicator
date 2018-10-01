package com.discoverops.sporadicator.rest;

import com.discoverops.sporadicator.domain.*;
import com.discoverops.sporadicator.domain.request.Request;
import com.discoverops.sporadicator.domain.response.FutureResponse;
import com.discoverops.sporadicator.rest.contract.ConnectionAccepted;
import com.discoverops.sporadicator.rest.factory.RequestFactory;
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
    @Qualifier("httpAsyncClient")
    AsyncClient asyncClient;

    @Autowired
    FutureResponseRepository futureResponseRepository;

    @RequestMapping(value="/**")
    public ConnectionAccepted forward(HttpMethod method, HttpEntity<byte[]> requestEntity) throws IOException {

        Request request = requestFactory.create(method, requestEntity);

        FutureResponse futureResponse = asyncClient.executeAsync(request);

        futureResponseRepository.save(futureResponse);

        return new ConnectionAccepted(futureResponse.getUUID());
    }
}
