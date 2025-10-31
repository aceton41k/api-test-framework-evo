package com.example.api.evo.models.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

import static com.example.api.evo.constants.ServiceData.API_VERSION;

@SuperBuilder
public class FavoriteFromPaymentRequest extends BaseRequest<FavoriteFromPaymentRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("payment_id")
        private Long paymentId;

        @JsonProperty("api_version")
        @Builder.Default
        private Integer apiVersion = API_VERSION;
    }
}
