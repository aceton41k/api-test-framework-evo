package com.example.api.vitrina.vitrina_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class VitrinaAuthRequest {
    @JsonProperty("merchant_id")
    private String merchantId;
}