package com.example.api.evo.models.myhome;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MyHomeFromFavoriteRequest extends BaseRequest<MyHomeFromFavoriteRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private String id;

        @JsonProperty("myhome_id")
        private Long myhomeId;
    }
}
