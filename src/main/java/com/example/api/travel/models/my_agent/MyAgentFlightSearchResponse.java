package com.example.api.travel.models.my_agent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyAgentFlightSearchResponse {

    private Boolean success;
    private Integer code;
    private TimeBlock time;
    private DataBlock data;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TimeBlock {
        // Поля не требуются для теста, важно лишь, что объект есть.
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataBlock {
        private SearchBlock search;
        private List<FlightItem> flights;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SearchBlock {
        private List<SearchSegment> segments;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SearchSegment {
        private JsonNode from;
        private JsonNode to;
        private String date;

        public static String iataOf(JsonNode n) {
            if (n == null || n.isNull()) return null;
            if (n.isTextual()) return n.asText();
            if (n.hasNonNull("iata")) return n.get("iata").asText();
            if (n.hasNonNull("code")) return n.get("code").asText();
            return null;
        }
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FlightItem {
        private String id;
        private Integer duration;

        @JsonProperty("segments_count")
        private Integer segmentsCount;

        @JsonProperty("is_baggage")
        private Boolean isBaggage;
    }
}