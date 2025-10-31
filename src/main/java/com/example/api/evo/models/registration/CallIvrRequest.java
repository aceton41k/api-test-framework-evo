package com.example.api.evo.models.registration;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CallIvrRequest extends BaseRequest<CallIvrRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private Integer type;
    }
}
