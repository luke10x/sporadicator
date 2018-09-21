package com.discoverops.restlater.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            responseRepository.put(uuid, response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
