package com.example.api.egov.models.birth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class BirthResponse {
    private String id;

    @JsonProperty("result_code")
    private int resultCode;

    @JsonProperty("result_message")
    private String resultMessage;

    @JsonProperty("Items")
    private List<Item> items;

    @Getter
    public static class Item {
        private String pnfl;
        private String surname;
        private String name;
        private String patronym;

        @JsonProperty("doc_date")
        private String docDate;

        private int branch;

        @JsonProperty("cert_birth_date")
        private String certBirthDate;

        @JsonProperty("birth_date")
        private String birthDate;

        @JsonProperty("gender_code")
        private int genderCode;

        @JsonProperty("doc_num")
        private String docNum;

        @JsonProperty("cert_series")
        private String certSeries;

        @JsonProperty("cert_number")
        private String certNumber;
    }
}
