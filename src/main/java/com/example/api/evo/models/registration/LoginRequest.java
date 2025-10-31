package com.example.api.evo.models.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.constants.DeviceEnum;
import com.example.api.evo.models.BaseRequest;

@SuperBuilder
public class LoginRequest extends BaseRequest<LoginRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("phone_number")
        private String phoneNumber;

        @JsonProperty("device_id")
        private String deviceId;

        private String password;
        private String datetime;

        @JsonProperty("app_version")
        @Builder.Default
        private String appVersion = DeviceEnum.ANDROID.getAppVersion();
    }
}
