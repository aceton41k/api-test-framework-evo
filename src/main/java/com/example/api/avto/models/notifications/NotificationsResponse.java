package com.example.api.avto.models.notifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class NotificationsResponse extends BaseModelResponse<List<NotificationsResponse.NotificationData>> {
    @Getter
    public static class NotificationData {
        private Integer id;
        private String title;
        private String body;
        private String url;
        @JsonProperty("internal_url")
        private String internalUrl;
        @JsonProperty("is_opened")
        private Boolean isOpened;
        @JsonProperty("created_at")
        private String createdAt;
        private String dtime;
    }
}
