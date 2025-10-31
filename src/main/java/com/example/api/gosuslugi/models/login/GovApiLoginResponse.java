package com.example.api.gosuslugi.models.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import com.example.api.evo.config.HeadersFactory;

import java.util.Map;

@Getter
@Setter
public class GovApiLoginResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    private User user;

    private String webSession;

    @Getter
    public static class User {
        private Integer id;

        @JsonProperty("client_id")
        private Integer clientId;

        private String name;
        private String surname;
        private String patronym;

        @JsonProperty("phone_number")
        private String phoneNumber;

        private String gender;
        private String birthdate;

        @JsonProperty("region_code")
        private Integer regionCode;

        @JsonProperty("is_premium")
        private Boolean isPremium;

        private String lang;

        @JsonProperty("last_visited_at")
        private String lastVisitedAt;

        private String notifications;
        private String status;

        @JsonProperty("created_at")
        private String createdAt;

        @JsonProperty("updated_at")
        private String updatedAt;

        @JsonProperty("is_identified")
        private boolean isIdentified;

        @JsonProperty("is_oneid")
        private Boolean isOneid;

        @JsonProperty("is_our_identified")
        private boolean isOurIdentified;

        @JsonProperty("identification_date")
        private String identificationDate;

        @JsonProperty("click_name")
        private String clickName;

        @JsonProperty("click_surname")
        private String clickSurname;

        @JsonProperty("click_patronym")
        private String clickPatronym;

        private Passport passport;
        @Getter
        public static class Passport {
            private String serie;
            private String pinfl;

            @JsonProperty("expiry_date")
            private String expiryDate;

            @JsonProperty("issued_date")
            private String issuedDate;
        }
    }

    public Map<String, String> getToken() {
        return HeadersFactory.builder()
                .authBearerToken(accessToken)
                .build();
    }

}
