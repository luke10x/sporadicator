package com.discoverops.sporadicator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.discoverops.sporadicator.domain")
public class SporadicatorApplication {

    protected static class Parent {}

    public static void main(String[] args) {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(Parent.class);

        builder.child(ProxyApplication.class)
                .properties("server.port:8181")
                .run(args);

        builder.child(ResultApplication.class)
                .properties("server.port:8282")
                .run(args);
    }
}
