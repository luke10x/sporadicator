package com.discoverops.restlater.connection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Component
public class ConnectionFactory {
    public HttpURLConnection create(HttpMethod method, HttpEntity<byte[]> requestEntity) throws IOException {

        URL url = new URL("http://backend");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod(method.toString());

        HttpHeaders headers = requestEntity.getHeaders();
        for (Map.Entry<String, List<String>> header: headers.entrySet()) {
            for (String repeated : header.getValue())
            con.addRequestProperty(header.getKey(), repeated);
        }

        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(requestEntity.getBody());
        os.close();

        return con;
    }
}
