package com.example.api.travel.models.unit_travel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnitTravelFlightSearchResponse {
    private String flightId;
    private BigDecimal totalPrice;
    private List<List<Leg>> segments;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Leg {
        @JsonProperty("DepDateTime") private String depDateTime;
        @JsonProperty("DepAirpIATA") private String depAirpIATA;
        @JsonProperty("ArrAirpIATA") private String arrAirpIATA;
    }
}