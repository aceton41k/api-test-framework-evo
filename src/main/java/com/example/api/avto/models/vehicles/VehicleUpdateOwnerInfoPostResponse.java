package com.example.api.avto.models.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

@Getter
public class VehicleUpdateOwnerInfoPostResponse extends BaseModelResponse<VehicleUpdateOwnerInfoPostResponse.DriverData> {

    @Getter
    public static class DriverData {
        private Integer id;
        private Boolean fy;
        private String name;
        @JsonProperty("region_id")
        private Integer regionId;
        @JsonProperty("district_id")
        private Integer districtId;
        @JsonProperty("driver_hash")
        private String driverHash;
    }
}
