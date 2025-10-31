package com.example.tests.evo.settings;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.constants.UserData;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.settings.RevokeDeviceRequest;
import com.example.api.evo.services.settings.DeviceListService;
import com.example.tests.evo.EvoBaseTest;

import java.util.Map;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class DeleteDevicesTests extends EvoBaseTest {
    @Autowired
    DeviceListService deviceListService;

    String secPhone;
    Map<String, String> secHeaders;

    @BeforeEach
    void setUp() {
        secPhone = UserData.generateUniquePhoneNumber();
        secHeaders = operations.userRegister(secPhone).getEvoHeaders();
    }

    @AfterEach
    void tearDown() {
        dataBaseService.deleteUserRegistration(secPhone);
    }

    @Test
    
    @DisplayName("[device.revoke] Удаление устройства по девайс ид")
    void revokeDevice() {
        String deviceId = step("Запрос на получение девайса", () -> deviceListService
                .getDeviceList(secHeaders)
                .getResult()
                .getFirst()
                .getDeviceId());
        step("Удаление девайса по девайс ид", () -> {
            var params = RevokeDeviceRequest.Params.builder()
                    .deviceId(deviceId)
                    .appType("evo")
                    .build();
            ResponseWithOkResult response = deviceListService.revokeDevice(params,
                   secHeaders);

            step("Проверка, что ответ содержит ок ответ", () -> assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
    }

    @Test
    
    @DisplayName("[device.revoke.all] Удаление всех девайсов из списка устройств")
    void revokeAllDevices() {
        var response = step("Удаление всех девайсов",
                () -> deviceListService.revokeAllDevice(secHeaders));
        step("Проверка, что ответ содержит ок ответ", () -> assertThat(response.getResult())
                .isEqualTo("ok"));
    }
}
