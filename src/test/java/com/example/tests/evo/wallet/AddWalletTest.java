package com.example.tests.evo.wallet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.wallet.CreateWalletRequest;
import com.example.api.evo.models.wallet.CreateWalletResponse;
import com.example.api.evo.services.wallet.EvoWalletService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.api.evo.constants.AccountData.XAS_BIRTH_DATE;


public class AddWalletTest extends EvoBaseTest {
    @Autowired
    EvoWalletService evoWalletService;

    @Test
    
    @DisplayName("[wallet.create] Создание кошелька")
    void createWalletTest() {
        step("Создание кошелька", () -> {
            var params = CreateWalletRequest.Params.builder()
                    .birthdate(XAS_BIRTH_DATE)
                    .build();
            CreateWalletResponse response = evoWalletService.createWallet(params, headers);

            step("Проверка ответа", () -> {
                assertThat(response.getResult().getCardNumber()).isNotNull();
                assertThat(response.getResult().getAccountId()).isNotNull();
            });
        });
    }
}
