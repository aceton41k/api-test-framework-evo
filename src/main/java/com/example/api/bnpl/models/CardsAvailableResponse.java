package com.example.api.bnpl.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CardsAvailableResponse {
    private String status;
    private String message;
    private Data data;

    @Getter
    public static class Data {

        @JsonProperty("list_acc_ids")
        private List<Long> listAccIds;

        private String partner;
    }
}
