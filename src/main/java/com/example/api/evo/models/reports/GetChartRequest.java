package com.example.api.evo.models.reports;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class GetChartRequest extends BaseRequest<GetChartRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("date_end")
        private Long dateEnd;

        @JsonProperty("date_start")
        private Long dateStart;
    }
}
