package com.example.api.avto.models.car_sale;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class CarSaleMarksAndBrandsGetResponse extends BaseModelResponse<List<CarSaleMarksAndBrandsGetResponse.CarBrand>> {

    @Getter
    public static class CarBrand {
        private Integer id;
        private String name;
        private String logo;
        private List<CarModel> models;
    }

    @Getter
    public static class CarModel {
        private Integer id;
        private String name;
        private Integer year;
        private String dimensions;
        private Integer clearance;
        private Integer guarantee;
        private Integer doors;
        private Integer seats;
        @JsonProperty("body_type")
        private String bodyType;
        private List<String> colors;
        private List<String> photos;
        @JsonProperty("top_configuration")
        private TopConfiguration topConfiguration;
        @JsonProperty("min_price")
        private String minPrice;
    }

    @Getter
    public static class TopConfiguration {
        @JsonProperty("engine_type")
        private String engineType;
        private String description;
    }
}
