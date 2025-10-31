package com.example.tests.evo.premium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.premium_subscription.PremiumTariffListResponse;
import com.example.api.evo.services.premium_subscription.PremiumSubscriptionService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;


public class PremiumTariffListTests extends EvoBaseTest {

    @Autowired
    PremiumSubscriptionService premiumSubscriptionService;

    @Test
    
    @DisplayName("[premium.tariff.list] Получение листа опций премиум тарифа")
        void getPremiumTariffListTest() {
        step("Запрос на получение листа опций премиум тарифа", () -> {
            PremiumTariffListResponse response = premiumSubscriptionService.getPremiumTariffList(headers);
            step("Проверка всех тарифов", () -> {
                soft.assertThat(response.getResult())
                        .as("tariffs")
                        .allSatisfy(tariff -> {
                            soft.assertThat(tariff.getName())
                                    .as("tariff.name")
                                    .isNotBlank();

                            soft.assertThat(tariff.getHeader())
                                    .as("tariff.header")
                                    .isNotNull();
                            soft.assertThat(tariff.getHeader().getDescription())
                                    .as("tariff.header.description")
                                    .isNotBlank();

                            soft.assertThat(tariff.getDetails())
                                    .as("tariff.details")
                                    .isNotEmpty()
                                    .allSatisfy(detail -> {
                                        soft.assertThat(detail.getTitle())
                                                .as("detail.title")
                                                .isNotBlank();
                                        soft.assertThat(detail.getText())
                                                .as("detail.text")
                                                .isNotBlank();
                                        
                                        soft.assertThatCode(() -> given()
                                                        .when()
                                                        .head(detail.getIcon())
                                                        .then()
                                                        .statusCode(200))
                                                .as("Broken ICON: %s", detail.getIcon())
                                                .doesNotThrowAnyException();
                                        
                                        soft.assertThatCode(() -> given()
                                                        .when()
                                                        .head(detail.getUrl())
                                                        .then()
                                                        .statusCode(200))
                                                .as("Broken URL: %s", detail.getUrl())
                                                .doesNotThrowAnyException();

                                        soft.assertThat(detail.getAnalyticsVariable())
                                                .as("detail.analyticsVariable")
                                                .isNotBlank();
                                    });
                        });
            });
        });
    }
}
