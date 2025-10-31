package com.example.tests.ofd.autopayment_ms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.autopayments.autopayment_ms.CreateAutoPayRequest;
import com.example.api.autopayments.autopayment_ms.CreateAutoPayResponse;
import com.example.api.autopayments.services.AutoPaymentMSService;
import com.example.tests.BaseTest;

import java.util.Map;
import java.util.UUID;

import static io.qameta.allure.Allure.step;

public class CreateAutoPayment extends BaseTest {

    @Autowired
    AutoPaymentMSService autoPaymentMSService;


    @Test
    
    @DisplayName("[v2/autopay][POST] Создание автоплатежа")
    void createAutoPayment() {
        var request = CreateAutoPayRequest.builder().build();
        Map<String, String> headers = Map.of("clientId", "1", "X-Idempotency-Key", UUID.randomUUID().toString());

        step("Отправить запрос на создание автоплатежа", () -> {
            var response = autoPaymentMSService.createAutoPayment(request, headers, CreateAutoPayResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).isNotNull();
                soft.assertThat(response.getType()).isEqualTo(3);
                soft.assertThat(response.getDatetime()).isNotNull();
                soft.assertThat(response.getServiceId()).isEqualTo(5);
                soft.assertThat(response.getName()).isEqualTo("Оплата телефона");
                soft.assertThat(response.getAmount()).isEqualByComparingTo("1001.0000");
                soft.assertThat(response.getCntrgParamId()).isEqualTo(1);
                soft.assertThat(response.getCntrgParam2()).isEqualTo("977734075");
                soft.assertThat(response.getCntrgParam3()).isEqualTo("3");
                soft.assertThat(response.getCntrgParam4()).isEqualTo("4");
                soft.assertThat(response.getCntrgParam5()).isEqualTo("5");
                soft.assertThat(response.getStartDate()).isNull();
                soft.assertThat(response.getDaysIntervalAmount()).isNull();
                soft.assertThat(response.getPaytime()).isEqualTo("19:00").matches("^([01]\\d|2[0-3]):[0-5]\\d$");
                soft.assertThat(response.getWeeklyDay()).isNull();
                soft.assertThat(response.getMonthlyDay()).isEqualTo(15);
                soft.assertThat(response.getStep()).isNull();
                soft.assertThat(response.getAccountId()).isEqualTo(12345678);
                soft.assertThat(response.getStatus()).isEqualTo(1);
            });
            step("Проверка по ид автоплатежа", () -> {
                Integer autoPayId = response.getId();
                Integer autoPayType = 1;
                Map<String, String> headersForAutoPay = Map.of("clientId", "1");
                Map<String, Object> params = Map.of("autopayId", autoPayId, "autopayType", autoPayType);
                var autoPayResponse = autoPaymentMSService.getAutoPayment(params, headersForAutoPay,
                        CreateAutoPayResponse.class);
                soft.assertThat(autoPayResponse.getId()).isEqualTo(response.getId());
                soft.assertThat(autoPayResponse.getType()).isEqualTo(3);
                soft.assertThat(autoPayResponse.getDatetime()).isNotNull();
                soft.assertThat(autoPayResponse.getServiceId()).isEqualTo(5);
                soft.assertThat(autoPayResponse.getName()).isEqualTo("Оплата телефона");
                soft.assertThat(autoPayResponse.getAmount()).isEqualByComparingTo("1001.0000");
                soft.assertThat(autoPayResponse.getCntrgParamId()).isEqualTo(1);
                soft.assertThat(autoPayResponse.getCntrgParam2()).isEqualTo("977734075");
                soft.assertThat(autoPayResponse.getCntrgParam3()).isEqualTo("3");
                soft.assertThat(autoPayResponse.getCntrgParam4()).isEqualTo("4");
                soft.assertThat(autoPayResponse.getCntrgParam5()).isEqualTo("5");
                soft.assertThat(autoPayResponse.getStartDate()).isNull();
                soft.assertThat(autoPayResponse.getDaysIntervalAmount()).isNull();
                soft.assertThat(autoPayResponse.getPaytime()).isEqualTo("19:00").matches("^([01]\\d|2[0-3]):[0-5]\\d$");
                soft.assertThat(autoPayResponse.getWeeklyDay()).isNull();
                soft.assertThat(autoPayResponse.getMonthlyDay()).isEqualTo(15);
                soft.assertThat(autoPayResponse.getStep()).isNull();
                soft.assertThat(autoPayResponse.getAccountId()).isEqualTo(12345678);
                soft.assertThat(autoPayResponse.getStatus()).isEqualTo(1);
            });
        });

    }
}
