package com.discoverops.restlater.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.UUID;

public class ConnectionTask implements Runnable {

    private UUID uuid;
    private ResponseRepository responseRepository;
    private HttpURLConnection connection;

    public ConnectionTask(UUID uuid, ResponseRepository responseRepository, HttpURLConnection connection) {
        this.responseRepository = responseRepository;
        this.connection = connection;
        this.uuid = uuid;
    }

    @Override
    public void run() {
        int responseCode = 0;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Response Code : " + responseCode);



        BufferedReader in = null;
        try {
            Response response = new Response(connection.getInputStream());

//            connection.get
            responseRepository.put(uuid, response.toStringBuffer());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
