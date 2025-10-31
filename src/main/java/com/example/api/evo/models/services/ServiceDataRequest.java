package com.example.api.evo.models.services;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ServiceDataRequest extends BaseRequest<ServiceDataRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("service_id")
        private int serviceId;
    }
}
