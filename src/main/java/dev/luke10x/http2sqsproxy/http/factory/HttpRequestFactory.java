package dev.luke10x.http2sqsproxy.http.factory;

import dev.luke10x.http2sqsproxy.domain.request.Request;
import dev.luke10x.http2sqsproxy.domain.request.Header;
import dev.luke10x.http2sqsproxy.http.UniversalUriRequest;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestFactory {
    public HttpUriRequest create(Request request) {
        UniversalUriRequest httpUriRequest = new UniversalUriRequest(request.getMethod(), request.getUri());

        httpUriRequest.setEntity(new ByteArrayEntity(request.getBody()));

        for (Header header : request.getHeaders()) {
            if ("content-length".equals(header.getKey().toLowerCase())) continue;
            httpUriRequest.setHeader(header.getKey(), header.getValue());
        }

        return httpUriRequest;
    }
}
