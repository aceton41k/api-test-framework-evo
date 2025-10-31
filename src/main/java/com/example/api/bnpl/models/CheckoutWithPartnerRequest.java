package com.example.api.bnpl.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CheckoutWithPartnerRequest {
    private Integer merchantServiceId;
    private String merchantServiceName;
    @Builder.Default
    private String basket = "{\"s\":\"a\"}";
    @Builder.Default
    private String geo = "{\"latitude\":\"12.741895\",\"longtitude\":\"-12.989308\"}";
    @Builder.Default
    private Integer clientId = 2459517;
    @Builder.Default
    private Integer totalAmount = 10000;
    @Builder.Default
    private String transactionType = "indoor";
    @Builder.Default
    private String deviceId = "123456789";
    private String bnplPartner;
}
