package com.example.tests.evo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.BaseErrorResponse;
import com.example.api.evo.models.card_applications.CardAplBanksRequest;
import com.example.api.evo.models.card_applications.CardAplBanksResponse;
import com.example.api.evo.models.card_applications.CardApplicationsDirectoryResponse;
import com.example.api.evo.services.card_applications.CardAplService;
import com.example.jupiter.annotations.SkipAuthSetup;

import java.util.Collections;
import java.util.Map;

import static io.qameta.allure.Allure.step;
import static com.example.api.evo.constants.ErrorCodes.SESSION_BLOCKED;
import static com.example.api.evo.constants.ServiceData.CITY_TASHKENT;
import static com.example.api.evo.constants.ServiceData.REGION_TASHKENT;

public class CardAplTests extends EvoBaseTest {
    private final Map<String, String> emptyHeaders = Collections.emptyMap();
    @Autowired
    CardAplService cardAplService;

    @Test
    
    @DisplayName("[card_apl.directory]  Получение тип карт и регионов")
    void getCardApplDirectory() {
        step("Получение тип карт и регионов", () -> {
            CardApplicationsDirectoryResponse response = cardAplService.getCardAplDirectory(headers,
                    CardApplicationsDirectoryResponse.class);

            step("Проверка блока card_types", () -> {
                soft.assertThat(response.getResult().getCardTypes())
                        .as("Список card_types не должен быть пустым")
                        .isNotEmpty()
                        .allSatisfy(card -> {
                            soft.assertThat(card.getCategory()).as("Category должен быть заполнен").isNotEmpty();
                            soft.assertThat(card.getType()).as("Type должен быть заполнен").isNotEmpty();
                            soft.assertThat(card.getName()).as("Name должен быть заполнен").isNotEmpty();
                            soft.assertThatUrl(card.getLogo()).as("Logo должен быть доступен по URL").isReachable();
                        });
            });

            step("Проверка блока regions и cities", () -> {
                soft.assertThat(response.getResult().getRegions())
                        .as("Список регионов не должен быть пустым")
                        .isNotEmpty()
                        .allSatisfy(region -> {
                            soft.assertThat(region.getCode()).as("Код региона должен быть > 0").isGreaterThan(0);
                            soft.assertThat(region.getName()).as("Название региона не должно быть пустым").isNotEmpty();

                            soft.assertThat(region.getCities())
                                    .as("Список городов в регионе не должен быть пустым")
                                    .isNotEmpty()
                                    .allSatisfy(city -> {
                                        soft.assertThat(city.getCode())
                                                .as("Код города должен быть > 0")
                                                .isGreaterThan(0);
                                        soft.assertThat(city.getName())
                                                .as("Название города не должно быть пустым")
                                                .isNotEmpty();
                                    });
                        });
            });

        });
    }

    @Test
    
    @DisplayName("[card_apl.directory] Получение тип карт и регионов без сессии")
    @SkipAuthSetup
    void getCardApplDirectoryWithOutHeaders() {
        step("Получение тип карт и регионов", () -> {
            BaseErrorResponse response = cardAplService.getCardAplDirectory(emptyHeaders, BaseErrorResponse.class);

            step("Проверка ответа", () ->
                    soft.assertThatError(response).hasError(SESSION_BLOCKED)
            );
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"UZCARD", "HUMO"})
    
    @DisplayName("[card_apl.banks] Получение данных банков")
    @SkipAuthSetup
    void getCardApplBanks(String typeOfCard) {
        step("Запрос на получение данных банков", () -> {
            var params = CardAplBanksRequest.Params.builder()
                    .cardType(typeOfCard)
                    .region(REGION_TASHKENT)
                    .city(CITY_TASHKENT)
                    .build();
            CardAplBanksResponse response = cardAplService.getCardAplBanks(params, emptyHeaders);

            step("Проверка ответа", () -> {
                var bank = response.getResult().getFirst();

                soft.assertThat(bank.getCode()).as("Код банка").isNotNull();
                soft.assertThat(bank.getName()).as("Название банка").isNotNull();

                soft.assertThat(bank.getDesigns())
                        .as("Список дизайнов").isNotEmpty()
                        .allSatisfy(d -> {
                            soft.assertThat(d.getDesign()).as("design").isNotNull();
                            soft.assertThat(d.getPrice()).as("price").isNotNull();
                            soft.assertThat(d.getDeliveryPrice()).as("delivery_price").isNotNull();
                            soft.assertThatUrl(d.getUrl()).as("url изображения дизайна").isReachable();
                        });

                soft.assertThat(bank.getTerms())
                        .as("HTML условия должны быть непустыми")
                        .isNotBlank();

                soft.assertThat(bank.getPaymentTypes())
                        .as("Тип оплаты должен содержать 'cash'")
                        .contains("cash");

                soft.assertThat(bank.getService()).as("Объект service").isNotNull();
                soft.assertThat(bank.getService().getId()).as("ID сервиса").isGreaterThan(0);
                soft.assertThat(bank.getService().getCardTypes())
                        .as("Список card_types в сервисе").isNotEmpty();

                soft.assertThat(bank.getDelivery())
                        .as("Поле delivery должно быть false")
                        .isFalse();

                soft.assertThat(bank.getLocationUrl())
                        .as("location_url должен быть непустым и начинаться с https://")
                        .startsWith("https://");
            });
        });
    }
}
