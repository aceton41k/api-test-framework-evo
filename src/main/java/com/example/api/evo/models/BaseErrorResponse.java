package com.example.api.evo.models;

import lombok.Getter;

import static com.example.api.evo.constants.ServiceData.JSON_RPC_VERSION;

@Getter
public class BaseErrorResponse {
    private final String jsonrpc = JSON_RPC_VERSION;
    private Long id;
    private Error error;

    @Getter
    public static class Error {
        private Integer code;
        private String data;
        private String message;
    }
}
