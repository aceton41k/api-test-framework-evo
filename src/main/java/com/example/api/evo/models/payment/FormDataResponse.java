package com.example.api.evo.models.payment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.example.api.evo.models.BaseResponse;

import lombok.Getter;

public class FormDataResponse extends BaseResponse<FormDataResponse.Result> {
    @Getter
    public static class Result {
        @JsonProperty("internet_package")
        private InternetPackage internetPackage;
    }

    @Getter
    public static class InternetPackage {
        private List<Item> items;
    }

    @Getter
    public static class Item {
        private String title;
        private String value;

        @JsonProperty("items")
        private List<SubItem> subItems;
    }

    @Getter
    public static class SubItem {
        private String title;
        private String value;
        private Extra extra;
    }

    @Getter
    public static class Extra {
        private Integer amount;
    }
}
