package com.example.api.evo.models.myhome;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

import static com.example.api.evo.constants.ServiceData.API_VERSION;

@SuperBuilder
public class MyHomePaymentDataRequest extends BaseRequest<MyHomePaymentDataRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("api_version")
        @Builder.Default
        private Integer apiVersion = API_VERSION;

        @JsonProperty("myhome_id")
        private Long myHomeId;

        private Long id;
    }
}
