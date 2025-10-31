package com.example.tests.evo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.Location;
import com.example.api.evo.models.indoor.IndoorServiceListRequest;
import com.example.api.evo.models.indoor.IndoorServiceListResponse;
import com.example.api.evo.models.services.CategoryListResponse;
import com.example.api.evo.services.indoor.IndoorListService;
import com.example.jupiter.annotations.SkipAuthSetup;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.qameta.allure.Allure.step;

public class IndoorTests extends EvoBaseTest {
    @Autowired
    IndoorListService indoorListService;

    Map<String, String> emptyHeader = Collections.emptyMap();

    @Test
    
    @DisplayName("[indoor.category.list] Получение списка категорий индор сервисов")
    @SkipAuthSetup
    void getIndoorCategoryList() {
        step("[indoor.category.list] Получение списка категорий индор сервисов", () -> {
            CategoryListResponse response = indoorListService.getIndoorCategoryList(emptyHeader);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Список категорий не должен быть пустым")
                        .isNotEmpty()
                        .allSatisfy(category -> {
                            soft.assertThat(category.getId())
                                    .as("ID категории должен быть больше 0")
                                    .isGreaterThan(0);
                            soft.assertThat(category.getName())
                                    .as("Название категории не должно быть пустым")
                                    .isNotBlank();
                            soft.assertThat(category.getPriority())
                                    .as("Приоритет должен быть положительным числом")
                                    .isNotNull();
                            soft.assertThat(category.getStatus())
                                    .as("Статус категории должен быть 1 (активен)")
                                    .isIn(1, 0);
                            soft.assertThatUrl(category.getImage())
                                    .as("Ссылка на изображение должна быть доступной")
                                    .isReachable();
                        });
            });
        });
    }

    @Test
    
    @DisplayName("[indoor.service.list] Получение списка сервисов индор")
    @Disabled("Отключу так как что то сломался в бд")
    @SkipAuthSetup
    void getIndoorServiceList() {
        step("[indoor.service.list] Получение списка сервисов индор", () -> {
            var params = IndoorServiceListRequest.Params.builder().build();
            IndoorServiceListResponse response = indoorListService.getIndoorServiceList(params, emptyHeader);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Список indoor-сервисов не должен быть пустым")
                        .isNotEmpty()
                        .allSatisfy(service -> {
                            soft.assertThat(service.getId())
                                    .as("ID сервиса должен быть > 0")
                                    .isGreaterThan(0);
                            soft.assertThat(service.getName())
                                    .as("Название сервиса не должно быть пустым")
                                    .isNotBlank();
                            soft.assertThat(service.getCategoryId())
                                    .as("ID категории должен быть > 0")
                                    .isGreaterThan(0);
                            soft.assertThat(service.getStatus())
                                    .as("Статус должен быть 1")
                                    .isIn(1, 0);
                            soft.assertThat(service.getPriority())
                                    .as("Приоритет должен быть числом (>= 0)")
                                    .isGreaterThanOrEqualTo(0);
                            soft.assertThatUrl(service.getImage())
                                    .as("Ссылка на изображение должна быть доступна")
                                    .isReachable();
                            soft.assertThat(service.getMinLimit())
                                    .as("Минимальный лимит должен быть > 0")
                                    .isGreaterThan(0);
                            soft.assertThat(service.getMaxLimit())
                                    .as("Максимальный лимит должен быть больше минимального")
                                    .isGreaterThan(service.getMinLimit());
                            soft.assertThat(service.getCommissionPercent())
                                    .as("Комиссия должна быть от 0 до 100")
                                    .isBetween(0, 100);
                            soft.assertThat(service.getCardTypes())
                                    .as("card_types не должен быть пустым")
                                    .isNotEmpty()
                                    .allSatisfy(type ->
                                            soft.assertThat(type)
                                                    .as("тип карты должен быть строкой")
                                                    .isInstanceOf(String.class)
                                    );
                            soft.assertThat(service.getType())
                                    .as("Тип indoor-сервиса должен быть 'indoor'")
                                    .isEqualTo("indoor");
                            soft.assertThat(service.getQrOnly())
                                    .as("qr_only должен быть true или false")
                                    .isIn(true, false);
                            if (service.getLocation() != null) {
                                soft.assertThat(service.getLocation().getLat())
                                        .as("Широта должна быть указана")
                                        .isBetween(-90.0, 90.0);
                                soft.assertThat(service.getLocation().getLon())
                                        .as("Долгота должна быть указана")
                                        .isBetween(-180.0, 180.0);
                            }
                        });
            });
        });
    }

    @Test
    
    @DisplayName("[indoor.service.list] Получение списка сервисов индор по локации")
    @Disabled("Отключу так как что то сломался в бд")
    void getIndoorServiceListWithLocation() {
        step("[indoor.service.list] Получение списка сервисов индор", () -> {
            var location = Location.builder().lon(69.0433).lat(41.011337).build();
            var params = IndoorServiceListRequest.Params.builder().location(location).build();
            IndoorServiceListResponse response = indoorListService.getIndoorServiceList(params,
                    headers);

            step("Проверка ответа", () -> {
                List<Double> distances = response.getResult().stream()
                        .map(IndoorServiceListResponse.ServiceResult::getDistance)
                        .collect(Collectors.toList());

                soft.assertThat(distances)
                        .as("Все значения distance должны быть заполнены (не null)")
                        .allSatisfy(d -> soft.assertThat(d).isNotNull());

                soft.assertThat(distances)
                        .as("Значения distance должны быть отсортированы по возрастанию")
                        .isSorted();
            });
        });
    }
}
