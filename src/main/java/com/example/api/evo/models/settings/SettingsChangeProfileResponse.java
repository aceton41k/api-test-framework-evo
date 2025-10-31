package com.example.api.evo.models.settings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

public class SettingsChangeProfileResponse extends BaseResponse<SettingsChangeProfileResponse.Result> {
    @Getter
    public static class Result {
        private String name;
        private String surname;
        private String patronym;
        private String gender;
        private Long birthdate;

        @JsonProperty("region_code")
        private String regionCode;

        @JsonProperty("profile_editable")
        private Boolean profileEditable;
    }
}
