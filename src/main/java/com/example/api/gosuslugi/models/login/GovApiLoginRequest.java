package com.example.api.gosuslugi.models.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class GovApiLoginRequest {
    @JsonProperty("web_session")
    private String webSession;

    @Builder.Default
    private String activate = "1";
}
