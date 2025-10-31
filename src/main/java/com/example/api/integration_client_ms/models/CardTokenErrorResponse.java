package com.example.api.integration_client_ms.models;

import lombok.Getter;

@Getter
public class CardTokenErrorResponse {
    private String code;
    private String message;
    private String target;
    private Locale locale;

    @Getter
    public static class Locale {
        private String extra;
        private String message;
    }
}
