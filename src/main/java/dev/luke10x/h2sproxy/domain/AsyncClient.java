package dev.luke10x.h2sproxy.domain;

import dev.luke10x.h2sproxy.domain.request.Request;
import dev.luke10x.h2sproxy.domain.response.FutureResponse;

import javax.jms.JMSException;

public interface AsyncClient {
    public FutureResponse executeAsync(Request request);
}
