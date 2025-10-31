package com.example.api.evo.models.click_pass;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class TimeSynchronizationRequest extends BaseRequest<TimeSynchronizationRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private Long datetime;
    }
}
