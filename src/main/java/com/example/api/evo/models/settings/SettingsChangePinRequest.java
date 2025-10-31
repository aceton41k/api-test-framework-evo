package com.example.api.evo.models.settings;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SettingsChangePinRequest extends BaseRequest<SettingsChangePinRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("current_pin")
        private String currentPin;

        @JsonProperty("new_pin")
        private String newPin;
    }
}
