package dev.luke10x.h2sproxy.jms;

public class JmsRequest {
    private String originalRequestMessageId;
    private String response;
    private String originalRequest;

    public JmsRequest() {}

    public JmsRequest(String originalRequestMessageId, String response, String originalRequest) {
        this.originalRequestMessageId = originalRequestMessageId;
        this.response = response;
        this.originalRequest = originalRequest;
    }

    public String getOriginalRequestMessageId() {
        return originalRequestMessageId;
    }

    public String getResponse() {
        return response;
    }

    public String getOriginalRequest() {
        return originalRequest;
    }
}
