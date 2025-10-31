package com.example.api.avto.models.car_sale;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class CarSaleConfigurationsGetResponse extends BaseModelResponse<List<CarSaleConfigurationsGetResponse.VehicleConfig>> {

    @Getter
    public static class VehicleConfig {
        private Integer id;
        private String name;
        @JsonProperty("min_price")
        private String minPrice;
        private List<Spec> specs;
        private List<String> features;
    }

    @Getter
    public static class Spec {
        private String title;
        private String value;
        private List<String> options;
        private Boolean hidden;
    }
}
