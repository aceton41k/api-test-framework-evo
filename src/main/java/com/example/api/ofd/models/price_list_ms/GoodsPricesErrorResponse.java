package com.example.api.ofd.models.price_list_ms;

import lombok.Getter;

@Getter
public class GoodsPricesErrorResponse {
    private ErrorBody error;

    @Getter
    public static class ErrorBody {
        private String code;
        private String message;
        private String target;
        private Localize localize;
    }

    @Getter
    public static class Localize {
        private String message;
        private String extra;
    }
}
