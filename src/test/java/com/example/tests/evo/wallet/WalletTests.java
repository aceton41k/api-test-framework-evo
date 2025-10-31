package com.example.tests.evo.wallet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.wallet.WalletRatesResponse;
import com.example.api.evo.services.wallet.EvoWalletService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WalletTests extends EvoBaseTest {
    @Autowired
    EvoWalletService evoWalletService;

    @Test
    
    @DisplayName("[wallet.rates] Тарифы по кошельку")
    void getWalletRatesTest() {
        step("Запрос на тарифы кошелька", () -> {
            WalletRatesResponse response = evoWalletService.getWalletRates(headers);

            step("Проверка, что получили result", () -> {
                assertThat(response.getResult().get(0).getCode()).isEqualTo("light");
                assertThat(response.getResult().get(1).getCode()).isEqualTo("premium");
            });
        });
    }
}
