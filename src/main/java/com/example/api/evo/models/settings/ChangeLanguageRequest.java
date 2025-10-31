package com.example.api.evo.models.settings;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ChangeLanguageRequest extends BaseRequest<ChangeLanguageRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        private String language;
    }
}
