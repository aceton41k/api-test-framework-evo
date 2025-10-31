package com.example.api.evo.models.myhome;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MyHomeDeleteRequest extends BaseRequest<MyHomeDeleteRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("myhome_id")
        private Long myhomeId;
    }
}
