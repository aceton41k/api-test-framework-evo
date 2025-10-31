package com.example.tests.gosuslugi;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.gosuslugi.models.services.GetCategoriesWithServicesResponse;
import com.example.api.gosuslugi.services.GovServices;

import java.util.Arrays;
import java.util.Optional;

import static io.qameta.allure.Allure.step;

public class GetServiceTests extends GovApiBaseTest {
    @Autowired
    GovServices govServices;

    @BeforeAll
    void setUp() {
        token = govOperations.govLogin(userData.getUser("XAS")).getToken();
    }

    @Test
    
    @DisplayName("[/getCategoriesWithServices][GET] Получение полного списка сервисов")
    void getAllServicesTest() {
        step("Запрос на получения сервисов", () -> {
            GetCategoriesWithServicesResponse[] response = govServices.getServices(token, "all", null);

            step("Проверка ответа", () -> {
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
                    soft.assertThat(category.getServices()).allSatisfy(service -> {
                        soft.assertThat(service.getCategoryId())
                                .as("service.category_id")
                                .isEqualTo(category.getId());
                        soft.assertThat(service.getId()).isPositive();
                        soft.assertThat(service.getNameRu())
                                .as("service.name_ru")
                                .isNotEmpty();
                        soft.assertThat(service.getNameEn())
                                .as("service.name_en")
                                .isNotEmpty();
                        soft.assertThat(service.getNameUz())
                                .as("service.name_uz")
                                .isNotEmpty();
                        soft.assertThat(service.getDescriptionRu())
                                .as("service.description_ru")
                                .isNotEmpty();
                        soft.assertThat(service.getDescriptionEn())
                                .as("service.description_en")
                                .isNotEmpty();
                        soft.assertThat(service.getDescriptionUz())
                                .as("service.description_uz")
                                .isNotEmpty();
                        soft.assertThat(service.getMethod())
                                .as("service.method")
                                .isNotEmpty();
                        soft.assertThat(service.getIcon()).as("service.icon").isNotEmpty();
                        soft.assertThat(service.getIsPopular())
                                .as("service.is_popular")
                                .isNotNull();
                        soft.assertThat(service.getForOrganization()).isNotNull();
                    });
                });
            });
        });
    }

    @Test
    
    @DisplayName(
            "[/getCategoriesWithServices?id=4][GET] Получение категории 'Семья и дети' (ID: 4) с привязанными сервисами")
    void getServicesWithIdTest() {
        step("Запрос на получение всех сервисов и категории и выбор категории с id = 4", () -> {
            GetCategoriesWithServicesResponse[] responseAll = govServices.getServices(token, "all", null);
            int familyCategoryId = Arrays.stream(responseAll)
                    .filter(r -> "Семья и дети".equals(r.getNameRu())) // Используем getNameRu(), не getName()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Категория 'Семья и дети' не найдена"))
                    .getId();
            soft.assertThat(responseAll.length).as("Добавлено 4 категории").isGreaterThanOrEqualTo(4);
            GetCategoriesWithServicesResponse[] response = govServices.getServices(token, null, familyCategoryId);

            step("Проверка ответа", () -> {
                soft.assertThat(response).as("Ожидаем 1 категорию в списке").hasSize(1);
                GetCategoriesWithServicesResponse category = Arrays.stream(response)
                        .filter(c -> c.getId() == familyCategoryId)
                        .findFirst()
                        .orElseThrow(() -> new AssertionError("Категория с id = " + familyCategoryId + " не найдена"));
                    soft.assertThat(category.getId()).as("category.id").isNotEqualTo(0);
                    soft.assertThat(category.getNameRu()).as("category.name_ru").isEqualTo("Семья и дети");
                    soft.assertThat(category.getNameEn()).as("category.name_en").isEqualTo("Family and children");
                    soft.assertThat(category.getNameUz()).as("category.name_uz").isEqualTo("Oila va bolalar");
                    soft.assertThat(category.getDescriptionRu())
                            .as("category.description_ru")
                            .isEqualTo("<div>Семья и дети</div>");
                    soft.assertThat(category.getDescriptionEn())
                            .as("category.description_en")
                            .isEqualTo("<div>Family and children</div>");
                    soft.assertThat(category.getDescriptionUz())
                            .as("category.description_uz")
                            .isEqualTo("<div>Oila va bolalar</div>");
                    soft.assertThat(category.getServices())
                            .as("category.services")
                            .hasSize(1);
                    Optional<GetCategoriesWithServicesResponse.Service> targetService = category.getServices().stream()
                            .filter(service -> service.getId() == 16)
                            .findFirst();
                    soft.assertThat(targetService)
                            .as("Сервис Очередь в детский сад должен быть найден")
                            .isPresent();
                    targetService.ifPresent(service -> {
                    soft.assertThat(service.getCategoryId())
                            .as("service.category_id")
                            .isEqualTo(4);
                   soft.assertThat(service.getNameRu())
                           .as("service.name_ru")
                           .isEqualTo("Проверка очереди в детский сад");
                   soft.assertThat(service.getNameEn())
                           .as("service.name_en")
                           .isEqualTo("Checking the kindergarten queue");
                   soft.assertThat(service.getNameUz())
                           .as("service.name_uz")
                           .isEqualTo("Bog'chaga navbatni tekshirish");
                   soft.assertThat(service.getDescriptionRu())
                           .as("service.description_ru")
                           .isEqualTo("<div>Проверка очереди в детский сад</div>");
                   soft.assertThat(service.getDescriptionEn())
                           .as("service.description_en")
                           .isEqualTo("<div>Checking the kindergarten queue</div>");
                   soft.assertThat(service.getDescriptionUz())
                           .as("service.description_uz")
                           .isEqualTo("<div>Bog'chaga navbatni tekshirish</div>");
                   soft.assertThat(service.getMethod())
                           .as("service.method")
                           .isEqualTo("GET");
                   soft.assertThat(service.getIcon()).as("service.icon").isNotEmpty();
                   soft.assertThat(service.getOrganization().getId())
                           .as("organization.id")
                           .isEqualTo(8);
                   soft.assertThat(service.getOrganization().getNameRu())
                           .as("organization.name_ru")
                           .isEqualTo(
                                   "Министерство дошкольного и школьного образования Республики Узбекистан");
                   soft.assertThat(service.getOrganization().getNameEn())
                           .as("organization.name_en")
                           .isEqualTo(
                                        "Ministry of preschool and school education of the Republic of Uzbekistan");
                   soft.assertThat(service.getOrganization().getNameUz())
                           .as("organization.name_uz")
                           .isEqualTo("O‘zbekiston Respublikasi maktabgacha va maktab ta'limi vazirligi");
                });
            });
        });
    }
}
