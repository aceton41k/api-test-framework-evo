package com.example.api.avto.models.references;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public class MapPostRequest {

    @Getter
    @Builder
    public static class Params {
        private Integer type;
        @JsonProperty("vehicle_id")
        private Integer vehicleId;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String lat;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String lng;
        private List<Integer> tags;
    }

}
