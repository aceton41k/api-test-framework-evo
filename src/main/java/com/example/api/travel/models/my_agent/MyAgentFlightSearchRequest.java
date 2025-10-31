package com.example.api.travel.models.my_agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class MyAgentFlightSearchRequest {

    @Builder.Default
    @JsonProperty("segments[0][from]")
    private final String from = "TAS";

    @Builder.Default
    @JsonProperty("segments[0][to]")
    private final String to = "MOW";

    @Builder.Default
    @JsonProperty("segments[0][date]")
    private final String date = "2026-01-01";

    @Builder.Default
    private final String adt = "1";

    @Builder.Default
    private final String chd = "0";

    @Builder.Default
    private final String inf = "0";

    @Builder.Default
    @JsonProperty("class")
    private final String clazz = "a";

    @Builder.Default
    private final String countryCode = "RU";

    @Builder.Default
    private final String lang = "ru";

    public Map<String, String> toMap() {
        return Map.of(
                "segments[0][from]", from,
                "segments[0][to]", to,
                "segments[0][date]", date,
                "adt", adt,
                "chd", chd,
                "inf", inf,
                "class", clazz,
                "countryCode", countryCode,
                "lang", lang
        );
    }

    public static Map<String, String> defaultFlightSearchParams() {
        return MyAgentFlightSearchRequest.builder().build().toMap();
    }
}
