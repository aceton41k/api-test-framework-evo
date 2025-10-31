package com.example.api.evo.models.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.constants.ServiceData;
import com.example.api.evo.models.BaseRequest;

@SuperBuilder
public class SubscriptionListRequest extends BaseRequest<SubscriptionListRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("api_version")
        @Builder.Default
        private Integer apiVersion = ServiceData.API_VERSION;

        @JsonProperty("page_number")
        @Builder.Default
        private Integer pageNumber = 1;
    }
}