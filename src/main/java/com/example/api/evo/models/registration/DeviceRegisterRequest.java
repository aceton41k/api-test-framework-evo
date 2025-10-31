package com.example.api.evo.models.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import com.example.api.evo.models.BaseRequest;

import static com.example.api.evo.constants.DeviceEnum.ANDROID;

@SuperBuilder
public class DeviceRegisterRequest extends BaseRequest<DeviceRegisterRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("app_version")
        @Builder.Default
        private String appVersion = ANDROID.getAppVersion();

        @JsonProperty("device_info")
        @Builder.Default
        private String deviceInfo = "Test Device Info" + RandomStringUtils.randomNumeric(15);

        @JsonProperty("device_name")
        @Builder.Default
        private String deviceName = "Test Device Name " + RandomStringUtils.randomNumeric(15);

        @JsonProperty("device_type")
        @Builder.Default
        private Integer deviceType = ANDROID.getType();

        @Builder.Default
        private String imei = RandomStringUtils.randomNumeric(15);

        @JsonProperty("phone_number")
        private String phoneNumber;
    }
}
