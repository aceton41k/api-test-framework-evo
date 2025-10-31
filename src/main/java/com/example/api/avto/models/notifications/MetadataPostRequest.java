package com.example.api.avto.models.notifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public class MetadataPostRequest {
    @Getter
    @Builder
    public static class Params {
        private String key;
        private String value;
    }
}
