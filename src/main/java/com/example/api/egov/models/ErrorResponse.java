package com.example.api.egov.models;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String code;
    private String message;
    private String target;
    private Locale locale;

    @Getter
    public static class Locale {
        private String message;
        private String extra;
    }
}
