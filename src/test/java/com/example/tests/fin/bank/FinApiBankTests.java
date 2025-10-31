package com.example.tests.fin.bank;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.fin.constants.CardType;
import com.example.api.fin.models.BanksResponse;
import com.example.api.fin.services.FinApiAuthService;
import com.example.tests.fin.FinBaseTest;

import static io.qameta.allure.Allure.step;
import static com.example.api.fin.constants.FinApiConstants.FIN_API_BASE_URI;

public class FinApiBankTests extends FinBaseTest {

    @Autowired
    FinApiAuthService finService;

    @BeforeAll
    void setUp() {
        webSession = operations.login(userData.getUser("XAS")).getWebSession();
    }

    @ParameterizedTest
    @EnumSource(CardType.class)
    
    @DisplayName("[/api/banks] GET Получение информации о банках по cardType")
    void getBankInformationByCardTypeTest(CardType cardType) {
        step("Запрос на получение банков", () -> {
            BanksResponse response = finService.getBanks(webSession, cardType);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getData())
                        .as("bank list for " + cardType)
                        .isNotEmpty()
                        .allSatisfy(bank -> {
                            soft.assertThat(bank.getName()).isNotBlank();
                            soft.assertThat(bank.getPhoneNumber()).isNotBlank();
                            soft.assertThat(bank.getId()).isNotEqualTo("0");
                            soft.assertThat(bank.getCardOfferEndpoint()).isNotBlank();
                            soft.assertThatUrl(FIN_API_BASE_URI + bank.getLogo()).isReachable();
                        });
            });

        });

    }
}
