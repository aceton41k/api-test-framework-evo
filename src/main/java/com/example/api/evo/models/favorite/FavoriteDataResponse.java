package com.example.api.evo.models.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;
import java.util.Map;

@Getter
public class FavoriteDataResponse extends BaseResponse<FavoriteDataResponse.Result> {

    @Getter
    public static class Result {
        private Integer step;
        private String title;

        @JsonProperty("get_info")
        private Boolean getInfo;

        private List<List<FormField>> form;
        private List<Object> condition;
        private Settings settings;
        private List<Object> offline;
        private Map<String, Object> data;
    }

    @Getter
    public static class FormField {
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
        private String keyboard;
        private Integer min;
        private Integer max;
        private String mask;
        private String regex;

        @JsonProperty("error_message")
        private String errorMessage;

        @JsonProperty("qr_scanner")
        private Boolean qrScanner;

        private Object value;

        // Input.Text специфичные
        @JsonProperty("confirmation_hidden")
        private Boolean confirmationHidden;

        private Boolean limiter;
        private String suggestions;
        private Boolean readonly;

        @JsonProperty("minLines")
        private Integer minLines;

        // Amount.Input специфичные
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

        // Button.Button специфичные
        private String text;
        private String color;
        private String style;
        private String link;
    }

    @Getter
    public static class Settings {
        private Map<String, Object> data;

        @JsonProperty("ussd_query")
        private String ussdQuery;
    }
}
