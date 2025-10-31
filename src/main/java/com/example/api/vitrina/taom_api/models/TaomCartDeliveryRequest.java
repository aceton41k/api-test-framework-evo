package com.example.api.vitrina.taom_api.models;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaomCartDeliveryRequest {
    private final String type; // "delivery" or "pickup"
    private final Double lat; // nullable for pickup
    private final Double lng; // nullable for pickup

    @JsonProperty("delivery_date")
    private final String deliveryDate; // nullable for pickup

    @JsonProperty("delivery_time")
    private final String deliveryTime; // nullable for pickup

    private final String address; // nullable for pickup

    @JsonProperty("house_number")
    private final String houseNumber; // nullable for pickup

    private final String entrance; // nullable for pickup
    private final String floor; // nullable for pickup
    private final String apt; // nullable for pickup

    @JsonProperty("door_code")
    private final String doorCode; // nullable for pickup

    private final String comment; // optional
}