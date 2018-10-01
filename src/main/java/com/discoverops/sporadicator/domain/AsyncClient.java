package com.discoverops.sporadicator.domain;

import com.discoverops.sporadicator.domain.request.Request;
import com.discoverops.sporadicator.domain.response.FutureResponse;

public interface AsyncClient {
    public FutureResponse executeAsync(Request request);
}
