package com.example.api.avto.models.car_sale;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public class CarSaleOrderPostRequest {
    @Getter
    @Builder
    public static class Params {
        @JsonProperty("offer_id")
        private Integer offerId;

    }
}
