package com.example.tests.evo.registration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.constants.UserData;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.registration.CallIvrRequest;
import com.example.api.evo.models.registration.DeviceRegisterRequest;
import com.example.api.evo.models.registration.DeviceRegisterResponse;
import com.example.api.evo.models.registration.GetRegionListResponse;
import com.example.api.evo.services.DeviceService;
import com.example.api.evo.services.registration.AdditionalRegistrationService;
import com.example.jupiter.annotations.SkipAuthSetup;
import com.example.tests.evo.EvoBaseTest;

import java.util.Map;
import java.util.stream.Collectors;

import static io.qameta.allure.Allure.step;
import static java.util.Map.entry;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AdditionalRegistrationTests extends EvoBaseTest {
    @Autowired
    AdditionalRegistrationService additionalRegistrationService;
    @Autowired
    DeviceService deviceService;

    @Test
    
    @DisplayName("[get.region.list] Получение списка регионов")
    void getRegionList() {
        step(
                "Запрос на получение списка регионов [get.region.list]",
                () -> {
                    GetRegionListResponse response = additionalRegistrationService.getRegionList(headers);

                    step("Проверка ответа что пришли города", () -> {
                        Map<String, String> responseRegions = response.getResult().stream()
                                .collect(Collectors.toMap(GetRegionListResponse.Result::getCode,
                                        GetRegionListResponse.Result::getName));
                        Map<String, String> regions = Map.ofEntries(
                                entry("26", "г.Ташкент"),
                                entry("03", "Андижан"),
                                entry("06", "Бухара"),
                                entry("08", "Джизак"),
                                entry("10", "Кашкадарья"),
                                entry("12", "Навои"),
                                entry("14", "Наманган"),
                                entry("18", "Самарканд"),
                                entry("22", "Сурхандарья"),
                                entry("24", "Сырдарья"),
                                entry("27", "Ташкентская обл."),
                                entry("30", "Фергана"),
                                entry("33", "Хорезм"),
                                entry("35", "Республика Каракалпакстан"));
                        assertThat(regions).isEqualTo(responseRegions);
                    });
                });

    }

    @Test
    
    @DisplayName("[call.ivr] Ivr - Позвони мне")
    @SkipAuthSetup
    void callIvr() {
        step("Запрос на [call.ivr]", () -> {
            String phone = UserData.generateUniquePhoneNumber();
            String imei = phone + "000";
            DeviceRegisterRequest.Params deviceRegisterParams = DeviceRegisterRequest.Params.builder()
                    .phoneNumber(phone)
                    .deviceInfo("Iphone " + imei)
                    .deviceName("Iphone " + imei)
                    .imei(imei)
                    .build();
            DeviceRegisterResponse deviceRegisterResponse = deviceService.deviceRegister(deviceRegisterParams);
            Map<String, String> deviceId = Map.of("device-id", deviceRegisterResponse.getResult().getDeviceId());
            CallIvrRequest.Params params =
                    CallIvrRequest.Params.builder().type(0).build();
            ResponseWithOkResult responseOfCallIvr = additionalRegistrationService.callIvr(params, deviceId);

            step("Проверка ответа получили ок ответ с result", () -> {
                assertThat(responseOfCallIvr.getResult()).isEqualTo("ok");
            });
        });
    }
}
