package com.example.api.gosuslugi.models.call_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
public class BaseCallServiceRequest {
    private String organization;

    @JsonProperty("service_id")
    private Integer serviceId;

    private String pin;
    private String passport;

    @JsonProperty("web_session")
    private String webSession;
}