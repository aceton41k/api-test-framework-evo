package com.example.api.gosuslugi.models.services;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class GetCategoriesWithServicesResponse {
    private Integer id;

    @JsonProperty("name_ru")
    private String nameRu;

    @JsonProperty("name_en")
    private String nameEn;

    @JsonProperty("name_uz")
    private String nameUz;

    @JsonProperty("description_en")
    private String descriptionEn;

    @JsonProperty("description_uz")
    private String descriptionUz;

    @JsonProperty("description_ru")
    private String descriptionRu;

    private Boolean status;
    private String photo;
    private Integer sort;
    private List<Service> services;

    @Getter
    public static class Service {
        private Integer id;

        @JsonProperty("category_id")
        private Integer categoryId;

        @JsonProperty("name_ru")
        private String nameRu;

        @JsonProperty("name_en")
        private String nameEn;

        @JsonProperty("name_uz")
        private String nameUz;

        @JsonProperty("description_ru")
        private String descriptionRu;

        @JsonProperty("description_en")
        private String descriptionEn;

        @JsonProperty("description_uz")
        private String descriptionUz;

        private String url;
        private String method;
        private String icon;
        private Boolean status;
        private Integer sort;

        @JsonProperty("organization_id")
        private Integer organizationId;

        @JsonProperty("is_free")
        private Boolean isFree;

        private String phone;
        private String price;

        @JsonProperty("is_popular")
        private Boolean isPopular;

        @JsonProperty("for_organization")
        private Boolean forOrganization;

        private Organization organization;
    }

    @Getter
    public static class Organization {
        private Integer id;

        @JsonProperty("name_ru")
        private String nameRu;

        @JsonProperty("name_uz")
        private String nameUz;

        @JsonProperty("name_en")
        private String nameEn;

        @JsonProperty("phone_number")
        private String phoneNumber;
    }
}
