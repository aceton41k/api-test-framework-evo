package com.example.api.avto.models.car_sale;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class CarSaleConfigsOffersGetResponse extends BaseModelResponse<List<CarSaleConfigsOffersGetResponse.CarOffer>> {
    @Getter
    public static class CarOffer {
        private Integer id;
        private Long price;
        @JsonProperty("official_dealer")
        private Integer officialDealer;
        @JsonProperty("loan_availability")
        private Integer loanAvailability;
        private Dealership dealership;
        private Configuration configuration;
    }

    @Getter
    public static class Dealership {
        private Integer id;
        private String name;
        @JsonProperty("phone_number")
        private String phoneNumber;
        private String address;
        private String lat;
        private String lon;
        @JsonProperty("working_hours_start")
        private String workingHoursStart;
        @JsonProperty("working_hours_end")
        private String workingHoursEnd;
        @JsonProperty("avg_response_time")
        private Integer avgResponseTime;
    }

    @Getter
    public static class Configuration {
        private Integer id;
        private String name;
        @JsonProperty("engine_type")
        private String engineType;
        @JsonProperty("engine_capacity")
        private String engineCapacity;
        @JsonProperty("boost_type")
        private String boostType;
        @JsonProperty("power_hp")
        private Integer powerHp;
        @JsonProperty("power_kw")
        private Integer powerKw;
        private String acceleration;
        @JsonProperty("trunk_capacity")
        private Integer trunkCapacity;
        @JsonProperty("drive_type")
        private String driveType;
        private String transmission;
        @JsonProperty("fuel_tank_capacity")
        private Integer fuelTankCapacity;
        @JsonProperty("fuel_consumption")
        private String fuelConsumption;
        private Integer range;
        @JsonProperty("wheel_size")
        private Integer wheelSize;
        @JsonProperty("wheel_type")
        private String wheelType;
        private Integer airbags;
        private Integer abs;
        private Integer esp;
        private Integer sunroof;
        private List<String> features;
    }
}
