package dev.luke10x.http2sqsproxy.domain.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

final public class Response {
    private final InputStream responseStream;

    public Response(InputStream responseStream) {
        this.responseStream = responseStream;
    }

    public StringBuffer toStringBuffer() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(responseStream));

        String inputLine;
        StringBuffer buffer = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            buffer.append(inputLine);
        }
        in.close();

        return buffer;
    }
}
