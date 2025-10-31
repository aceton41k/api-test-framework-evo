package com.example.api.evo.models.gamification;

import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

import java.util.List;

public class UserProgressPrivilegesGetResponse extends BaseResponse<List<UserProgressPrivilegesGetResponse.Result>> {
    @Getter
    public static class Result {
        private Integer level;
        private String title;
        private String description;
        private List<Privilege> privileges;
        private Action action;
    }

    @Getter
    public static class Privilege {
        private String text;
        private String icon;
    }

    @Getter
    public static class Action {
        private String text;
        private Boolean isEnable;
        private String deeplink;
    }
}
