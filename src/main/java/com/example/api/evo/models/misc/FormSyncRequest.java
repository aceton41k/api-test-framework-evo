package com.example.api.evo.models.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

import static com.example.api.evo.constants.ServiceData.API_VERSION;

@SuperBuilder
public class FormSyncRequest extends BaseRequest<FormSyncRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("api_version")
        @Builder.Default
        private Integer apiVersion = API_VERSION;

        private Parameters parameters;

        @Getter
        @SuperBuilder
        public static class Parameters {
            @JsonProperty("service_id")
            private Long serviceId;

            private Integer version;
        }
    }
}
