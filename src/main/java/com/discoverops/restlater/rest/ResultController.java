package com.discoverops.restlater.rest;

import com.discoverops.restlater.connection.FutureResponseRepository;
import com.discoverops.restlater.connection.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value="/task")
public class ResultController {

    @Autowired
    FutureResponseRepository futureResponseRepository;

    @RequestMapping(value = "/status/{uuid}", method = {RequestMethod.GET})
    public String status(@PathVariable("uuid") UUID uuid) throws IOException, ExecutionException, InterruptedException {

        Future<Response> futureResponse = futureResponseRepository.get(uuid);
        if (null == futureResponse) {
            return "Such request does not even exist";
        }

        if (futureResponse.isCancelled()) {
            return "Unfortunately the response is cancelled";
        }

        if (!futureResponse.isDone()) {
            return "Response is still in progress, try again later";
        }

        Response response = futureResponse.get();

        String result = new String(response.toStringBuffer());
        System.out.println(result);
        return "Response: " + result;
    }
}
