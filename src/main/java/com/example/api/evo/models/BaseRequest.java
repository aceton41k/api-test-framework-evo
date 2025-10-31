package com.example.api.evo.models;

import lombok.Builder.Default;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import static com.example.api.evo.constants.ServiceData.JSON_RPC_VERSION;

@Getter
@SuperBuilder
public abstract class BaseRequest<T> {
    @Default
    private String jsonrpc = JSON_RPC_VERSION;

    private String method;
    private String id;
    private T params;
}
