package com.example.api.avto.models.osago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public class OSAGODriverPostRequest {

    @Getter
    @Builder
    public static class Params {
        @JsonProperty("relative_id")
        private Integer relativeId;
        @JsonProperty("passport_series")
        private String passportSeries;
        @JsonProperty("passport_number")
        private Integer passportNumber;
        @JsonProperty("birth_date")
        private String birthDate;
    }

}