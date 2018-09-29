package com.discoverops.restlater.rest.factory;

import com.discoverops.restlater.domain.Request;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class RequestFactory {
    public Request create(HttpMethod restMethod) {

        Request request = new Request(restMethod.toString(), "http://backend/index.php");
        return request;
    }
}
