package com.example.api.evo.models.auto_pay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

import static com.example.api.evo.constants.ServiceData.API_VERSION;

@SuperBuilder
public class AutoPayDataRequest extends BaseRequest<AutoPayDataRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("api_version")
        @Builder.Default
        private Integer apiVersion = API_VERSION;

        @JsonProperty("autopay_type")
        @Builder.Default
        private Integer autopayType = AutoPayType.SCHEDULE;

        @JsonProperty("service_id")
        private Long serviceId;
    }
}
