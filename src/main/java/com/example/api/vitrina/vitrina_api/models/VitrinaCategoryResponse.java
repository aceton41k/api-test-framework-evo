package com.example.api.vitrina.vitrina_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class VitrinaCategoryResponse {
    private String id;
    private String name;

    @JsonProperty("merchant_id")
    private String merchantId;

    @JsonProperty("service_id")
    private String serviceId;

    private Boolean status;
}
