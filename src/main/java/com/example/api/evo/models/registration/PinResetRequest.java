package com.example.api.evo.models.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class PinResetRequest extends BaseRequest<PinResetRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("sms_code")
        private String smsCode;
    }
}
