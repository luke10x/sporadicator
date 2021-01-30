package dev.luke10x.h2sproxy.rest;

import dev.luke10x.h2sproxy.domain.AsyncClient;
import dev.luke10x.h2sproxy.domain.FutureResponseRepository;
import dev.luke10x.h2sproxy.domain.request.Request;
import dev.luke10x.h2sproxy.domain.response.FutureResponse;
import dev.luke10x.h2sproxy.rest.contract.ConnectionAccepted;
import dev.luke10x.h2sproxy.rest.factory.RequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/")
public class ProxyController {

    @Autowired
    RequestFactory requestFactory;

    @Autowired
    @Qualifier("httpAsyncClient")
    AsyncClient asyncClient;

    @Autowired
    @Qualifier("sqsAsyncClient")
    AsyncClient sqsAsyncClient;

    @Autowired
    FutureResponseRepository futureResponseRepository;

    @RequestMapping(value = "/**")
    public ConnectionAccepted forward(HttpMethod method, HttpEntity<byte[]> requestEntity) throws IOException {

        Request request = requestFactory.create(method, requestEntity);

        FutureResponse futureResponse = sqsAsyncClient.executeAsync(request);

        futureResponseRepository.save(futureResponse);

        return new ConnectionAccepted(futureResponse.getRequestId());
    }
}
