package com.example.api.evo.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class RequestWithoutParams extends BaseRequest<RequestWithoutParams.Params> {
    @Getter
    @SuperBuilder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Params {}
}
