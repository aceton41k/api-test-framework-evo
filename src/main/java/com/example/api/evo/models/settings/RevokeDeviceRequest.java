package com.example.api.evo.models.settings;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class RevokeDeviceRequest extends BaseRequest<RevokeDeviceRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("device_id")
        private String deviceId;

        @JsonProperty("app_type")
        private String appType;
    }
}
