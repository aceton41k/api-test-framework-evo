package com.example.api.evo.models.chat;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class ChatListResponse extends BaseResponse<List<ChatListResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("chat_id")
        private Long chatId;

        private String name;

        @JsonProperty("profile_name")
        private String profileName;

        private String participant;

        @JsonProperty("last_message_id")
        private String lastMessageId;

        @JsonProperty("last_message_date")
        private Long lastMessageDate;

        private String type;
        private String image;

        @JsonProperty("unread_count")
        private Integer unreadCount;

        @JsonProperty("card_number_hash")
        private String cardNumberHash;

        private Boolean pinned;
        private Boolean renamed;

        @JsonProperty("is_identified")
        private Boolean isIdentified;

        @JsonProperty("is_premium")
        private Boolean isPremium;
    }
}
