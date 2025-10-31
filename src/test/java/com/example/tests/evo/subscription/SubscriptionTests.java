package com.example.tests.evo.subscription;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.subscription.SubscriptionListRequest;
import com.example.api.evo.models.subscription.SubscriptionListResponse;
import com.example.api.evo.services.subscription.SubscriptionService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static uz.click.evo_api.constants.EvoUrls.EVO_TEST_URL;

public class SubscriptionTests extends EvoBaseTest {
    @Autowired
    SubscriptionService subscriptionService;

    @Test
    
    @DisplayName("[subscription.list] Получение списка подписок.")
    void subscriptionListTest() {
        step("Получить в ответе список подписок", () -> {
            var paramsForSubscriptionList = SubscriptionListRequest.Params.builder()
                    .build();
            SubscriptionListResponse response =
                    subscriptionService.getSubscriptionList(paramsForSubscriptionList, headers);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult()).allSatisfy(r -> {
                    soft.assertThatCode(
                                    () -> given().when().head("").
                                            then().statusCode(200))
                            .as("Broken URL: %s", "")
                            .doesNotThrowAnyException();
                    soft.assertThat(r.getId()).isNotEmpty();
                    soft.assertThat(r.getServiceId()).isNotNull();
                    soft.assertThat(r.getCode()).isNotEmpty();
                    soft.assertThat(r.getAmount()).isNotNull();
                    //soft.assertThat(r.getExpires()).isNotNull();
                    soft.assertThat(r.getInterval()).isNotEmpty();
                    //soft.assertThat(r.getAccountId()).isNotNull();
                    soft.assertThat(r.getLogo()).isNotEmpty();
                    soft.assertThat(r.getRenewal()).isNotNull();
                    soft.assertThat(r.getTrialInterval()).isNotEmpty();
                    soft.assertThat(r.getIsSubscribed()).isNotNull();
                    soft.assertThat(r.getIsTrial()).isNotNull();
                    soft.assertThat(r.getDescriptionInfo()).allSatisfy(di -> {
                        soft.assertThat(di.getLabel()).isNotEmpty();
                        soft.assertThat(di.getValue()).isNotEmpty();
                    });
                    soft.assertThat(r.getInfo()).allSatisfy(info ->
                            soft.assertThat(info.getLabel()).isNotEmpty());
                });
            });
        });
    }
}