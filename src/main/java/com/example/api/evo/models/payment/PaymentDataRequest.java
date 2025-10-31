package com.example.api.evo.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

import static com.example.api.evo.constants.ServiceData.API_VERSION;

@SuperBuilder
public class PaymentDataRequest extends BaseRequest<PaymentDataRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("service_id")
        private Long serviceId;

        private Integer step;

        @JsonProperty("api_version")
        @Builder.Default
        private Integer apiVersion = API_VERSION;
    }
}
