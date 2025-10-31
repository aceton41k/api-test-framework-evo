package com.example.api.avto.models.references;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class TrafficLinesGetResponse extends BaseModelResponse<List<TrafficLinesGetResponse.TrafficFineCategory>> {
    @Getter
    public static class TrafficFineCategory {
        private Integer id;
        private String name;
        @JsonProperty("traffic_fines")
        private List<TrafficFine> trafficFines;
    }

    @Getter
    public static class TrafficFine {
        private Integer id;
        private String article;
        private String part;
        private String keywords;
        private String link;
        @JsonProperty("price_without_discount")
        private String priceWithoutDiscount;
        @JsonProperty("price_with_discount30")
        private String priceWithDiscount30;
        @JsonProperty("price_with_discount50")
        private String priceWithDiscount50;
        @JsonProperty("price2_without_discount")
        private String price2WithoutDiscount;
        @JsonProperty("price2_with_discount30")
        private String price2WithDiscount30;
        @JsonProperty("price2_with_discount50")
        private String price2WithDiscount50;
        private String description;
        private String threat;
    }
}
