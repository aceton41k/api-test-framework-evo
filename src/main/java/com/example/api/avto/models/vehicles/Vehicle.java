package com.example.api.avto.models.vehicles;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class Vehicle {
    private Integer id;
    private String group;
    private Integer warnings;

    @JsonProperty("plate_number")
    private String plateNumber;

    @JsonProperty("formatted_plate_number")
    private String formattedPlateNumber;

    private String photo;
    private String model;

    @JsonProperty("vehicle_type_id")
    private String vehicleTypeId;

    @JsonProperty("vehicle_type_name")
    private String vehicleTypeName;

    @JsonProperty("use_territory")
    private String useTerritory;

    @JsonProperty("use_territory_name")
    private String useTerritoryName;

    @JsonProperty("region_id")
    private Integer regionId;

    private String color;

    @JsonProperty("vehicle_color_id")
    private Integer vehicleColorId;

    private Integer power;
    private Integer year;

    @JsonProperty("tech_passport_series")
    private String techPassportSeries;

    @JsonProperty("tech_passport_number")
    private String techPassportNumber;

    @JsonProperty("tech_passport_issue_date")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate techPassportIssueDate;

    @JsonProperty("tuning_permit")
    private Boolean tuningPermit;

    @JsonProperty("tuning_given_date")
    private String tuningGivenDate;

    @JsonProperty("tuning_issue_date")
    private String tuningIssueDate;

    @JsonProperty("tuning_left_days")
    private Integer tuningLeftDays;

    @JsonProperty("tuning_left_percent")
    private Integer tuningLeftPercent;

    @JsonProperty("is_default")
    private Boolean isDefault;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("is_gas_insurance_available")
    private Boolean isGasInsuranceAvailable;

    @JsonProperty("is_electric")
    private Boolean isElectric;

    @JsonProperty("sos_category_id")
    private Integer sosCategoryId;

    private Informers informers;

    private Owner owner;

    @JsonProperty("insurance_policies")
    private List<InsurancePolicy> insurancePolicies;

    @JsonProperty("photo_v2_light")
    private String photoV2Light;

    @JsonProperty("photo_v2_dark")
    private String photoV2Dark;

    @JsonProperty("photo_version")
    private Integer photoVersion;

    @Getter
    public static class Informers {
        private Osago osago;
        private Tinting tinting;

        @Getter
        public static class Osago {
            private String title;

            @JsonProperty("remaining_percent")
            private Integer remainingPercent;

            private String color;
        }

        @Getter
        public static class Tinting {
            private String title;

            @JsonProperty("remaining_percent")
            private Integer remainingPercent;

            private String color;
        }
    }
}