package com.example.api.evo.models.premium_subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.math.BigDecimal;
import java.util.List;

public class PremiumTariffListResponse extends BaseResponse<List<PremiumTariffListResponse.Result>> {
    @Getter
    public static class Result {
        private Integer id;
        private String name;
        private BigDecimal price;

        @JsonProperty("full_price")
        private BigDecimal fullPrice;

        @JsonProperty("service_id")
        private Integer serviceId;

        private String barcode;
        private HeaderDto header;
        private List<DetailDto> details;
    }

    @Getter
    public static class HeaderDto {
        private String description;
        private String price;
    }

    @Getter
    public static class DetailDto {

        private String title;
        private String text;
        private String icon;
        private String url;

        @JsonProperty("analytics_variable")
        private String analyticsVariable;
    }
}