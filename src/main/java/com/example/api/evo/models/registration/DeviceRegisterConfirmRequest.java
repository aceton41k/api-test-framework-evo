package com.example.api.evo.models.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class DeviceRegisterConfirmRequest extends BaseRequest<DeviceRegisterConfirmRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("phone_number")
        private String phoneNumber;

        @JsonProperty("device_id")
        private String deviceId;

        @JsonProperty("confirm_token")
        private String confirmToken;

        private Boolean upgrade;
    }
}
