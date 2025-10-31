package com.example.api.evo.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

import java.util.List;

import static com.example.api.evo.constants.ServiceData.API_VERSION;

@SuperBuilder
public class FormDataRequest extends BaseRequest<FormDataRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("service_id")
        private Integer serviceId;

        private Integer step;
        private List<String> params;

        @JsonProperty("api_version")
        @Builder.Default
        private Integer apiVersion = API_VERSION;
    }
}
