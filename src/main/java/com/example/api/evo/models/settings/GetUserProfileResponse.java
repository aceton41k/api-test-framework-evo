package com.example.api.evo.models.settings;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class GetUserProfileResponse extends BaseResponse<GetUserProfileResponse.Result> {
    @Getter
    public static class Result {
        private String name;
        private String surname;
        private String patronym;
        private String gender;
        private Long birthdate;

        @JsonProperty("region_code")
        private String regionCode;

        @JsonProperty("profile_editable")
        private Boolean profileEditable;

        @JsonProperty("image_url")
        private String imageUrl;

        @JsonProperty("regular_payments")
        private Boolean regularPayments;

        @JsonProperty("is_identificated")
        private Boolean isIdentificated;

        @JsonProperty("identification_date")
        private String identificationDate;

        private Passport passport;

        @JsonProperty("fraud_level")
        private Integer fraudLevel;
    }

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
