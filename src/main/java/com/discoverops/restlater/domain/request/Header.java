package com.discoverops.restlater.domain.request;

public class Header {

    private final String key;
    private final String value;

    public Header(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
