package com.example.api.evo.models.myhome;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class MyHomePaymentDataResponse extends BaseResponse<MyHomePaymentDataResponse.Result> {
    @Getter
    public static class Result {
        private Integer step;
        private String title;

        @JsonProperty("get_info")
        private Boolean getInfo;

        private List<List<FormElement>> form;
        private List<Object> condition;
        private Settings settings;
        private List<Object> offline;
    }

    @Getter
    public static class FormElement {
        private String type;
        private Options options;
        private String name;
        private Integer weight;

        @Getter
        public static class Options {
            private String label;
            private String placeholder;
            private String hint;
            private Boolean required;
            private Boolean hidden;
            private Boolean offline;
            private String keyboard;
            private String minLines;
            private Integer min;
            private Integer max;
            private String mask;
            private String regex;

            @JsonProperty("error_message")
            private String errorMessage;

            @JsonProperty("qr_scanner")
            private Boolean qrScanner;

            private String value;

            @JsonProperty("confirmation_hidden")
            private Boolean confirmationHidden;

            private Boolean limiter;
            private String suggestions;
            private Boolean readonly;

            @JsonProperty("input_currency")
            private String inputCurrency;

            @JsonProperty("deposit_currency")
            private String depositCurrency;

            private Boolean decimal;

            @JsonProperty("withdrawal_currency")
            private String withdrawalCurrency;

            private Integer cost;
            private Integer rate;

            @JsonProperty("commission_percent")
            private Integer commissionPercent;

            @JsonProperty("commission_min_amount")
            private Integer commissionMinAmount;

            private Integer nds;

            @JsonProperty("low_ratio")
            private Integer lowRatio;

            private String style;
            private String text;
            private String color;
            private String link;
        }
    }

    @Getter
    public static class Settings {
        private Map<String, Object> data;

        @JsonProperty("ussd_query")
        private String ussdQuery;
    }
}
