package com.example.tests.egov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.egov.models.ErrorResponse;
import com.example.api.egov.models.birth.BirthCertRequest;
import com.example.api.egov.models.birth.BirthPinflRequest;
import com.example.api.egov.models.birth.BirthRequest;
import com.example.api.egov.models.birth.BirthResponse;
import com.example.api.egov.services.BirthService;

import static io.qameta.allure.Allure.step;

public class BirthTests extends EGovApiBaseTest {

    @Autowired
    BirthService birthService;

    @Test
    
    @DisplayName("[/birthCertificate/byFullNameAndBirthDate][POST] Получить данные об рождении по FIO")
    void getBirthByFio() {
        step("Отправить запрос на получение данных о рождении", () -> {
            var request = BirthRequest.builder().build();
            var response = birthService.getBirthByFio(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(1);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Malumotlar muvaffaqiyatli ishlandi");

                soft.assertThat(response.getItems()).as("items list").hasSize(1);

                var item = response.getItems().getFirst();

                soft.assertThat(item.getPnfl()).as("pnfl").isEqualTo("55555555555555");
                soft.assertThat(item.getSurname()).as("surname").isEqualTo("ESHMATOV");
                soft.assertThat(item.getName()).as("name").isEqualTo("ALI");
                soft.assertThat(item.getPatronym()).as("patronym").isEqualTo("VALI O’GLI");
                soft.assertThat(item.getDocDate()).as("doc date").isEqualTo("11.03.2018");
                soft.assertThat(item.getBranch()).as("branch").isEqualTo(1012);
                soft.assertThat(item.getCertBirthDate()).as("cert birth date").isEqualTo("11.03.2018");
                soft.assertThat(item.getBirthDate()).as("birth date").isEqualTo("10.03.2018");
                soft.assertThat(item.getGenderCode()).as("gender code").isEqualTo(1);
                soft.assertThat(item.getDocNum()).as("doc num").isEqualTo("9999");
                soft.assertThat(item.getCertSeries()).as("cert series").isEqualTo("I-TN");
                soft.assertThat(item.getCertNumber()).as("cert number").isEqualTo("7654321");
            });
        });
    }

    @Test
    
    @DisplayName("[/birthCertificate/byPinfl][POST] Получить данные об рождении по pinfl")
    void getBirthByPinfl() {
        step("Отправить запрос на получение данных о рождении", () -> {
            var request = BirthPinflRequest.builder().build();
            var response = birthService.getBirthByPinfl(request, BirthResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(1);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Malumotlar muvaffaqiyatli ishlandi");

                soft.assertThat(response.getItems()).as("items list").hasSize(1);

                var item = response.getItems().getFirst();

                soft.assertThat(item.getPnfl()).as("pnfl").isEqualTo("12345678901234");
                soft.assertThat(item.getSurname()).as("surname").isEqualTo("ESHMATOV");
                soft.assertThat(item.getName()).as("name").isEqualTo("ALI");
                soft.assertThat(item.getPatronym()).as("patronym").isEqualTo("VALI O’GLI");
                soft.assertThat(item.getDocDate()).as("doc date").isEqualTo("16.01.2018");
                soft.assertThat(item.getBranch()).as("branch").isEqualTo(1010);
                soft.assertThat(item.getCertBirthDate()).as("cert birth date").isEqualTo("16.01.2018");
                soft.assertThat(item.getBirthDate()).as("birth date").isEqualTo("15.01.2018");
                soft.assertThat(item.getGenderCode()).as("gender code").isEqualTo(1);
                soft.assertThat(item.getDocNum()).as("doc num").isEqualTo("1234");
                soft.assertThat(item.getCertSeries()).as("cert series").isEqualTo("I-TN");
                soft.assertThat(item.getCertNumber()).as("cert number").isEqualTo("1234567");
            });
        });
    }


    @Test
    
    @DisplayName("[/birthCertificate/byCertNumber][POST] Получить данные об рождении по cert")
    void getBirthByCert() {
        step("Отправить запрос на получение данных о рождении", () -> {
            var request = BirthCertRequest.builder().build();
            var response = birthService.getBirthByCert(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(1);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Malumotlar muvaffaqiyatli ishlandi");

                soft.assertThat(response.getItems()).as("items list").hasSize(1);

                var item = response.getItems().getFirst();

                soft.assertThat(item.getPnfl()).as("pnfl").isEqualTo("43210987654321");
                soft.assertThat(item.getSurname()).as("surname").isEqualTo("TOSHMATOV");
                soft.assertThat(item.getName()).as("name").isEqualTo("AKBAR");
                soft.assertThat(item.getPatronym()).as("patronym").isEqualTo("ALI O‘G‘LI");
                soft.assertThat(item.getDocDate()).as("doc date").isEqualTo("03.02.2019");
                soft.assertThat(item.getBranch()).as("branch").isEqualTo(1011);
                soft.assertThat(item.getCertBirthDate()).as("cert birth date").isEqualTo("03.02.2019");
                soft.assertThat(item.getBirthDate()).as("birth date").isEqualTo("02.02.2019");
                soft.assertThat(item.getGenderCode()).as("gender code").isEqualTo(1);
                soft.assertThat(item.getDocNum()).as("doc num").isEqualTo("5678");
                soft.assertThat(item.getCertSeries()).as("cert series").isEqualTo("TA");
                soft.assertThat(item.getCertNumber()).as("cert number").isEqualTo("1234567");
            });
        });
    }

    @Test
    
    @DisplayName("[/birthCertificate/byPinfl][POST] Получить данные о рождении по pinfl с не правильным ид")
    void getBirthByInvalidPin() {
        step("Отправить запрос на получение данных о рождении", () -> {
            var request = BirthPinflRequest.builder().id("WAY_TOO_LONG_ID").build();
            var response = birthService.getBirthByPinfl(request, BirthResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(5);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Неправильный формат данных");

                soft.assertThat(response.getItems()).as("items").hasSize(0);

            });
        });
    }

    @Test
    
    @DisplayName("[/birthCertificate/byPinfl][POST] Получить данные о рождении по pinfl с не правильным ид для проверки 500")
    void getBirthByInvalidPinToGet500() {
        step("Отправить запрос на получение данных о рождении", () -> {
            var request = BirthPinflRequest.builder().id("TRIGGER500").build();
            var response = birthService.getBirthByPinfl(request, ErrorResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getCode()).as("code").isEqualTo("egov_api_error");
                soft.assertThat(response.getMessage()).as("message").isEqualTo("Server error");
                soft.assertThat(response.getTarget()).as("target").isEqualTo("egov_server");
                soft.assertThat(response.getLocale()).as("locale").isNotNull();
                soft.assertThat(response.getLocale().getMessage()).as("locale.message").isNotBlank();
                soft.assertThat(response.getLocale().getExtra()).as("locale.extra").isNotBlank();
            });
        });
    }

}
