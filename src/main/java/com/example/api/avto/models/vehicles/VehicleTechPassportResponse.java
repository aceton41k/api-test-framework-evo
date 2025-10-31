package com.example.api.avto.models.vehicles;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.time.LocalDate;

@Getter
public class VehicleTechPassportResponse extends BaseModelResponse<VehicleTechPassportResponse.Data> {
    @Getter
    public static class Data {
        @JsonProperty("plate_number")
        private String plateNumber;

        private String model;
        private String color;

        @JsonProperty("tech_passport_issue_date")
        @JsonFormat(pattern = "dd.MM.yyyy")
        private LocalDate techPassportIssueDate;

        @JsonProperty("tech_passport_issued_by")
        private String techPassportIssuedBy;

        @JsonProperty("tech_passport_series")
        private String techPassportSeries;

        @JsonProperty("tech_passport_number")
        private String techPassportNumber;

        private Integer year;

        @JsonProperty("vehicle_type_name")
        private String vehicleTypeName;

        @JsonProperty("body_number")
        private String bodyNumber;

        @JsonProperty("frame_number")
        private String frameNumber;

        @JsonProperty("full_weight")
        private String fullWeight;

        @JsonProperty("empty_weight")
        private String emptyWeight;

        @JsonProperty("engine_number")
        private String engineNumber;

        private Integer power;

        @JsonProperty("fuel_type_name")
        private String fuelTypeName;
        private Integer seats;
        private Integer stands;
        private Owner owner;
    }

    @Getter
    public static class Owner {
        private String name;

        @JsonProperty("pinfl_inn")
        private String pinflInn;

        private String region;
        private String district;
    }
}
