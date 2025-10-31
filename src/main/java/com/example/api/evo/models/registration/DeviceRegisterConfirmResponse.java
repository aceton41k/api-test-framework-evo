package com.example.api.evo.models.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class DeviceRegisterConfirmResponse extends BaseResponse<DeviceRegisterConfirmResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("next_step")
        private String nextStep;

        @JsonProperty("register_token")
        private String registerToken;

        @JsonProperty("clickpass_token")
        private String clickpassToken;
    }
}
