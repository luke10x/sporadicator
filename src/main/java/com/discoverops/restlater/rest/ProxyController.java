package com.discoverops.restlater.rest;

import com.discoverops.restlater.connection.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.UUID;

@RestController
@RequestMapping(value="/")
public class ProxyController {

    @Autowired
    ConnectionRepository applicationScopedBean;

    @Autowired
    ResponseRepository responseRepository;

    @Autowired
    ConnectionFactory connectionFactory;

    @Autowired
    ThreadPool threadPool;

    @RequestMapping(value="/**")
    public String forward(HttpMethod method, HttpEntity<byte[]> requestEntity) throws IOException {

        HttpURLConnection con = connectionFactory.create(method, requestEntity);
        UUID uuid = UUID.randomUUID();
        applicationScopedBean.put(uuid, con);

        ConnectionTask task = new ConnectionTask(uuid, responseRepository, con);
        threadPool.execute(task);

        return "UUID: " + uuid.toString();
    }
}