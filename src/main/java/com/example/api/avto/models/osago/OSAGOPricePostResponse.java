package com.example.api.avto.models.osago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class OSAGOPricePostResponse extends BaseModelResponse<List<OSAGOPricePostResponse.DataItem>> {

    @Getter
    public static class DataItem {
        @JsonProperty("price")
        private Integer price;
        @JsonProperty("insured_amount")
        private Integer insuredAmount;
        @JsonProperty("cashback")
        private Integer cashback;
        @JsonProperty("cashback_price")
        private Integer cashbackPrice;
        @JsonProperty("cashback_limit")
        private Integer cashbackLimit;
    }
}
