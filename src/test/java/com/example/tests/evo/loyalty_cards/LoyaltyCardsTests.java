package com.example.tests.evo.loyalty_cards;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.constants.UserData;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.loyalty_card.LoyaltyCardPartnersResponse;
import com.example.api.evo.models.loyalty_card.LoyaltyCardsAddRequest;
import com.example.api.evo.models.loyalty_card.LoyaltyCardsEditRequest;
import com.example.api.evo.models.loyalty_card.LoyaltyCardsListResponse;
import com.example.api.evo.services.loyalty_card.LoyaltyCardPartnersService;
import com.example.api.evo.services.loyalty_card.LoyaltyCardsService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoyaltyCardsTests extends EvoBaseTest {
    @Autowired
    LoyaltyCardPartnersService loyaltyCardPartnersService;

    @Autowired
    LoyaltyCardsService loyaltyCardsService;

    String loyaltyCardTitle = "Карта лояльности";
    String loyaltyCardId;
    LoyaltyCardsListResponse.Result addedCard;
    UserData.User user;

    @BeforeEach
    void setUp() {
        step("Создание карты лояльности для тестов", () -> {
            String partnerId = loyaltyCardPartnersService.getFirstLoyaltyCardPartnerId(headers);
            var params = LoyaltyCardsAddRequest.Params.builder()
                    .title(loyaltyCardTitle)
                    .code(RandomStringUtils.randomNumeric(9))
                    .partnerId(partnerId)
                    .build();
            loyaltyCardsService.addLoyaltyCard(params, headers);
            addedCard = loyaltyCardPartnersService.getLoyaltyCardByTitle(headers,
                    loyaltyCardTitle);
        });
    }

    @Test
    
    @DisplayName("[loyalty_card.partners] Получение списка партнеров карт лояльностей")
    void getLoyaltyCardPartners() {
        step("Запрос на получение списка партнеров", () -> {
            LoyaltyCardPartnersResponse response = loyaltyCardPartnersService.getLoyaltyCardPartnersList(
                    headers);

            step(
                    "Проверка, что ответ содержит партнеров",
                    () -> assertThat(response.getResult().getFirst().getId()).isNotNull());
        });
    }

    @Test
    
    @DisplayName("[loyalty_card.list] Получение списка карт лояльностей")
    void getLoyaltyCardList() {
        step("Запрос на получение списка карт", () -> {
            LoyaltyCardsListResponse response = loyaltyCardPartnersService.getLoyaltyCardsList(headers);

            step("Проверка, что ответ содержит список карт", () -> {
                assertThat(response.getResult().getFirst()).isNotNull();
                loyaltyCardId = response.getResult().getFirst().getId();
            });
        });
    }

    @Test
    
    @DisplayName("[loyalty_card.edit] Изменить карту лояльности")
    void editLoyaltyCard() {
        loyaltyCardTitle += " edited";
        step("Запрос на изменения карты лояльности", () -> {
            var params = LoyaltyCardsEditRequest.Params.builder()
                    .id(addedCard.getId())
                    .title(loyaltyCardTitle)
                    .code(addedCard.getCode())
                    .partnerId(addedCard.getPartnerId())
                    .build();

            ResponseWithOkResult response = loyaltyCardsService.editLoyaltyCard(params,
                    headers);

            step("Проверка, что ответ содержит ок текст", () -> assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
        step("Запрос на получение списка карт", () -> {
            LoyaltyCardsListResponse.Result editedCard =
                    loyaltyCardPartnersService.getLoyaltyCardByTitle(headers, loyaltyCardTitle);

            step("Проверка, что название карты изменено", () -> assertThat(editedCard.getTitle())
                    .isEqualTo(loyaltyCardTitle));
            loyaltyCardId = editedCard.getId();
        });
    }
}
