package com.example.api.gosuslugi.models.call_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Map;

@Getter
public class GetTravelBanStatusResponse {
    private Response response;
    private Map<String, Translation> translations;

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
        @JsonProperty("result_code")
        private Integer resultCode;

        @JsonProperty("result_message")
        private String resultMessage;

        @JsonProperty("debtor_passport_sn")
        private String debtorPassportSn;

        @JsonProperty("debtor_passport_num")
        private String debtorPassportNum;

        @JsonProperty("debtor_pin")
        private String debtorPin;

        @JsonProperty("debtor_name")
        private String debtorName;

        @JsonProperty("ban_info")
        private Object banInfo;
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
