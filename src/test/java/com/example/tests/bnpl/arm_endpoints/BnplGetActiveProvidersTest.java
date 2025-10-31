package com.example.tests.bnpl.arm_endpoints;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.bnpl.services.BnplApiActiveProvidersService;
import com.example.tests.bnpl.BnplBaseTest;

import static io.qameta.allure.Allure.step;

public class BnplGetActiveProvidersTest extends BnplBaseTest {
    @Autowired
    BnplApiActiveProvidersService bnplApiActiveProvidersService;

    @Test
    
    @DisplayName("[/api/arm/v1/providers][GET]Успешное получение списка активных провайдеров")
    void getActiveProviders() {
        step("Отправка запроса на получение активных провайдеров", () -> {
            var activeProvidersResponse = bnplApiActiveProvidersService.getActiveProviders();

            step("Проверка схемы ответа", () -> {
                soft.assertThat(activeProvidersResponse).isNotNull();
                soft.assertThat(activeProvidersResponse).validateJsonSchema("json_schema/get_active_providers.json");
            });
        });
    }

}
