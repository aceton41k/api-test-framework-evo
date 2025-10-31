package com.example.api.travel.models.unit_travel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class UnitTravelFlightSearchRequest {

    @Builder.Default
    @JsonProperty("fly_from")
    private final String flyFrom = "TAS";

    @Builder.Default
    @JsonProperty("fly_to")
    private final String flyTo = "BHK";

    @Builder.Default
    @JsonProperty("date_from")
    private final String dateFrom = "20.01.2026";

    @Builder.Default
    private final String adults = "1";

    @Builder.Default
    private final String children = "0";

    @Builder.Default
    private final String infants = "0";

    @Builder.Default
    @JsonProperty("hasFareFamilies")
    private final String hasFareFamilies = "true";

    @Builder.Default
    private final String lang = "ru";

    @Builder.Default
    private final String currency = "UZS";

    public Map<String, String> toMap() {
        return Map.of(
                "fly_from", flyFrom,
                "fly_to", flyTo,
                "date_from", dateFrom,
                "adults", adults,
                "children", children,
                "infants", infants,
                "hasFareFamilies", hasFareFamilies,
                "lang", lang,
                "currency", currency
        );
    }

    public static Map<String, String> defaultFlightSearchParams() {
        return UnitTravelFlightSearchRequest.builder().build().toMap();
    }
}