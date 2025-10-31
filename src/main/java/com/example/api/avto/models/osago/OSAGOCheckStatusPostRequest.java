package com.example.api.avto.models.osago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public class OSAGOCheckStatusPostRequest {
    @Getter
    @Builder
    public static class Params {
        @JsonProperty("check_id")
        private Integer checkId;
    }
}
