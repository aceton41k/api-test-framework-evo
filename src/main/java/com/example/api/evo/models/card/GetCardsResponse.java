package com.example.api.evo.models.card;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class GetCardsResponse extends BaseResponse<List<GetCardsResponse.Result>> {
    @Getter
    public static class Result {
        private Long id;

        @JsonProperty("bank_code")
        private String bankCode;

        @JsonProperty("bank_name")
        private String bankName;

        @JsonProperty("bank_short_name")
        private String bankShortName;

        @JsonProperty("bank_color")
        private String bankColor;

        @JsonProperty("card_name")
        private String cardName;

        @JsonProperty("card_number")
        private String cardNumber;

        @JsonProperty("card_expire_date")
        private String cardExpireDate;

        @JsonProperty("card_status")
        private Integer cardStatus;

        @JsonProperty("card_status_text")
        private String cardStatusText;

        @JsonProperty("card_token")
        private String cardToken;

        @JsonProperty("card_type")
        private String cardType;

        @JsonProperty("monitoring_status")
        private Integer monitoringStatus;

        @JsonProperty("is_default")
        private Boolean isDefault;

        @JsonProperty("card_number_hash")
        private String cardNumberHash;

        private String cardholder;

        @JsonProperty("currency_code")
        private String currencyCode;

        private Boolean click;
        private Boolean details;

        @JsonProperty("securecode_available")
        private Boolean securecodeAvailable;

        @JsonProperty("securecode_status")
        private Boolean securecodeStatus;

        @JsonProperty("is_identified")
        private Boolean isIdentified;

        private Permission permission;
        private Images images;

        @JsonProperty("transfer_limits")
        private TransferLimits transferLimits;

        private Options options;
        private Monitoring monitoring;
    }

    @Getter
    public static class Permission {
        private Integer payment;
        private List<String> transfer;
        private Integer clickpass;
        private Boolean blockable;
        private Boolean activation;

        @JsonProperty("copy_number")
        private Boolean copyNumber;

        private Boolean history;

        @JsonProperty("set_as_default")
        private Boolean setAsDefault;

        private Boolean balance;
        private Boolean tokenization;
        private Integer removable;
    }

    @Getter
    public static class Images {
        private String background;
        private String logo;

        @JsonProperty("mini_logo")
        private String miniLogo;

        @JsonProperty("logo_mini_white")
        private String logoMiniWhite;

        @JsonProperty("cardtype_mini_white")
        private String cardtypeMiniWhite;

        private String cardtype;

        @JsonProperty("cardtype_mini")
        private String cardtypeMini;

        @JsonProperty("badge_url")
        private String badgeUrl;
    }

    @Getter
    public static class TransferLimits {
        @JsonProperty("send_min_limit")
        private Integer sendMinLimit;

        @JsonProperty("send_max_limit")
        private Integer sendMaxLimit;

        @JsonProperty("receive_min_limit")
        private Integer receiveMinLimit;

        @JsonProperty("receive_max_limit")
        private Integer receiveMaxLimit;

        private Integer percent;
    }

    @Getter
    public static class Options {
        @JsonProperty("is_masked")
        private Boolean isMasked;

        @JsonProperty("button_set")
        private String buttonSet;

        @JsonProperty("display_type")
        private String displayType;
    }

    @Getter
    public static class Monitoring {
        private Boolean available;
        private Integer price;
        private String currency;
        private Boolean toggle;
        private Boolean status;
    }
}
