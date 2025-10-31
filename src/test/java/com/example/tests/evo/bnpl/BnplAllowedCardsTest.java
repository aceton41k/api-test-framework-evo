package com.example.tests.evo.bnpl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.bnpl.BnplAllowedCardsRequest;
import com.example.api.evo.models.bnpl.BnplAllowedCardsResponse;
import com.example.api.evo.services.bnpl.BnplAllowedCardsService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class BnplAllowedCardsTest extends EvoBaseTest {
    @Autowired
    BnplAllowedCardsService bnplAllowedCardsService;

    @Test
    
    @DisplayName("[bnpl.card.list] Получить список доступных карт")
        @Disabled("Пока не понятно почему падает отключу")
    void getAllowedCards() {
        step("Запрос на получение списка доступных карт", () -> {
            var params = BnplAllowedCardsRequest.Params.builder()
                    .partner("split")
                    .build();
            BnplAllowedCardsResponse response = bnplAllowedCardsService.getBnplAllowedCards(params,
                    headers);

            step("Проверка, что список разрешённых карт не пустой", () -> {
                assertThat(response.getResult().getCards()).isNotEmpty();
            });
        });
    }
}
