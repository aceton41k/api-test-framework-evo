package com.example.api.avto.models.notifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class MetadataDeleteResponse extends BaseModelResponse<MetadataPostResponse.Metadata> {

    @Getter
    public static class Metadata {
        @JsonProperty("main_onboarding")
        private List<String> mainOnboarding;
        @JsonProperty("empty_onboard")
        private Boolean emptyOnboard;
    }
}
