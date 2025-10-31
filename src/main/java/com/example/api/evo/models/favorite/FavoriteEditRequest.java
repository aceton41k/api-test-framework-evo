package com.example.api.evo.models.favorite;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class FavoriteEditRequest extends BaseRequest<FavoriteEditRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private String id;

        @JsonProperty("api_version")
        private Integer apiVersion;

        private Parameters parameters;
    }

    @Getter
    @SuperBuilder
    public static class Parameters {
        @JsonProperty("favorite_name")
        private String favoriteName;

        @JsonProperty("phone_num")
        private String phoneNum;

        private Integer amount;
    }
}
