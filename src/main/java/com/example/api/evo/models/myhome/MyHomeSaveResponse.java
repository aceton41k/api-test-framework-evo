package com.example.api.evo.models.myhome;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

public class MyHomeSaveResponse extends BaseResponse<MyHomeSaveResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("myhome_id")
        private Long myhomeId;
    }
}