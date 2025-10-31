package com.example.tests.evo;

import com.example.api.evo.models.transfer_p2p.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uz.click.evo_api.models.transfer_p2p.*;
import com.example.api.evo.services.transfer_p2p.TransferService;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.api.evo.constants.AccountData.XAS_ACCOUNT_ID_1;
import static com.example.api.evo.constants.AccountData.XAS_ACCOUNT_ID_1_CARD_NUMBER_HASH;

public class TransferP2PTests extends EvoBaseTest {
    @Autowired
    TransferService transferService;

    @Test
    
    @DisplayName("[issuer.list] Получение списка банков и их данных по переводам")
    void getIssuerList() {
        IssuerListResponse response = step("[issuer.list] Получение списка банков и их данных по переводам",
                () -> transferService.getIssuerList(headers));
        step("Проверка ответа", () -> assertThat(response.getResult()).isNotNull());
    }

    @Test
    
    @DisplayName("[transfer.info] Получение информации о лимите переводов")
    @Disabled("Нужно добавление карты что бы запустить тест")
    void getTransferInfo() {
        step("[transfer.info] Получение информации о лимите переводов", () -> {
            TransferInfoRequest.Params params = TransferInfoRequest.Params.builder()
                    .accountId(XAS_ACCOUNT_ID_1)
                    .cardNumberHash(XAS_ACCOUNT_ID_1_CARD_NUMBER_HASH)
                    .build();
            TransferInfoResponse response = transferService.getTransferInfo(params, headers);

            step("Проверка ответа", () -> assertThat(response.getResult()).isNotNull());
        });
    }

    @Test
    
    @DisplayName("[transfer.confirmation.get] Получение подтверждение на перевод")
    void transferConfirmation() {
        GetTransferConfirmationResponse response = step(
                "[transfer.confirmation.get] Получение подтверждение на перевод", () ->
                        transferService.getTransferConfirmation(headers));
        step("Проверка ответа", () -> assertThat(response.getResult()).isNotNull());
    }

    @Test
    
    @DisplayName("[transfer.confirmation.update] Обновит получение подтверждение на перевод")
    void updateTransferConfirmationTrue() {
        step("[transfer.confirmation.update] Включить получение подтверждение на перевод", () -> {
            TransferConfirmationUpdateRequest.Params params = TransferConfirmationUpdateRequest.Params.builder()
                    .p2p_otp_enabled(true)
                    .build();
            GetTransferConfirmationResponse response = transferService.updateTransferConfirmation(params,
                    headers);

            step("Проверка ответа", () -> assertThat(response.getResult().isP2pOtpEnabled())
                    .isTrue());
        });
    }

    @Test
    
    @DisplayName("[transfer.confirmation.update] Отключить получение подтверждение на перевод")
    void updateTransferConfirmationFalse() {
        step("[transfer.confirmation.update] Отключить получение подтверждение на перевод", () -> {
            TransferConfirmationUpdateRequest.Params params = TransferConfirmationUpdateRequest.Params.builder()
                    .p2p_otp_enabled(false)
                    .build();
            GetTransferConfirmationResponse response = transferService.updateTransferConfirmation(params,
                    headers);

            step("Проверка ответа", () -> assertThat(response.getResult().isP2pOtpEnabled())
                    .isFalse());
        });
    }
}
