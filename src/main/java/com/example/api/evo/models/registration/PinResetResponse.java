package com.example.api.evo.models.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class PinResetResponse extends BaseResponse<PinResetResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("confirm_needed")
        private Boolean confirmNeeded;

        @JsonProperty("register_token")
        private String registerToken;
        private Boolean sms;
    }
}
