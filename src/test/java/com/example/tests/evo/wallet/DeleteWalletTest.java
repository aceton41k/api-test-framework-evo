package com.example.tests.evo.wallet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.constants.AccountData;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.card.GetCardsResponse;
import com.example.api.evo.models.wallet.CreateWalletRequest;
import com.example.api.evo.models.wallet.CreateWalletResponse;
import com.example.api.evo.models.wallet.DeleteWalletRequest;
import com.example.api.evo.services.card.EvoCardService;
import com.example.api.evo.services.wallet.EvoWalletService;
import com.example.tests.evo.EvoBaseTest;

import java.util.Optional;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeleteWalletTest extends EvoBaseTest {
    @Autowired
    EvoWalletService evoWalletService;

    @Autowired
    EvoCardService evoCardService;

    Long walletId;

    @BeforeEach
    void setUp() {
        Optional<GetCardsResponse.Result> wallet = evoCardService.getCards(GetCardsResponse.class, headers)
                .getResult()
                .stream()
                .filter(item -> item.getCardType().equals("WALLET"))
                .findFirst();
        if (wallet.isEmpty()) {
            var paramsForCreateWallet = CreateWalletRequest.Params.builder()
                    .birthdate(AccountData.XAS_BIRTH_DATE)
                    .build();
            CreateWalletResponse response = evoWalletService.createWallet(paramsForCreateWallet, headers);
            walletId = Long.valueOf(response.getResult().getAccountId());
        } else {
            walletId = wallet.get().getId();
        }
    }

    @Test
    
    @DisplayName("[wallet.delete] Проверка удаление кошелька")
    void deleteWalletTest() {
        step("Запрос на удаление кошелька по ид", () -> {
            var paramsDeleteWalletRequest =
                    DeleteWalletRequest.Params.builder().accountId(walletId).build();
            ResponseWithOkResult responseOfDeleteWallet =
                    evoWalletService.deleteWallet(paramsDeleteWalletRequest, headers);

            step("Проверка, что получили ок текст в result", () -> assertThat(responseOfDeleteWallet.getResult())
                    .isEqualTo("ok"));
        });
        step("Проверка, что кошелек отсутствует", () -> {
            Optional<GetCardsResponse.Result> wallet = evoCardService.getCards(GetCardsResponse.class, headers)
                    .getResult()
                    .stream()
                    .filter(item -> item.getCardType().equals("WALLET"))
                    .findFirst();
            assertThat(wallet.isEmpty()).isTrue();
        });
    }
}
