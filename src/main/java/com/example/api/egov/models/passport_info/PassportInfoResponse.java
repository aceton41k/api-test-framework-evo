package com.example.api.egov.models.passport_info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class PassportInfoResponse {

    @JsonProperty("result")
    private String result;

    @JsonProperty("data")
    private List<DataItem> data;

    @JsonProperty("comments")
    private String comments;

    @Getter
    public static class DataItem {

        @JsonProperty("pinpps")
        private List<String> pinpps;

        @JsonProperty("documents")
        private List<Document> documents;

        @JsonProperty("surnamelat")
        private String surnameLat;

        @JsonProperty("namelat")
        private String nameLat;

        @JsonProperty("patronymlat")
        private String patronymLat;

        @JsonProperty("surnamecyr")
        private String surnameCyr;

        @JsonProperty("namecyr")
        private String nameCyr;

        @JsonProperty("patronymcyr")
        private String patronymCyr;

        @JsonProperty("engsurname")
        private String engSurname;

        @JsonProperty("engname")
        private String engName;

        @JsonProperty("birthplace")
        private String birthplace;

        @JsonProperty("birthcountry")
        private String birthCountry;

        @JsonProperty("birthcountryid")
        private Integer birthCountryId;

        @JsonProperty("livestatus")
        private Integer liveStatus;

        @JsonProperty("nationality")
        private String nationality;

        @JsonProperty("nationalityid")
        private Integer nationalityId;

        @JsonProperty("citizenship")
        private String citizenship;

        @JsonProperty("citizenshipid")
        private Integer citizenshipId;

        @JsonProperty("sex")
        private Integer sex;

        @JsonProperty("photo")
        private String photo;

        @JsonProperty("transaction_id")
        private Integer transactionId;

        @JsonProperty("current_pinpp")
        private String currentPinpp;

        @JsonProperty("current_document")
        private String currentDocument;

        @JsonProperty("birth_date")
        private String birthDate;
    }

    @Getter
    public static class Document {

        @JsonProperty("document")
        private String document;

        @JsonProperty("type")
        private String type;

        @JsonProperty("docgiveplace")
        private String docGivePlace;

        @JsonProperty("docgiveplaceid")
        private Integer docGivePlaceId;

        @JsonProperty("datebegin")
        private String dateBegin;

        @JsonProperty("dateend")
        private String dateEnd;

        @JsonProperty("status")
        private Integer status;
    }
}
