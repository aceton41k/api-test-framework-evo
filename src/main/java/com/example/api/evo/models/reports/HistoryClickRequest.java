package com.example.api.evo.models.reports;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

@SuperBuilder
public class HistoryClickRequest extends BaseRequest<HistoryClickRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("date_end")
        private Long dateEnd;

        @JsonProperty("date_start")
        private Long dateStart;

        @JsonProperty("page_number")
        @Builder.Default
        private Integer pageNumber = 1;

        @JsonProperty("page_size")
        @Builder.Default
        private Integer pageSize = 10;
    }
}
