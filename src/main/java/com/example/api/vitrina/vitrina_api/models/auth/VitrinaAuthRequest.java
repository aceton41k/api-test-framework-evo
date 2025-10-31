package com.example.api.vitrina.vitrina_api.models.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VitrinaAuthRequest {
    @JsonProperty("merchant_id")
    private String merchantId;
}
