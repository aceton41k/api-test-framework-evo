package com.example.api.evo.models.big_cashback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

import static com.example.api.evo.constants.ServiceData.API_VERSION;

@SuperBuilder
public class BigCashbackServiceListRequest extends BaseRequest<BigCashbackServiceListRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("page_number")
        @Builder.Default
        private Integer pageNumber = 1;

        @JsonProperty("api_version")
        @Builder.Default
        private Integer apiVersion = API_VERSION;
    }
}
