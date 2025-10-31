package com.example.api.evo.models.big_cashback;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;
import com.example.api.evo.models.Location;

import static com.example.api.evo.constants.ServiceData.API_VERSION;

@SuperBuilder
public class BigCashbackIndoorServiceListRequest extends BaseRequest<BigCashbackIndoorServiceListRequest.Params> {
    @Getter
    @SuperBuilder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Params {
        private Location location;
        private String search;

        @JsonProperty("page_number")
        @Builder.Default
        private Integer pageNumber = 1;

        @JsonProperty("api_version")
        @Builder.Default
        private Integer apiVersion = API_VERSION;

        @JsonProperty("category_id")
        @Builder.Default
        private Integer categoryId = 1;

        private String sort;
        @JsonProperty("batch_size")
        private Integer batchSize;
    }
}
