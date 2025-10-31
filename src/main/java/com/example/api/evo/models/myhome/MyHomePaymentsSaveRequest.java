package com.example.api.evo.models.myhome;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

import static com.example.api.evo.constants.ServiceData.API_VERSION;

@SuperBuilder
public class MyHomePaymentsSaveRequest extends BaseRequest<MyHomePaymentsSaveRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private DataObject data;

        @JsonProperty("service_id")
        private Integer serviceId;

        @JsonProperty("api_version")
        @Builder.Default
        private Integer apiVersion = API_VERSION;

        @JsonProperty("myhome_id")
        private Long myHomeId;

        private Parameters parameters;
    }

    @Getter
    public static class DataObject {}

    @Getter
    @SuperBuilder
    public static class Parameters {
        private String amount;
        @Builder.Default
        private String balance = "";

        @JsonProperty("phone_num")
        private String phoneNum;
    }
}
