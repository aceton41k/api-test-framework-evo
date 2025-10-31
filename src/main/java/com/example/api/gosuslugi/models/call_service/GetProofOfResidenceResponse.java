package com.example.api.gosuslugi.models.call_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetProofOfResidenceResponse {
    private Response response;
    private Translations translations;

    @Getter
    public static class Response {
        private Boolean success;
        private File file;
        private Data data;
        private Object[] errors;
    }

    @Getter
    public static class File {
        private String has;
        private Long id;
    }

    @Getter
    public static class Data {
        @JsonProperty("PermanentRegistration")
        private PermanentRegistration permanentRegistration;

        @JsonProperty("TemproaryRegistrations")
        private Object temproaryRegistrations;
    }

    @Getter
    public static class PermanentRegistration {
        @JsonProperty("Cadastre")
        private String cadastre;

        @JsonProperty("Country")
        private NamedValue country;

        @JsonProperty("Region")
        private NamedValue region;

        @JsonProperty("District")
        private NamedValue district;

        @JsonProperty("Maxalla")
        private Maxalla maxalla;

        @JsonProperty("Street")
        private Street street;

        @JsonProperty("Address")
        private String address;

        @JsonProperty("RegistrationDate")
        private String registrationDate;
    }

    @Getter
    public static class NamedValue {
        @JsonProperty("Id")
        private Integer id;

        @JsonProperty("Value")
        private String value;

        @JsonProperty("IdValue")
        private String idValue;
    }

    @Getter
    public static class Maxalla {
        @JsonProperty("Id")
        private Integer id;

        @JsonProperty("Guid")
        private String guid;

        @JsonProperty("Value")
        private String value;
    }

    @Getter
    public static class Street {
        @JsonProperty("Id")
        private Integer id;

        @JsonProperty("Guid")
        private String guid;

        @JsonProperty("Value")
        private String value;
    }

    @Getter
    public static class Translations {
        @JsonProperty("PermanentRegistration")
        private Translation permanentRegistration;
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

        @JsonProperty("sort")
        private Integer sort;

        @JsonProperty("is_copied")
        private Boolean isCopied;
    }
}
