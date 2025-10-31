package com.example.api.evo.models.settings;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class SettingsUploadPhotoResponse extends BaseResponse<SettingsUploadPhotoResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("image_url")
        private String imageUrl;
    }
}
