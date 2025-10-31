package com.example.tests.evo.identification_group;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.identification_group.GetIdentificationPaymentDataResponse;
import com.example.api.evo.services.identification_group.GetIdentificationPaymentDataService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;

public class IdentificationPaymentDataTest extends EvoBaseTest {

    @Autowired
    GetIdentificationPaymentDataService getIdentificationPaymentDataService;

    @Test
    
    @DisplayName("[identification.payment.data]Идентификация получения данных по платежу.]")
    void getIdentificationPaymentDataTest() {
        step("Запрос на получение идентификации данных по платежам", () -> {
            GetIdentificationPaymentDataResponse response = getIdentificationPaymentDataService.getIdentificationPaymentData(
                    headers);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult().getType()).isNotNull();
                soft.assertThat(response.getResult().getParameters().getAmount()).isNotNull();
                soft.assertThat(response.getResult().getService().getId()).isNotNull();
                soft.assertThat(response.getResult().getService().getName()).isNotNull();
                soft.assertThat(response.getResult().getService().getCategoryId()).isNotNull();
                soft.assertThat(response.getResult().getService().getStatus()).isNotNull();
                soft.assertThat(response.getResult().getService().getImage()).isNotNull();
                soft.assertThat(response.getResult().getService().getMaxLimit()).isNotNull();
                soft.assertThat(response.getResult().getService().getMinLimit()).isNotNull();
                soft.assertThat(response.getResult().getService().getCommissionPercent()).isNotNull();
                soft.assertThat(response.getResult().getService().getCardTypes()).isNotNull();
                soft.assertThat(response.getResult().getService().getMaintenance()).isFalse();
                soft.assertThat(response.getResult().getService().getDirect_payment()).isTrue();
                soft.assertThat(response.getResult().getService().getFavorite_permission()).isFalse();
                soft.assertThat(response.getResult().getService().getMyHomePermission()).isNull();
                soft.assertThat(response.getResult().getService().getCashbackLabel()).isNull();
                soft.assertThat(response.getResult().getService().getLabel()).isNull();
                soft.assertThat(response.getResult().getService().getQrOnly()).isFalse();
            });
        });
    }
}
