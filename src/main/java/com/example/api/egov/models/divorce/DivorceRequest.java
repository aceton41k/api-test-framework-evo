package com.example.api.egov.models.divorce;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DivorceRequest {
    @Builder.Default
    private String id = "111";
    @Builder.Default
    private String surname = "ESHMATOV";
    @Builder.Default
    private String name = "ALI";
    @Builder.Default
    private String patronym = "VALI O’G’LI";
    @Builder.Default
    private String birth_year = "1988";
    @Builder.Default
    private String type = "2";
}
