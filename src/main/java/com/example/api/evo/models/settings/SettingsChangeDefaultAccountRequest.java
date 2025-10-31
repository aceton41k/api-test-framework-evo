package com.example.api.evo.models.settings;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SettingsChangeDefaultAccountRequest extends BaseRequest<SettingsChangeDefaultAccountRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("account_id")
        private Long accountId;
    }
}
