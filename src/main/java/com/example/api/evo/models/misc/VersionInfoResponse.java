package com.example.api.evo.models.misc;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class VersionInfoResponse extends BaseResponse<VersionInfoResponse.Result> {
    @Getter
    public static class Result {
        private Integer version;
        private Integer level;
    }
}
