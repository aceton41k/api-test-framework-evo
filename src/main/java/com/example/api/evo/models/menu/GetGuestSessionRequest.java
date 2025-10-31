package com.example.api.evo.models.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

@SuperBuilder
public class GetGuestSessionRequest extends BaseRequest<GetGuestSessionRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("old_guest_web_session")
        private String oldGuestWebSession;
    }
}
