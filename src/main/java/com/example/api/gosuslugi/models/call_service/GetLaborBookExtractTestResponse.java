package com.example.api.gosuslugi.models.call_service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class GetLaborBookExtractTestResponse {
    private Response response;
    private Map<String, Translation> translations;

    @Getter
    public static class Response {
        private Boolean success;
        private File file;
        private List<EmploymentRecord> data;
        private List<String> errors;
    }

    @Getter
    public static class File {
        private String has;
        private Long id;
    }

    @Getter
    public static class EmploymentRecord {
        @JsonProperty("action_type_id")
        private Integer actionTypeId;

        @JsonProperty("actual_company_profile_name")
        private String actualCompanyProfileName;

        @JsonProperty("company_profile_name")
        private String companyProfileName;

        @JsonProperty("company_tin")
        private String companyTin;

        @JsonProperty("date_start")
        private String dateStart;

        @JsonProperty("date_stop")
        private String dateStop;

        private Long id;

        @JsonProperty("order_date")
        private String orderDate;

        @JsonProperty("order_number")
        private String orderNumber;

        @JsonProperty("parent_id")
        private Long parentId;

        @JsonProperty("position_name")
        private String positionName;

        @JsonProperty("position_name_ru")
        private String positionNameRu;

        @JsonProperty("structure_change")
        private String structureChange;

        @JsonProperty("structure_name")
        private String structureName;

        @JsonProperty("structure_name_ru")
        private String structureNameRu;

        @JsonProperty("work_type")
        private Integer workType;
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