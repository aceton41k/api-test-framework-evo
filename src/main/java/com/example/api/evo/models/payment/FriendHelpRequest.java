package com.example.api.evo.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

@SuperBuilder
public class FriendHelpRequest extends BaseRequest<FriendHelpRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private Data data;

        @JsonProperty("service_id")
        private Long serviceId;

        @JsonProperty("phone_number")
        private String phoneNumber;

        private Parameters parameters;
    }

    @Getter
    @SuperBuilder
    public static class Data {}

    @Getter
    @SuperBuilder
    public static class Parameters {
        private String amount;

        @JsonProperty("phone_num")
        private String phoneNum;
    }
}
