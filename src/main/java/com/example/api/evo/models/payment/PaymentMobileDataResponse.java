package com.example.api.evo.models.payment;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class PaymentMobileDataResponse extends BaseResponse<PaymentMobileDataResponse.Result> {
    @Getter
    public static class Result {
        private String type;
        private Service service;
        private Form form;
    }

    @Getter
    public static class Service {
        private Integer id;
        private String name;

        @JsonProperty("category_id")
        private Integer categoryId;

        private Integer status;
        private String image;

        @JsonProperty("min_limit")
        private Integer minLimit;

        @JsonProperty("max_limit")
        private Integer maxLimit;

        @JsonProperty("commission_percent")
        private Double commissionPercent;

        @JsonProperty("card_types")
        private List<String> cardTypes;

        private Boolean maintenance;

        @JsonProperty("direct_payment")
        private Boolean directPayment;

        @JsonProperty("favorite_permission")
        private Boolean favoritePermission;

        @JsonProperty("myhome_permission")
        private Boolean myhomePermission;

        @JsonProperty("cashback_label")
        private String cashbackLabel;

        private String label;

        @JsonProperty("qr_only")
        private Boolean qrOnly;

        private Location location;
    }

    @Getter
    public static class Location {
        private Double lat;

        @JsonProperty("long") // Обрабатываем ключ JSON, который совпадает с типом данных `long`
        private Double lon;
    }

    @Getter
    public static class Form {
        private Integer step;
        private String title;

        @JsonProperty("get_info")
        private Boolean getInfo;

        private List<List<Field>> form;
        private List<Object> condition;
        private Settings settings;
        private List<Object> offline;
    }

    @Getter
    public static class Field {
        private String type;
        private Options options;
        private String name;
        private Integer weight;
    }

    @Getter
    public static class Options {
        private String label;
        private String placeholder;
        private String hint;
        private Boolean required;
        private Boolean hidden;
        private Boolean offline;

        @JsonProperty("confirmation_hidden")
        private Boolean confirmationHidden;

        private Boolean limiter;
        private String suggestions;
        private Boolean readonly;
        private String value;
        private String keyboard;
        private Integer minLines;
        private Integer min;
        private Integer max;
        private String mask;
        private String regex;

        @JsonProperty("error_message")
        private String errorMessage;

        @JsonProperty("qr_scanner")
        private Boolean qrScanner;

        @JsonProperty("input_currency")
        private String inputCurrency;

        @JsonProperty("deposit_currency")
        private String depositCurrency;

        private Boolean decimal;

        @JsonProperty("withdrawal_currency")
        private String withdrawalCurrency;

        private Double cost;
        private Double rate;

        @JsonProperty("commission_min_amount")
        private Double commissionMinAmount;

        private Integer nds;

        @JsonProperty("low_ratio")
        private Double lowRatio;

        private String text;
        private String color;
        private String style;
        private String link;

        @JsonProperty("commission_percent")
        private Double commissionPercent;
    }

    @Getter
    public static class Settings {
        private Map<String, Object> data;

        @JsonProperty("ussd_query")
        private String ussdQuery;
    }
}
