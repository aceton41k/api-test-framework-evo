package com.example.api.avto.models.osago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public class OSAGOPKbmPricePostRequest {

    @Getter
    @Builder
    public static class Params {
        @JsonProperty("vehicle_id")
        private Integer vehicleId;
        @JsonProperty("insurance_company_id")
        private Integer insuranceCompanyId;
        @JsonProperty("driver_limit")
        private Integer driverLimit;
        private List<String> drivers;
    }
}
