package com.discoverops.restlater.connection;

public class Header {

    final private String key;
    final private String value;

    public Header(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
