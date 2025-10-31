package com.example.api.evo.models.auto_pay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

@Getter
@SuperBuilder
public class AutoPayDeleteRequest extends BaseRequest<AutoPayDeleteRequest.Params> {

    @Getter
    @Builder
    public static class Params {

        @JsonProperty("autopay_type")
        @Builder.Default
        private Integer autopayType = AutoPayType.SCHEDULE;

        @JsonProperty("autopay_id")
        private Long autopayId;
    }
}
