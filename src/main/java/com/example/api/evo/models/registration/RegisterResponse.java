package com.example.api.evo.models.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class RegisterResponse extends BaseResponse<RegisterResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("session_key")
        private String sessionKey;

        @JsonProperty("is_identified")
        private Boolean isIdentified;

        @JsonProperty("web_session")
        private String webSession;

        @JsonProperty("subscription_status")
        private Object subscriptionStatus; // viasnit' 4to eto

        private Boolean fraud_status;
        private RegisterResponse.Result.Monitoring monitoring;
        private RegisterResponse.Result.User user;
        private RegisterResponse.Result result;

        @Getter
        public static class Monitoring {
            private Boolean enabled;
            private Integer amount;
        }

        @Getter
        public static class User {
            @JsonProperty("client_id")
            private Integer clientId;

            private String name;
            private String status;

            @JsonProperty("default_account_id")
            private Long defaultAccountId;

            private String language;

            @JsonProperty("wallet_opened")
            private Boolean walletOpened;
        }
    }
}
