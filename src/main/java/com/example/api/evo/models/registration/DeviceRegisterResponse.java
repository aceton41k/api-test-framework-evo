package com.example.api.evo.models.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

public class DeviceRegisterResponse extends BaseResponse<DeviceRegisterResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("device_id")
        private String deviceId;
        private Boolean sms;
    }
}