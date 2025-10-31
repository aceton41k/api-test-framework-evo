package com.example.api.evo.models.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

@SuperBuilder
public class IdentificationRequest extends BaseRequest<IdentificationRequest.Params> {

    @Getter
    @Builder
    public static class Params {
        @JsonProperty("pass_data")
        private String passData;

        @JsonProperty("birth_date")
        private String birthDate;

        private String pinfl;

        @JsonProperty("is_resident")
        private Boolean isResident;

        @JsonProperty("photo_from_camera")
        private PhotoFromCamera photoFromCamera;
    }
}
