package com.example.api.egov.models.death;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeathCertRequest {
    @Builder.Default
    private String id = "111";
    @Builder.Default
    @JsonProperty("cert_series")
    private String certSeries = "I-TN";
    @Builder.Default
    @JsonProperty("cert_number")
    private String certNumber = "1234567";

}
