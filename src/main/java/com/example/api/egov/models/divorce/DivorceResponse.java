package com.example.api.egov.models.divorce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class DivorceResponse {
    private String id;

    @JsonProperty("result_code")
    private int resultCode;

    @JsonProperty("result_message")
    private String resultMessage;

    @JsonProperty("Items")
    private List<Item> items;

    @Getter
    public static class Item {
        @JsonProperty("doc_num")
        private String docNum;

        @JsonProperty("doc_date")
        private String docDate;

        private int branch;

        @JsonProperty("h_birth_day")
        private String hBirthDay;

        @JsonProperty("h_cert_date")
        private String hCertDate;

        @JsonProperty("w_birth_day")
        private String wBirthDay;

        @JsonProperty("w_cert_date")
        private String wCertDate;

        @JsonProperty("h_family")
        private String hFamily;

        @JsonProperty("h_family_after")
        private String hFamilyAfter;

        @JsonProperty("h_first_name")
        private String hFirstName;

        @JsonProperty("h_patronym")
        private String hPatronym;

        @JsonProperty("h_cert_series")
        private String hCertSeries;

        @JsonProperty("h_cert_number")
        private String hCertNumber;

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

        @JsonProperty("w_cert_series")
        private String wCertSeries;

        @JsonProperty("w_cert_number")
        private String wCertNumber;

        @JsonProperty("w_pnfl")
        private String wPnfl;
    }
}
