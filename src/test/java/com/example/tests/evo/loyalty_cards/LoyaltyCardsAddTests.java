package com.example.tests.evo.loyalty_cards;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.loyalty_card.LoyaltyCardsAddRequest;
import com.example.api.evo.models.loyalty_card.LoyaltyCardsListResponse;
import com.example.api.evo.services.loyalty_card.LoyaltyCardPartnersService;
import com.example.api.evo.services.loyalty_card.LoyaltyCardsService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoyaltyCardsAddTests extends EvoBaseTest {
    @Autowired
    LoyaltyCardPartnersService loyaltyCardPartnersService;

    @Autowired
    LoyaltyCardsService loyaltyCardsService;

    String loyaltyCardId;
    String uniqueTitle = "Карта " + System.currentTimeMillis();
    String code = RandomStringUtils.randomNumeric(9);

    @Test
    
    @DisplayName("[loyalty_card.add] Добавление карты лояльности")
    void addLoyaltyCard() {
        String partnerId = step(
                "Запрос на получение ид партнера",
                () -> loyaltyCardPartnersService.getFirstLoyaltyCardPartnerId(headers));
        step("Запрос на добавление карты лояльности", () -> {
            var params = LoyaltyCardsAddRequest.Params.builder()
                    .title(uniqueTitle)
                    .code(code)
                    .partnerId(partnerId)
                    .build();
            ResponseWithOkResult response = loyaltyCardsService.addLoyaltyCard(params, headers);

            step("Проверка, что ответ содержит ок текст", () -> assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
        step("Запрос на получение списка карт", () -> loyaltyCardPartnersService.getLoyaltyCardsList(headers));
        step("Поиск добавленной карты в списке по имени", () -> {
            LoyaltyCardsListResponse.Result addedCard =
                    loyaltyCardPartnersService.getLoyaltyCardByTitle(headers, uniqueTitle);
            loyaltyCardId = addedCard.getId();
        });
    }
}
