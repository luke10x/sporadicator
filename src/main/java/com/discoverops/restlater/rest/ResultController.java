package com.discoverops.restlater.rest;

import com.discoverops.restlater.connection.ConnectionRepository;
import com.discoverops.restlater.connection.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.UUID;

@RestController
@RequestMapping(value="/task")
public class ResultController {

    @Autowired
    ConnectionRepository connectionRepository;

    @Autowired
    ResponseRepository responseRepository;

    @RequestMapping(value = "/status/{uuid}", method = {RequestMethod.GET})
    public String status(@PathVariable("uuid") UUID uuid) throws IOException {

        HttpURLConnection connection = connectionRepository.get(uuid);
        if (null == connection) {
            return "This connection does not even exist.";
        }
        StringBuffer response = responseRepository.get(uuid);
        if (null == response) {
            return "Response is not yet ready, try again later";
        }

        //print result
        System.out.println(response.toString());
        return "Response: " + response.toString();
    }
}
