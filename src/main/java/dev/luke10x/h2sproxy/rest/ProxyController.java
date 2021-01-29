package dev.luke10x.http2sqsproxy.rest;

import dev.luke10x.http2sqsproxy.domain.AsyncClient;
import dev.luke10x.http2sqsproxy.domain.FutureResponseRepository;
import dev.luke10x.http2sqsproxy.domain.request.Request;
import dev.luke10x.http2sqsproxy.domain.response.FutureResponse;
import dev.luke10x.http2sqsproxy.rest.contract.ConnectionAccepted;
import dev.luke10x.http2sqsproxy.rest.factory.RequestFactory;
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
