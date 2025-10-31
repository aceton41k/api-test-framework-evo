package com.example.tests.evo.evo_bigcashback;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.Location;
import com.example.api.evo.models.big_cashback.BigCashbackIndoorServiceListRequest;
import com.example.api.evo.models.indoor.IndoorServiceListResponse;
import com.example.api.evo.services.big_cashback.BigCashbackService;
import com.example.jupiter.annotations.SkipAuthSetup;
import com.example.tests.evo.EvoBaseTest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;

@SkipAuthSetup
public class BigCashbackServicesTests extends EvoBaseTest {
    @Autowired
    BigCashbackService bigCashbackService;
    Map<String, String> emptyMap = Collections.emptyMap();

    static Stream<Arguments> sortTypesProvider() {
        return Stream.of(
                Arguments.of("location"),
                Arguments.of("biggest"),
                Arguments.of("newest")
        );
    }

    @Test
    
    @DisplayName("[promo.bigcashback.service.list] Получение списка сервисов Big Cashback")
    void bigCashbackServiceListTest() {
        step("Запрос на получение списка сервисов bigCashback", () -> {
            IndoorServiceListResponse response = bigCashbackService.getBcServiceList(emptyMap);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Результат не должен быть пустым")
                        .isNotEmpty()
                        .allSatisfy(r -> {
                            soft.assertThat(r.getId()).as("ID сервиса должен быть > 0").isGreaterThan(0);
                            soft.assertThat(r.getName()).as("Название сервиса не должно быть пустым").isNotEmpty();
                            soft.assertThat(r.getCategoryId()).as("CategoryId не должен быть null").isNotNull();
                            soft.assertThat(r.getPriority()).as("Priority не должен быть null").isNotNull();
                            soft.assertThat(r.getStatus()).as("Status должен быть 1").isEqualTo(1);
                            soft.assertThat(r.getVisible()).as("Visible должен быть true").isTrue();
                            soft.assertThatUrl(r.getImage()).as("URL изображения должен быть доступен").isReachable();
                            soft.assertThat(r.getMinLimit()).as("Минимальный лимит должен быть > 99").isGreaterThan(99);
                            soft.assertThat(r.getMaxLimit())
                                    .as("Максимальный лимит должен быть > 1000")
                                    .isGreaterThan(1000);
                            soft.assertThat(r.getCardTypes()).as("CardTypes не должен быть null").isNotNull();
                            soft.assertThat(r.getOfflineAvailable())
                                    .as("OfflineAvailable не должен быть null")
                                    .isNotNull();
                            soft.assertThat(r.getMaintenance()).as("Maintenance не должен быть null").isNotNull();
                            soft.assertThat(r.getAutopayScheduleAvailable())
                                    .as("AutopayScheduleAvailable не должен быть null")
                                    .isNotNull();
                            soft.assertThat(r.getFavoritePermission())
                                    .as("FavoritePermission не должен быть null")
                                    .isNotNull();
                            soft.assertThat(r.getQrOnly()).as("QrOnly не должен быть null").isNotNull();
                            soft.assertThat(r.getLabel()).as("Label не должен быть пустым").isNotEmpty();

                            double labelToPercent = Double.parseDouble(r.getLabel().replace("%", ""));
                            soft.assertThat(r.getCashbackPercent())
                                    .as("CashbackPercent должен соответствовать значению label / 100")
                                    .isEqualTo(labelToPercent / 100);

                            soft.assertThat(r.getCashbackExpireDate())
                                    .as("CashbackExpireDate не должен быть пустым")
                                    .isNotEmpty();
                            soft.assertThat(r.getVersion()).as("Version не должен быть пустым").isNotEmpty();
                            soft.assertThat(r.getApiVersion()).as("ApiVersion не должен быть пустым").isNotEmpty();
                        });
            });
        });
    }


    @ParameterizedTest
    @MethodSource("sortTypesProvider")
    
    @DisplayName("[promo.bigcashback.indoor.service.list] Получение списка indoor сервисов bigCashback")
    void bigCashbackIndoorServiceListTest(String sort) {
        step("Запрос на получение списка indoor сервисов bigCashback", () -> {
            var params = BigCashbackIndoorServiceListRequest.Params.builder().sort(sort).build();
            IndoorServiceListResponse response = bigCashbackService.getBcIndoorServiceList(params, emptyMap);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult()).isNotEmpty().allSatisfy(r -> {
                    soft.assertThat(r.getMaintenance()).as("Maintenance должен быть true или false").isIn(true, false);
                    if (!r.getMaintenance()) {
                        soft.assertThat(r.getId()).as("ID должен быть > 0").isGreaterThan(0);
                        soft.assertThat(r.getName()).as("Name не должен быть пустым").isNotEmpty();
                        soft.assertThat(r.getCategoryId()).as("CategoryId не должен быть null").isNotNull();
                        soft.assertThat(r.getStatus()).as("Status должен быть = 1").isEqualTo(1);
                        soft.assertThat(r.getPriority()).as("Priority не должен быть null").isNotNull();
                        soft.assertThatUrl(r.getImage()).as("Image должен быть доступен по URL").isReachable();
                        soft.assertThat(r.getMinLimit()).as("MinLimit > 99").isGreaterThan(99);
                        soft.assertThat(r.getMaxLimit()).as("MaxLimit > 1000").isGreaterThan(1000);
                        soft.assertThat(r.getCommissionPercent())
                                .as("CommissionPercent не должен быть null")
                                .isNotNull();
                        soft.assertThat(r.getAddress()).as("Address не должен быть null").isNotNull();
                        soft.assertThat(r.getCardTypes()).as("Тип карт не должен быть пустым").isNotEmpty();
                        soft.assertThat(r.getLabel()).as("Label не должен быть пустым").isNotEmpty();
                        double labelToPercent = Double.parseDouble(r.getLabel().replace("%", ""));
                        soft.assertThat(r.getCashbackPercent()).isEqualTo(labelToPercent / 100);
                        soft.assertThat(r.getCashbackExpireDate())
                                .as("CashbackExpireDate не должен быть пустым")
                                .isNotEmpty();
                        soft.assertThat(r.getQrOnly()).as("QrOnly должен быть true или false").isIn(true, false);
                        soft.assertThat(r.getLocation().getLat()).as("Lat не должен быть null").isNotNull();
                        soft.assertThat(r.getLocation().getLon()).as("Lon не должен быть null").isNotNull();
                    }

                });
            });
        });
    }

    @ParameterizedTest
    @MethodSource("sortTypesProvider")
    
    @DisplayName("[promo.bigcashback.indoor.service.list] Получение списка indoor сервисов bigCashback с локацией")
    void bigCashbackIndoorServiceListWithLocationTest(String sort) {
        step("Запрос на получение списка indoor сервисов bigCashback", () -> {
            var location = Location.builder().lat(41.326992).lon(69.313364).build();
            var params = BigCashbackIndoorServiceListRequest.Params.builder().location(location).sort(sort).build();
            IndoorServiceListResponse response = bigCashbackService.getBcIndoorServiceList(params, emptyMap);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult()).isNotEmpty().allSatisfy(r -> {
                    soft.assertThat(r.getMaintenance()).as("Maintenance должен быть true или false").isIn(true, false);
                    if (!r.getMaintenance()) {
                        soft.assertThat(r.getId()).as("ID должен быть > 0").isGreaterThan(0);
                        soft.assertThat(r.getName()).as("Name не должен быть пустым").isNotEmpty();
                        soft.assertThat(r.getCategoryId()).as("CategoryId не должен быть null").isNotNull();
                        soft.assertThat(r.getStatus()).as("Status должен быть = 1").isEqualTo(1);
                        soft.assertThat(r.getPriority()).as("Priority не должен быть null").isNotNull();
                        soft.assertThatUrl(r.getImage()).as("Image должен быть доступен по URL").isReachable();
                        soft.assertThat(r.getMinLimit()).as("MinLimit > 99").isGreaterThan(99);
                        soft.assertThat(r.getMaxLimit()).as("MaxLimit > 1000").isGreaterThan(1000);
                        soft.assertThat(r.getCommissionPercent())
                                .as("CommissionPercent не должен быть null")
                                .isNotNull();
                        soft.assertThat(r.getAddress()).as("Address не должен быть null").isNotNull();
                        soft.assertThat(r.getCardTypes()).as("Тип карт не должен быть пустым").isNotEmpty();
                        soft.assertThat(r.getLabel()).as("Label не должен быть пустым").isNotEmpty();
                        double labelToPercent = Double.parseDouble(r.getLabel().replace("%", ""));
                        soft.assertThat(r.getCashbackPercent()).isEqualTo(labelToPercent / 100);
                        soft.assertThat(r.getCashbackExpireDate())
                                .as("CashbackExpireDate не должен быть пустым")
                                .isNotEmpty();
                        soft.assertThat(r.getQrOnly()).as("QrOnly должен быть true или false").isIn(true, false);
                        soft.assertThat(r.getLocation().getLat()).as("Lat не должен быть null").isNotNull();
                        soft.assertThat(r.getLocation().getLon()).as("Lon не должен быть null").isNotNull();
                    }

                });
            });
        });
    }

    @Test
    
    @DisplayName("[promo.bigcashback.indoor.service.list] Получение списка indoor сервисов bigCashback по убыванию процента cashback(label)")
    void bigCashbackIndoorServiceListBiggestSortingTest() {
        step("Запрос на получение списка indoor сервисов bigCashback", () -> {
            var location = Location.builder().lat(41.326992).lon(69.313364).build();
            var params = BigCashbackIndoorServiceListRequest.Params.builder()
                    .location(location)
                    .sort("biggest")
                    .build();
            IndoorServiceListResponse response = bigCashbackService.getBcIndoorServiceList(params, emptyMap);
            step("Проверка ответа", () -> {
                List<Double> percents = response.getResult()
                        .stream()
                        .map(s -> Double.parseDouble(s.getLabel().replace("%", "")))
                        .toList();

                soft.assertThat(percents)
                        .as("Кэшбэк label'ы должны быть отсортированы по убыванию или равны")
                        .isSortedAccordingTo(Comparator.reverseOrder());
            });
        });
    }

    @Test
    
    @DisplayName("[promo.bigcashback.indoor.service.list] Получение списка indoor сервисов bigCashback по сортированной по локации")
    void bigCashbackIndoorServiceListLocationSortingTest() {
        step("Запрос на получение списка indoor сервисов bigCashback", () -> {
            var location = Location.builder().lat(41.326992).lon(69.313364).build();
            var params = BigCashbackIndoorServiceListRequest.Params.builder()
                    .location(location)
                    .sort("location")
                    .build();
            IndoorServiceListResponse response = bigCashbackService.getBcIndoorServiceList(params, emptyMap);
            step("Проверка ответа", () -> {
                var distances = response.getResult()
                        .stream()
                        .map(IndoorServiceListResponse.ServiceResult::getDistance)
                        .toList();
                soft.assertThat(distances)
                        .as("Сервисы должны быть отсортированы по возрастанию distance")
                        .isSortedAccordingTo(Comparator.naturalOrder());
            });
        });
    }

    @Test
    
    @DisplayName("[promo.bigcashback.indoor.service.list] Получение списка indoor сервисов bigCashback с параметром search")
    void bigCashbackIndoorServiceListWithSearchTest() {
        step("Запрос на получение списка indoor сервисов bigCashback", () -> {
            String searchText = "Ala";
            var params = BigCashbackIndoorServiceListRequest.Params.builder().search(searchText).build();
            IndoorServiceListResponse response = bigCashbackService.getBcIndoorServiceList(params, emptyMap);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Все названия должны содержать '" + searchText + "'")
                        .allSatisfy(r -> soft.assertThat(r.getName().toLowerCase())
                                .as("Сервис: " + r.getName())
                                .contains("ala"));
            });
        });
    }

    @Test
    
    @DisplayName("[promo.bigcashback.featured.list] Получение списка избранных сервисов bigCashback")
        void bigCashbackFeaturedServiceListTest() {
        step("Запрос на получение списка сервисов bigCashback", () -> {
            IndoorServiceListResponse response = bigCashbackService.getBcFeaturedServiceList(emptyMap);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Ответ должен содержать хотя бы один сервис")
                        .isNotEmpty()
                        .allSatisfy(r -> {
                            soft.assertThat(r.getMaintenance())
                                    .as("Поле maintenance должно быть true или false")
                                    .isIn(true, false);

                            if (!r.getMaintenance()) {
                                soft.assertThat(r.getId()).as("ID сервиса должен быть > 0").isGreaterThan(0);
                                soft.assertThat(r.getName()).as("Поле name не должно быть пустым").isNotEmpty();
                                soft.assertThat(r.getServiceName())
                                        .as("Поле serviceName не должно быть пустым")
                                        .isNotEmpty();
                                soft.assertThatUrl(r.getImage())
                                        .as("URL изображения должен быть доступен")
                                        .isReachable();
                                soft.assertThat(r.getType())
                                        .as("Тип сервиса должен быть 'indoor' или 'service")
                                        .isIn("indoor", "service");
                                soft.assertThat(r.getLabel()).as("Поле label не должно быть пустым").isNotEmpty();
                                double labelToPercent = Double.parseDouble(r.getLabel().replace("%", ""));
                                soft.assertThat(r.getCashbackPercent())
                                        .as("cashbackPercent должен соответствовать label / 100")
                                        .isEqualTo(labelToPercent / 100);
                                soft.assertThat(r.getCashbackExpireDate())
                                        .as("Поле cashbackExpireDate не должно быть пустым")
                                        .isNotEmpty();
                                soft.assertThat(r.getCommissionPercent())
                                        .as("Поле commissionPercent не должно быть null")
                                        .isNotNull();
                                soft.assertThat(r.getServiceCategory())
                                        .as("Поле serviceCategory не должно быть null")
                                        .isNotNull();
                                soft.assertThat(r.getMinLimit())
                                        .as("Минимальный лимит должен быть > 99")
                                        .isGreaterThan(99);
                                soft.assertThat(r.getMaxLimit())
                                        .as("Максимальный лимит должен быть > 1000")
                                        .isGreaterThan(1000);
                                soft.assertThat(r.getAddress()).as("Поле address не должно быть null").isNotNull();
                                soft.assertThat(r.getCardTypes())
                                        .as("Список cardTypes не должен быть пустым")
                                        .isNotEmpty();
                                soft.assertThat(r.getDirectPayment())
                                        .as("Поле directPayment должно быть true или false")
                                        .isIn(true, false);
                                soft.assertThat(r.getFavoritePermission())
                                        .as("Поле favoritePermission должно быть true или false")
                                        .isIn(true, false);
                                soft.assertThat(r.getQrOnly())
                                        .as("Поле qrOnly должно быть true или false")
                                        .isIn(true, false);
                                soft.assertThat(r.getPriority()).as("Поле priority не должно быть null").isNotNull();
                            }
                        });

            });
        });
    }

    @Test
    
    @DisplayName("[promo.bigcashback.vip.list] Получение списка vip сервисов bigCashback")
    void bigCashbackVipServiceListTest() {
        step("Запрос на получение списка vip сервисов bigCashback", () -> {
            IndoorServiceListResponse response = bigCashbackService.getBcVipServiceList(emptyMap);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Ответ должен содержать хотя бы один сервис")
                        .isNotEmpty()
                        .allSatisfy(r -> {
                            soft.assertThat(r.getMaintenance())
                                    .as("Поле maintenance должно быть true или false")
                                    .isIn(true, false);

                            if (!r.getMaintenance()) {
                                soft.assertThat(r.getId()).as("ID сервиса должен быть > 0").isGreaterThan(0);
                                soft.assertThat(r.getName()).as("Поле name не должно быть пустым").isNotEmpty();
                                soft.assertThat(r.getServiceName())
                                        .as("Поле serviceName не должно быть пустым")
                                        .isNotEmpty();
                                soft.assertThatUrl(r.getImage())
                                        .as("URL изображения должен быть доступен")
                                        .isReachable();
                                soft.assertThatUrl(r.getBannerImage())
                                        .as("URL баннера изображения должен быть доступен")
                                        .isReachable();
                                soft.assertThat(r.getType())
                                        .as("Тип сервиса должен быть 'service' или 'indoor'")
                                        .isIn("service", "indoor");
                                soft.assertThat(r.getLabel()).as("Поле label не должно быть пустым").isNotEmpty();
                                double labelToPercent = Double.parseDouble(r.getLabel().replace("%", ""));
                                soft.assertThat(r.getCashbackPercent())
                                        .as("cashbackPercent должен соответствовать label / 100")
                                        .isEqualTo(labelToPercent / 100);
                                soft.assertThat(r.getCashbackExpireDate())
                                        .as("Поле cashbackExpireDate не должно быть пустым")
                                        .isNotEmpty();
                                soft.assertThat(r.getCommissionPercent())
                                        .as("Поле commissionPercent не должно быть null")
                                        .isNotNull();
                                soft.assertThat(r.getServiceCategory())
                                        .as("Поле serviceCategory не должно быть null")
                                        .isNotNull();
                                soft.assertThat(r.getMinLimit())
                                        .as("Минимальный лимит должен быть > 99")
                                        .isGreaterThan(99);
                                soft.assertThat(r.getMaxLimit())
                                        .as("Максимальный лимит должен быть > 1000")
                                        .isGreaterThan(1000);
                                soft.assertThat(r.getAddress()).as("Поле address не должно быть null").isNotNull();
                                soft.assertThat(r.getCardTypes())
                                        .as("Список cardTypes не должен быть пустым")
                                        .isNotEmpty();
                                soft.assertThat(r.getDirectPayment())
                                        .as("Поле directPayment должно быть true или false")
                                        .isIn(true, false);
                                soft.assertThat(r.getFavoritePermission())
                                        .as("Поле favoritePermission должно быть true или false")
                                        .isIn(true, false);
                                soft.assertThat(r.getQrOnly())
                                        .as("Поле qrOnly должно быть true или false")
                                        .isIn(true, false);
                                soft.assertThat(r.getPriority()).as("Поле priority не должно быть null").isNotNull();
                            }
                        });
            });
        });
    }
}
