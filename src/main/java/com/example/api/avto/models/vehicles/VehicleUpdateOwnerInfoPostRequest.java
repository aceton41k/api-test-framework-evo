package com.example.api.avto.models.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public class VehicleUpdateOwnerInfoPostRequest {

    @Getter
    @Builder
    public static class Params {
        @JsonProperty("region_id")
        private Integer regionId;
        @JsonProperty("district_id")
        private Integer districtId;
    }
}
