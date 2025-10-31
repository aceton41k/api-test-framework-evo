package com.example.api.evo.models.debt;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

@Getter
public class GetDebtListResponse extends BaseResponse<List<GetDebtListResponse.Result>> {

    @Getter
    public static class Result {
        @JsonProperty("debt_id")
        private Integer debtId;

        @JsonProperty("phone_number")
        private String phoneNumber;

        private String name;

        @JsonProperty("image_url")
        private String imageUrl;

        private Long datetime;
        private Integer amount;

        @JsonProperty("shedule_datetime")
        private Long sheduleDatetime;

        @JsonProperty("is_own_debt")
        private Boolean isOwnDebt;

        @JsonProperty("is_editable")
        private Boolean isEditable;

        private String type;

        @JsonProperty("chat_id")
        private Integer chatId;

        @JsonProperty("is_closed")
        private Boolean isClosed;

        @JsonProperty("is_closeable")
        private Boolean isCloseable;

        @JsonProperty("is_restoreable")
        private Boolean isRestoreable;

        @JsonProperty("is_deleteable")
        private Boolean isDeleteable;
    }
}

