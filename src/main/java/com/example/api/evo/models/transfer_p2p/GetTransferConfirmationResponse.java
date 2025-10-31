package com.example.api.evo.models.transfer_p2p;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class GetTransferConfirmationResponse extends BaseResponse<GetTransferConfirmationResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("p2p_otp_enabled")
        private boolean p2pOtpEnabled;
    }
}
