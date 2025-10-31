package com.example.api.evo.models.settings;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class DeviceListResponse extends BaseResponse<List<DeviceListResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("device_id")
        private String deviceId;

        @JsonProperty("device_type_name")
        private String deviceTypeName;

        @JsonProperty("device_name")
        private String deviceName;

        @JsonProperty("register_datetime")
        private String registerDatetime;

        @JsonProperty("login_datetime")
        private String loginDatetime;

        @JsonProperty("app_version")
        private String appVersion;

        @JsonProperty("app_type")
        private String appType;
    }
}
