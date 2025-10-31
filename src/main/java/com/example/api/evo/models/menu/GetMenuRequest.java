package com.example.api.evo.models.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.constants.DeviceEnum;
import com.example.api.evo.models.BaseRequest;

@SuperBuilder
public class GetMenuRequest extends BaseRequest<GetMenuRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("device_type")
        @Builder.Default
        private Integer deviceType = DeviceEnum.ANDROID.getType();

        @Builder.Default
        private Boolean online = true;
    }
}
