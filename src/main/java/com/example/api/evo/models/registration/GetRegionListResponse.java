package com.example.api.evo.models.registration;

import java.util.List;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class GetRegionListResponse extends BaseResponse<List<GetRegionListResponse.Result>> {
    @Getter
    public static class Result {
        private String code;
        private String name;
    }
}
