package com.example.api.egov.models.birth;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BirthRequest {
    @Builder.Default
    private String id = "111";
    @Builder.Default
    private String surname = "ESHMATOV";
    @Builder.Default
    private String name = "ALI";
    @Builder.Default
    private String patronym = "VALI O’G’LI";
    @Builder.Default
    private String birth_year = "2018";
    @Builder.Default
    private String type = "1";
}
