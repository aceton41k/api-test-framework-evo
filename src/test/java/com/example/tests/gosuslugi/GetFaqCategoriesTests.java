package com.example.tests.gosuslugi;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.gosuslugi.models.faq.GetFaqCategoriesResponse;
import com.example.api.gosuslugi.services.GovFaqCategories;

import static io.qameta.allure.Allure.step;

public class GetFaqCategoriesTests extends GovApiBaseTest {
    @Autowired
    GovFaqCategories govFaqCategories;

    @BeforeAll
    void setUp() {
        token = govOperations.govLogin(userData.getUser("XAS")).getToken();
    }

    @Test
    
    @DisplayName("[/faq_categories] GET Получение категорий FAQ и вложенных вопросов")
    void getFaqCategoriesTest() {
        step("Запрос на получение категорий FAQ", () -> {
            GetFaqCategoriesResponse[] response = govFaqCategories.getFaq(token);

            step("Проверка ответа", () -> {
                soft.assertThat(response)
                        .as("Список категорий не должен быть пустым")
                        .isNotEmpty();
                soft.assertThat(response).allSatisfy(category -> {
                    soft.assertThat(category.getId()).isPositive();
                    soft.assertThat(category.getNameRu()).as("category.name_ru").isNotEmpty();
                    soft.assertThat(category.getNameEn()).as("category.name_en").isNotEmpty();
                    soft.assertThat(category.getNameUz()).as("category.name_uz").isNotEmpty();
                    soft.assertThat(category.getDescriptionRu())
                            .as("category.description_ru")
                            .isNotEmpty();
                    soft.assertThat(category.getDescriptionEn())
                            .as("category.description_en")
                            .isNotEmpty();
                    soft.assertThat(category.getDescriptionUz())
                            .as("category.description_uz")
                            .isNotEmpty();
                    soft.assertThat(category.getIcon()).as("category.icon").isNotEmpty();
                    soft.assertThat(category.getStatus()).as("category.status").isIn(true, false);
                    soft.assertThat(category.getFaqs()).as("category.faqs").isNotEmpty();
                    soft.assertThat(category.getFaqs()).allSatisfy(faq -> {
                        soft.assertThat(faq.getId()).isPositive();
                        soft.assertThat(faq.getFaqCategoryId())
                                .as("faq.faq_category_id")
                                .isEqualTo(category.getId());
                        soft.assertThat(faq.getQuestionRu())
                                .as("faq.question_ru")
                                .isNotEmpty();
                        soft.assertThat(faq.getQuestionEn())
                                .as("faq.question_en")
                                .isNotEmpty();
                        soft.assertThat(faq.getQuestionUz())
                                .as("faq.question_uz")
                                .isNotEmpty();
                        soft.assertThat(faq.getAnswerRu()).as("faq.answer_ru").isNotEmpty();
                        soft.assertThat(faq.getAnswerEn()).as("faq.answer_en").isNotEmpty();
                        soft.assertThat(faq.getAnswerUz()).as("faq.answer_uz").isNotEmpty();
                        soft.assertThat(faq.getStatus()).as("faq.status").isIn(true, false);
                    });
                });
            });
        });
    }
}
