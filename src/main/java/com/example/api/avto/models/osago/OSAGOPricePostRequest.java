package com.example.api.avto.models.osago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public class OSAGOPricePostRequest {

    @Getter
    @Builder
    public static class Params {
        @JsonProperty("vehicle_id")
        private Integer vehicleId;
    }
}
