package com.example.tests.evo;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.constants.UserData;
import com.example.api.evo.models.identification.IdentificationRequest;
import com.example.api.evo.models.identification.IdentificationResponse;
import com.example.api.evo.models.identification.IdentificationVerifyRequest;
import com.example.api.evo.models.identification.PhotoFromCamera;
import com.example.api.evo.services.DataBaseService;
import com.example.api.evo.services.IdentificationService;
import com.example.tests.BaseTest;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled("Нужно дописать")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// TODO доделать проверки
public class IdentificationTests extends BaseTest {

    @Autowired
    DataBaseService dataBaseService;

    @Autowired
    IdentificationService identificationService;

    Map<String, String> headers1;

    UserData.User user;

    @BeforeAll
    void setUp() {
        user = userData.getUser("REG");
        String imei = user.getPhoneNumber() + "000";
        headers1 = operations.userRegister(user, imei).getEvoHeaders();
    }

    @AfterAll
    void afterAll() {
        dataBaseService.deleteUserRegistration(user.getPhoneNumber());
    }

    @Test
    
    @DisplayName("[identification.request] Идентификация")
    void identificationRequest() throws IOException {
        headers1.put("Accept-AppVersion", "8201");
        byte[] photo = Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                .getResourceAsStream("photo.jpeg.base64")).readAllBytes();
        step("Отправка запроса", () -> {
            IdentificationRequest.Params params = IdentificationRequest.Params.builder()
                    .birthDate("1990-12-12")
                    .pinfl("12345678901234")
                    .isResident(true)
                    .passData("AA0123456")
                    .photoFromCamera(new PhotoFromCamera(new String(photo)))
                    .build();
        });

    }

    @Test
    
    @DisplayName("[identification.verify] Идентификация")
    void identificationVerify() throws IOException {
        headers1.put("Accept-AppVersion", "8201");
        byte[] photo = Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                .getResourceAsStream("photo.jpeg.base64")).readAllBytes();
        step("Отправка запроса", () -> {
            IdentificationVerifyRequest.Params params = IdentificationVerifyRequest.Params.builder()
                    .photoFromCamera(new PhotoFromCamera(new String(photo)))
                    .build();

            IdentificationResponse response = identificationService.verify(params, headers1);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isNotBlank();
                assertThat(response.getResult().getResultCode()).as("result code").isNotNull();
            });
        });

    }
}
