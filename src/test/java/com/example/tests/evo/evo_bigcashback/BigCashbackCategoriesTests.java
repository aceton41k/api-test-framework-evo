package com.example.tests.evo.evo_bigcashback;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.services.CategoryListResponse;
import com.example.api.evo.services.big_cashback.BigCashbackService;
import com.example.jupiter.annotations.SkipAuthSetup;
import com.example.tests.evo.EvoBaseTest;

import java.util.Collections;
import java.util.Map;

import static io.qameta.allure.Allure.step;
public class BigCashbackCategoriesTests extends EvoBaseTest {
    @Autowired
    BigCashbackService bigCashbackService;

    Map<String, String> emptyHeaders = Collections.emptyMap();

    @Test
    
    @DisplayName("[promo.bigcashback.category.list] Получение списка категорий bigCashback")
    @SkipAuthSetup
    void bigCashbackCategoryListTest() {
        step("Запрос на получение списка категорий bigCashback", () -> {
            CategoryListResponse response = bigCashbackService.getBcCategoryList(emptyHeaders);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Список категорий не должен быть пустым")
                        .isNotEmpty()
                        .allSatisfy(r -> {
                            soft.assertThat(r.getId())
                                    .as("ID категории должен быть > 0")
                                    .isGreaterThan(0);
                            soft.assertThat(r.getName())
                                    .as("Название категории не должно быть пустым")
                                    .isNotEmpty();
                            soft.assertThat(r.getPriority())
                                    .as("Priority не должен быть null")
                                    .isNotNull();
                            soft.assertThat(r.getStatus())
                                    .as("Status должен быть равен 1")
                                    .isEqualTo(1);
                            soft.assertThatUrl(r.getImage())
                                    .as("URL изображения должен быть доступен")
                                    .isReachable();
                        });
            });
        });
    }

    @Test
    
    @DisplayName("[promo.bigcashback.indoor.category.list] Получение списка indoor категорий bigCashback")
    @SkipAuthSetup
    void bigCashbackIndoorCategoryListTest() {
        step("Запрос на получение списка indoor категорий bigCashback", () -> {
            CategoryListResponse response = bigCashbackService.getBcIndoorCategoryList(emptyHeaders);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Список indoor категорий не должен быть пустым")
                        .isNotEmpty()
                        .allSatisfy(r -> {
                            soft.assertThat(r.getId())
                                    .as("ID категории должен быть > 0")
                                    .isGreaterThan(0);
                            soft.assertThat(r.getName())
                                    .as("Название категории не должно быть пустым")
                                    .isNotEmpty();
                            soft.assertThat(r.getPriority())
                                    .as("Priority не должен быть null")
                                    .isNotNull();
                            soft.assertThat(r.getStatus())
                                    .as("Status должен быть равен 1")
                                    .isEqualTo(1);
                            soft.assertThatUrl(r.getImage())
                                    .as("URL изображения должен быть доступен")
                                    .isReachable();
                        });
            });
        });
    }
}
