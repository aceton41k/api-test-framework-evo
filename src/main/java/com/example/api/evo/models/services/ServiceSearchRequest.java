package com.example.api.evo.models.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

@SuperBuilder
public class ServiceSearchRequest extends BaseRequest<ServiceSearchRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("api_version")
        @Builder.Default
        private Integer apiVersion = 7;

        private String search;
    }
}
