package dev.luke10x.h2sproxy.rest.factory;

import dev.luke10x.h2sproxy.domain.request.Request;
import dev.luke10x.h2sproxy.domain.request.Header;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RequestFactory {
    public Request create(HttpMethod restMethod, HttpEntity<byte[]> requestEntity) {

        List<Header> headers = new ArrayList<>();
        for (Map.Entry<String, List<String>> headerSet : requestEntity.getHeaders().entrySet()) {
            String headerKey = headerSet.getKey();
            for (String headerValue : headerSet.getValue()) {
                headers.add(new Header(headerKey, headerValue));
            }
        }

        Request request = new Request(
                restMethod.toString(),
                "http://backend/index.php",
                headers,
                requestEntity.getBody()
        );

        return request;
    }
}
