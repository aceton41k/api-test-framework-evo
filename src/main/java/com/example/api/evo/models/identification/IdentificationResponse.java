package com.example.api.evo.models.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.evo.models.BaseResponse;

@Getter
public class IdentificationResponse extends BaseResponse<IdentificationResponse.Result> {

    @Getter
    public static class Result {
        @JsonProperty("identification_status")
        private Long identificationStatus;

        @JsonProperty("verify_type")
        private String verifyType;

        @JsonProperty("result_code")
        private Integer resultCode;

        @JsonProperty("result_note")
        private String resultNote;

        @JsonProperty("event_id")
        private String eventId;

        private Integer attempts;
    }
}
