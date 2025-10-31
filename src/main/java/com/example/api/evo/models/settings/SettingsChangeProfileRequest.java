package com.example.api.evo.models.settings;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SettingsChangeProfileRequest extends BaseRequest<SettingsChangeProfileRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private String name;
        private String surname;
        private String patronym;
        private Long birthdate;

        @JsonProperty("region_code")
        private String regionCode;

        private String gender;
    }
}
