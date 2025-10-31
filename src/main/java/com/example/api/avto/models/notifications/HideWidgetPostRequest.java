package com.example.api.avto.models.notifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public class HideWidgetPostRequest {
    @Getter
    @Builder
    public static class Params {
        @JsonProperty("widget_key")
        private String widgetKey;
    }
}
