package com.example.api.evo.models.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

public class GetGuestSessionResponse extends BaseResponse<GetGuestSessionResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("guest_web_session")
        private String guestWebSession;

        @JsonProperty("guest_web_session_expiry")
        private Integer guestWebSessionExpiry;
    }
}
