package com.example.tests.evo.loyalty_cards;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.loyalty_card.LoyaltyCardsAddRequest;
import com.example.api.evo.models.loyalty_card.LoyaltyCardsDeleteRequest;
import com.example.api.evo.models.loyalty_card.LoyaltyCardsListResponse;
import com.example.api.evo.services.loyalty_card.LoyaltyCardPartnersService;
import com.example.api.evo.services.loyalty_card.LoyaltyCardsService;
import com.example.tests.evo.EvoBaseTest;

import java.util.Optional;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoyaltyCardsDeleteTests extends EvoBaseTest {
    @Autowired
    LoyaltyCardPartnersService loyaltyCardPartnersService;

    @Autowired
    LoyaltyCardsService loyaltyCardsService;

    String loyaltyCardId;

    @BeforeEach
    void setUp() {
        String loyaltyCardTitle = "Loyalty Card";
        step("Создание карты лояльности для тестов", () -> {
            String partnerId = loyaltyCardPartnersService.getFirstLoyaltyCardPartnerId(headers);
            var params = LoyaltyCardsAddRequest.Params.builder()
                    .title(loyaltyCardTitle)
                    .code(RandomStringUtils.randomNumeric(9))
                    .partnerId(partnerId)
                    .build();
            loyaltyCardsService.addLoyaltyCard(params, headers);
            loyaltyCardId = loyaltyCardPartnersService
                    .getLoyaltyCardByTitle(headers, loyaltyCardTitle)
                    .getId();
        });
    }

    @Test
    
    @DisplayName("[loyalty_card.remove] Удалить карту лояльности")
    void deleteLoyaltyCard() {
        step("Запрос на удаление карты лояльности", () -> {
            var params =
                    LoyaltyCardsDeleteRequest.Params.builder().id(loyaltyCardId).build();
            ResponseWithOkResult response = loyaltyCardsService.deleteLoyaltyCard(params,
                    headers);

            step("Проверка, что ответ содержит ок текст", () -> assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
        step("Поиск удаленной карты в списке", () -> {
            Optional<LoyaltyCardsListResponse.Result> addedCard =
                    loyaltyCardPartnersService.getLoyaltyCardById(headers, loyaltyCardId);

            step("Проверка, что удаленная карта не существует в списке", () -> assertThat(addedCard)
                    .isEmpty());
        });
    }
}
