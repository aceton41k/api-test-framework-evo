package com.example.api.evo.models.transfer_p2p;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseRequest;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class TransferConfirmationUpdateRequest extends BaseRequest<TransferConfirmationUpdateRequest.Params> {
    @Getter
    @SuperBuilder
    public static class Params {
        @JsonProperty("p2p_otp_enabled")
        private Boolean p2p_otp_enabled;
    }
}
