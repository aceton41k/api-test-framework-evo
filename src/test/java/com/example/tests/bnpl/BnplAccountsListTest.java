package com.example.tests.bnpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.bnpl.models.AccountsListResponse;
import com.example.api.bnpl.services.BnplApiAccountsListService;

import static io.qameta.allure.Allure.step;
import static com.example.api.bnpl.constants.BnplConstants.NIK_CLIENT_ID;

public class BnplAccountsListTest extends BnplBaseTest{
    @Autowired
    BnplApiAccountsListService bnplApiAccountsListService;

    @Test
    
    @DisplayName("[/api/bnpl/partner/v1/accounts/list][GET] Успешное получение списка доступных карт пользователя")
    void getAccountsList() {
        step("Запрос на получение карт пользователя", () -> {
            AccountsListResponse response = bnplApiAccountsListService.getAccountsList(NIK_CLIENT_ID);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getStatus()).isEqualTo("Success");
                soft.assertThat(response.getMessage()).isEqualTo("Accounts retrieved");
                soft.assertThat(response.getData().getAccounts()).isNotEmpty();

                var account = response.getData().getAccounts().getFirst();

                soft.assertThat(account.getId()).isNotNull();
                soft.assertThat(account.getName()).isNotEmpty();
                soft.assertThat(account.getCardNumber()).isNotEmpty();
                soft.assertThat(account.getExpireDate()).isNotEmpty();
                soft.assertThat(account.getStatus()).isNotNull();
                soft.assertThat(account.getStatusText()).isNotEmpty();
                soft.assertThat(account.getType()).isNotEmpty();
                soft.assertThat(account.getIsDefault()).isTrue();
                soft.assertThat(account.getHolder()).isNotEmpty();
                soft.assertThat(account.getCurrencyCode()).isNotEmpty();
                soft.assertThat(account.getMiniLogoUrl()).isNotEmpty();
                soft.assertThat(account.getLogoUrl()).isNotEmpty();
                soft.assertThat(account.getMonitoringStatus()).isNotNull();
                soft.assertThat(account.getMonitoringAvailable()).isTrue();
            });
        });
    }
}
