package dev.luke10x.h2sproxy.domain.response;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface FutureResponse {
    String getRequestId();

    boolean isCancelled();

    boolean isDone();

    Response get() throws InterruptedException, ExecutionException;
}
