package com.discoverops.restlater;

import com.discoverops.restlater.connection.ConnectionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class RestlaterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestlaterApplication.class, args);
    }
}
