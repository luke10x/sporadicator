package dev.luke10x.h2sproxy.jms;

import dev.luke10x.h2sproxy.domain.response.FutureResponse;
import dev.luke10x.h2sproxy.domain.response.Response;

public class JmsFutureResponse implements FutureResponse {
    private final String requestId;

    private final String originalReq;

    private Response receivedResponse = null;

    public JmsFutureResponse(String messageId, String originalReq) {
        requestId = messageId;
        this.originalReq = originalReq;
    }

    public String getRequestId() {
        return requestId;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return receivedResponse != null;
    }

    @Override
    public Response get() {
        return receivedResponse;
    }

    public void finish(Response response) {
        this.receivedResponse = response;

    }
}
