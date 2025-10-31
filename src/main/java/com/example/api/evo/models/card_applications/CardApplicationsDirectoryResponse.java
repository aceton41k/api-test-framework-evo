package com.example.api.evo.models.card_applications;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class CardApplicationsDirectoryResponse extends BaseResponse<CardApplicationsDirectoryResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("card_types")
        private List<CardType> cardTypes;

        private List<Region> regions;
    }

    @Getter
    public static class CardType {
        private String category;
        private String type;
        private String name;
        private String logo;
    }

    @Getter
    public static class Region {
        private Integer code;
        private String name;
        private List<City> cities;
    }

    @Getter
    public static class City {
        private Integer code;
        private String name;
    }
}
