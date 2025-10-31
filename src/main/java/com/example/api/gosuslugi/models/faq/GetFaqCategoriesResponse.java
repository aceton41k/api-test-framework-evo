package com.example.api.gosuslugi.models.faq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class GetFaqCategoriesResponse {
    private Integer id;

    @JsonProperty("name_ru")
    private String nameRu;

    @JsonProperty("name_uz")
    private String nameUz;

    @JsonProperty("name_en")
    private String nameEn;

    @JsonProperty("description_ru")
    private String descriptionRu;

    @JsonProperty("description_en")
    private String descriptionEn;

    @JsonProperty("description_uz")
    private String descriptionUz;

    private String icon;
    private Boolean status;
    private List<Faq> faqs;

    @Getter
    public static class Faq {
        private Integer id;

        @JsonProperty("faq_category_id")
        private Integer faqCategoryId;

        @JsonProperty("question_ru")
        private String questionRu;

        @JsonProperty("question_en")
        private String questionEn;

        @JsonProperty("question_uz")
        private String questionUz;

        @JsonProperty("answer_ru")
        private String answerRu;

        @JsonProperty("answer_en")
        private String answerEn;

        @JsonProperty("answer_uz")
        private String answerUz;

        private Boolean status;
        private Integer sort;
    }
}
