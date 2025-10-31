package com.example.api.avto.models.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public class VehicleColorUpdatePostRequest {
    @Getter
    @Builder
    public static class Params {
        @JsonProperty("vehicle_photo_id")
        private Integer vehiclePhotoId;
    }
}
