package com.example.api.egov.models.passport_info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PassportInfoPinSerialRequest {
    @JsonProperty("transaction_id")
    @Builder.Default
    private Integer transactionId = 3;

    @JsonProperty("is_consent")
    @Builder.Default
    private String isConsent = "Y";

    @JsonProperty("sender_pinfl")
    @Builder.Default
    private String senderPinfl = "12345678901234";

    @JsonProperty("langId")
    @Builder.Default
    private Integer langId = 1;

    @JsonProperty("document")
    @Builder.Default
    private String document = "AA1111111";

    @Builder.Default
    private String pinpp = "12345678901234";

    @JsonProperty("is_photo")
    @Builder.Default
    private String isPhoto = "Y";

    @JsonProperty("Sender")
    @Builder.Default
    private String sender = "P";

}
