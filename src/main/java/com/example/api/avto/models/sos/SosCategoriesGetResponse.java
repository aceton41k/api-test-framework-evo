package com.example.api.avto.models.sos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class SosCategoriesGetResponse extends BaseModelResponse<List<SosCategoriesGetResponse.VehicleType>> {

    @Getter
    public static class VehicleType {
        @JsonProperty("id")
        private Integer id;

        @JsonProperty("name")
        private String name;
    }
}
