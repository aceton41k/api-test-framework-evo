package com.example.tests.bnpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.bnpl.models.CardsAvailableResponse;
import com.example.api.bnpl.services.BnplApiCardsAvailableService;

import static io.qameta.allure.Allure.step;
import static com.example.api.bnpl.constants.BnplConstants.NIK_CLIENT_ID;
import static com.example.api.bnpl.constants.BnplConstants.SPLIT_PROVIDER;

public class BnplCardsAvailableTest extends BnplBaseTest {
    @Autowired
    BnplApiCardsAvailableService bnplApiCardListService;

    @Test
    
    @DisplayName("[/api/internal/v1/accounts/available][GET] Успешное получение списка доступных accountId (Банковских карт)")
    void getCardsAvailableListTest() {
        step("Запрос на получение массива карт", () -> {
            CardsAvailableResponse response = bnplApiCardListService.getCardsAvailableList(NIK_CLIENT_ID, SPLIT_PROVIDER);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getStatus())
                        .isEqualTo("Success");
                soft.assertThat(response.getMessage())
                        .isEqualTo("Accounts list are provided.");
                soft.assertThat(response.getData().getListAccIds())
                        .isNotEmpty()
                        .allSatisfy(id -> {
                            soft.assertThat(id).isPositive();
                        });
                soft.assertThat(response.getData().getPartner())
                        .isEqualTo(SPLIT_PROVIDER);
            });
        });
    }
}
