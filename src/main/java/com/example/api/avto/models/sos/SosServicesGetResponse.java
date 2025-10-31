package com.example.api.avto.models.sos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class SosServicesGetResponse extends BaseModelResponse<List<SosServicesGetResponse.Service>> {

    @Getter
    public static class Service {
        @JsonProperty("id")
        private Integer id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("price")
        private Integer price;

        @JsonProperty("description")
        private String description;
    }
}
