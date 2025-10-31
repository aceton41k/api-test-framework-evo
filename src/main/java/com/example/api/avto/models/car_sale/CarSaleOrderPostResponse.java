package com.example.api.avto.models.car_sale;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

@Getter
public class CarSaleOrderPostResponse extends BaseModelResponse<CarSaleOrderPostResponse.OrderData> {
    @Getter
    public static class OrderData {
        @JsonProperty("order_id")
        private String orderId;
        private String description;

    }
}
