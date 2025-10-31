package com.example.api.bnpl.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class AccountsListResponse {

    private String status;
    private String message;
    private Data data;

    @Getter
    public static class Data {
        private List<Account> accounts;
    }

    @Getter
    public static class Account {
        private Long id;
        private String name;

        @JsonProperty("card_number")
        private String cardNumber;

        @JsonProperty("expire_date")
        private String expireDate;

        private Integer status;

        @JsonProperty("status_text")
        private String statusText;

        private String type;

        @JsonProperty("is_default")
        private Boolean isDefault;

        private String holder;

        @JsonProperty("currency_code")
        private String currencyCode;

        @JsonProperty("mini_logo_url")
        private String miniLogoUrl;

        @JsonProperty("logo_url")
        private String logoUrl;

        @JsonProperty("monitoring_status")
        private Integer monitoringStatus;

        @JsonProperty("monitoring_available")
        private Boolean monitoringAvailable;
    }
}
