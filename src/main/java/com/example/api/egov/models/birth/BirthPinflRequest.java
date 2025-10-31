package com.example.api.egov.models.birth;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BirthPinflRequest {
    @Builder.Default
    private String id = "111";
    @Builder.Default
    private String pin = "12345678901234";

}
