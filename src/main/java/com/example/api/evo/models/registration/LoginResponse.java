package com.example.api.evo.models.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class LoginResponse extends BaseResponse<LoginResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("session_key")
        private String sessionKey;

        @JsonProperty("is_identified")
        private Boolean isIdentified;

        @JsonProperty("subscription_status")
        private String subscriptionStatus;

        @JsonProperty("fraud_status")
        private Boolean fraudStatus;

        @JsonProperty("web_session")
        private String webSession;

        private Monitoring monitoring;
        private User user;

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
            private String language;

            @JsonProperty("wallet_opened")
            private Boolean walletOpened;
        }
    }
}
