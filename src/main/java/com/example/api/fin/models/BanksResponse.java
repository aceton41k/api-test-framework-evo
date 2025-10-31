package com.example.api.fin.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;


@Getter
public class BanksResponse {

    private List<Bank> data;

    @Getter
    public static class Bank {
        @JsonProperty("_id")
        private String id;
        private String name;
        private String logo;
        private String cardOfferEndpoint;
        private String phoneNumber;
    }
}
