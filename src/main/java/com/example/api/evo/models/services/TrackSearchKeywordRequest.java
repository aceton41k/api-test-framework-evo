package com.example.api.evo.models.services;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class TrackSearchKeywordRequest extends BaseRequest<TrackSearchKeywordRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private String keyword;
        private Integer type;
        private Integer isFound;
    }
}
