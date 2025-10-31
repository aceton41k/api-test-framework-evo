package com.example.api.avto.models.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public class VehiclesPostRequest {

    @Getter
    @Builder
    public static class Params {
        private String pinfl;
        @JsonProperty("tech_passport_series")
        private String techPassportSeries;
        @JsonProperty("tech_passport_number")
        private String techPassportNumber;
    }

}
