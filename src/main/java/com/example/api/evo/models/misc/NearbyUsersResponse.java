package com.example.api.evo.models.misc;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class NearbyUsersResponse extends BaseResponse<List<NearbyUsersResponse.Result>> {
    @Getter
    public static class Result {
        @JsonProperty("client_id")
        private Integer clientId;

        private String name;

        @JsonProperty("phone_number")
        private String phoneNumber;

        @JsonProperty("image_url")
        private String imageUrl;
    }
}
