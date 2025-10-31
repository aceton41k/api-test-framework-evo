package com.example.api.evo.models.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.constants.DeviceEnum;
import com.example.api.evo.models.BaseRequest;

@SuperBuilder
public class VersionInfoRequest extends BaseRequest<VersionInfoRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private Integer version;

        @JsonProperty("device_type")
        @Builder.Default
        private Integer deviceType = DeviceEnum.ANDROID.getType();
    }
}
