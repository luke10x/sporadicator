package dev.luke10x.h2sproxy.rest;

import dev.luke10x.h2sproxy.domain.FutureResponseRepository;
import dev.luke10x.h2sproxy.domain.response.FutureResponse;
import dev.luke10x.h2sproxy.domain.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/task")
public class ResultController {

    @Autowired
    FutureResponseRepository futureResponseRepository;

    @RequestMapping(value = "/status/{uuid}", method = {RequestMethod.GET})
    public String status(@PathVariable("uuid") String uuid) throws IOException, ExecutionException, InterruptedException {

        FutureResponse futureResponse = futureResponseRepository.get(uuid);
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

        StringBuffer stringBufferResult = response.toStringBuffer();
        String rawResult = response.getResponseBody();

        String responseText = "Response: "
                + (stringBufferResult != null ? new String(stringBufferResult) : "null" )
                + "RawResponse " + (rawResult != null ? rawResult : "null") ;
        System.out.println(responseText);

        return responseText;
    }
}
