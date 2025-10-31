package com.example.api.avto.models.osago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

@Getter
public class OSAGOKbmPricePostResponse extends BaseModelResponse<OSAGOKbmPricePostResponse.InsuranceData> {

    @Getter
    public static class InsuranceData {
        private Integer price;

        @JsonProperty("insured_amount")
        private Integer insuredAmount;

        private Integer cashback;

        @JsonProperty("cashback_price")
        private Integer cashbackPrice;

        @JsonProperty("cashback_limit")
        private Integer cashbackLimit;

        @JsonProperty("checkout_id")
        private String checkoutId;
    }
}
