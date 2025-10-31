package com.example.api.evo.models.gamification;

import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

public class UserProgressStatusGetResponse extends BaseResponse<UserProgressStatusGetResponse.Result> {
    @Getter
    public static class Result {
        private Integer level;
        private Integer topLevel;
        private String title;
    }
}
