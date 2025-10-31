package com.example.api.vitrina.vitrina_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VitrinaAuthResponse {
    private String id;
    private String name;

    @JsonProperty("merchant_id")
    private String merchantId;

    @JsonProperty("merchant_user_id")
    private String merchantUserId;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String token;
    private String logo;
}
