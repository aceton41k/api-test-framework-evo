package com.example.api.egov.models.death;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class DeathResponse {
    private String id;

    @JsonProperty("result_code")
    private int resultCode;

    @JsonProperty("result_message")
    private String resultMessage;

    @JsonProperty("Items")
    private List<Item> items;

    @Getter
    public static class Item {
        @JsonProperty("doc_date")
        private String docDate;

        private int branch;

        @JsonProperty("cert_death_date")
        private String certDeathDate;

        private String pnfl;
        private String surname;
        private String name;
        private String patronym;

        @JsonProperty("birth_date")
        private String birthDate;

        @JsonProperty("doc_num")
        private String docNum;

        @JsonProperty("cert_series")
        private String certSeries;

        @JsonProperty("cert_number")
        private String certNumber;

        @JsonProperty("death_place")
        private String deathPlace;

        @JsonProperty("birth_place")
        private String birthPlace;

        @JsonProperty("citizen_country")
        private String citizenCountry;

        @JsonProperty("gender_code")
        private int genderCode;

        @JsonProperty("death_date")
        private String deathDate;
    }
}
