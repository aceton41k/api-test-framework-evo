package com.example.api.evo.models.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import com.example.api.evo.models.BaseRequest;

@SuperBuilder
public class IdentificationVerifyRequest extends BaseRequest<IdentificationVerifyRequest.Params> {

    @Builder
    public static class Params {
        private String vendor;

        @JsonProperty("photo_from_camera")
        private PhotoFromCamera photoFromCamera;
    }
}