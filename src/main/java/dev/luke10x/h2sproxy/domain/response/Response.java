package dev.luke10x.h2sproxy.domain.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

final public class Response {
    private InputStream responseStream;
    private String responseBody;

    public Response(InputStream responseStream) {
        this.responseStream = responseStream;
    }

    public StringBuffer toStringBuffer() throws IOException {
        if (responseStream == null) {
            return null;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(responseStream));

        String inputLine;
        StringBuffer buffer = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            buffer.append(inputLine);
        }
        in.close();

        return buffer;
    }

    public Response(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
