package com.example.api.egov.models.marriage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class MarriageResponse {
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

        @JsonProperty("cert_date")
        private String certDate;

        @JsonProperty("h_birth_day")
        private String hBirthDay;

        @JsonProperty("w_birth_day")
        private String wBirthDay;

        @JsonProperty("doc_num")
        private String docNum;

        @JsonProperty("cert_series")
        private String certSeries;

        @JsonProperty("cert_number")
        private String certNumber;

        @JsonProperty("h_family")
        private String hFamily;

        @JsonProperty("h_family_after")
        private String hFamilyAfter;

        @JsonProperty("h_first_name")
        private String hFirstName;

        @JsonProperty("h_patronym")
        private String hPatronym;

        @JsonProperty("h_address")
        private String hAddress;

        @JsonProperty("h_sitizen")
        private String hSitizen;

        @JsonProperty("h_pnfl")
        private String hPnfl;

        @JsonProperty("w_family")
        private String wFamily;

        @JsonProperty("w_family_after")
        private String wFamilyAfter;

        @JsonProperty("w_first_name")
        private String wFirstName;

        @JsonProperty("w_patronym")
        private String wPatronym;

        @JsonProperty("w_address")
        private String wAddress;

        @JsonProperty("w_sitizen")
        private String wSitizen;

        @JsonProperty("w_pnfl")
        private String wPnfl;
    }
}
