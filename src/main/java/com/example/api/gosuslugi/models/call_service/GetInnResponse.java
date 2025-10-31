package com.example.api.gosuslugi.models.call_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class GetInnResponse {
    private Response response;
    private Map<String, Translation> translations;

    @Getter
    public static class Response {
        private Boolean success;
        private File file;
        private Data data;
        private List<Object> errors;
    }

    @Getter
    public static class File {
        private String has;
        private Long id;
    }

    @Getter
    public static class Data {
        private String tin;
        private Integer ns10Code;
        private String ns10Name;
        private String ns10NameUz;
        private String ns10NameRu;
        private Integer ns11Code;
        private String ns11Name;
        private String ns11NameUz;
        private String ns11NameRu;
        private String surName;
        private String firstName;
        private String middleName;
        private String birthDate;
        private Integer sex;
        private String sexName;
        private String passSeries;
        private String passNumber;
        private String passDate;
        private String passOrg;
        private String phone;
        private String zipCode;
        private String address;
        private Integer ns13Code;
        private String ns13Name;
        private String tinDate;
        private String dateModify;
        private String isitd;
        private Boolean duty;
        private String personalNum;
        private String docCode;
        private String docName;
        private Boolean isNotary;
        private Boolean isPeasantFarm;
        private Boolean isResident;
    }

    @Getter
    public static class Translation {
        @JsonProperty("name_ru")
        private String nameRu;

        @JsonProperty("name_en")
        private String nameEn;

        @JsonProperty("name_uz")
        private String nameUz;

        @JsonProperty("is_title")
        private Boolean isTitle;

        private Integer sort;

        @JsonProperty("is_copied")
        private Boolean isCopied;
    }
}
