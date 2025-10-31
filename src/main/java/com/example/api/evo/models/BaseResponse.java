package com.example.api.evo.models;

import static com.example.api.evo.constants.ServiceData.JSON_RPC_VERSION;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode()
public abstract class BaseResponse<R> {
    private String id;
    private String jsonrpc = JSON_RPC_VERSION;
    private R result;
}
