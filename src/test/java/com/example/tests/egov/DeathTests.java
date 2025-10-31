package com.example.tests.egov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.egov.models.ErrorResponse;
import com.example.api.egov.models.death.DeathCertRequest;
import com.example.api.egov.models.death.DeathPinflRequest;
import com.example.api.egov.models.death.DeathRequest;
import com.example.api.egov.models.death.DeathResponse;
import com.example.api.egov.services.DeathService;

import static io.qameta.allure.Allure.step;

public class DeathTests extends EGovApiBaseTest {

    @Autowired
    DeathService deathService;

    @Test
    
    @DisplayName("[/deathCertificate/byFullNameAndBirthDate][POST] Получить данные о смерти по FIO")
    void getDeathByFio() {
        step("Отправить запрос на получение данных о смерти", () -> {
            var request = DeathRequest.builder().build();
            var response = deathService.getDeathByFio(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(1);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Malumotlar muvaffaqiyatli ishlandi");

                soft.assertThat(response.getItems()).as("items list").hasSize(1);

                var item = response.getItems().getFirst();

                soft.assertThat(item.getDocDate()).as("doc date").isEqualTo("01.01.2000");
                soft.assertThat(item.getBranch()).as("branch").isEqualTo(1005);
                soft.assertThat(item.getCertDeathDate()).as("cert death date").isEqualTo("01.01.2000");
                soft.assertThat(item.getPnfl()).as("pnfl").isEqualTo("55555555555555");
                soft.assertThat(item.getSurname()).as("surname").isEqualTo("QODIROV");
                soft.assertThat(item.getName()).as("name").isEqualTo("JASUR");
                soft.assertThat(item.getPatronym()).as("patronym").isEqualTo("XASAN O‘G‘LI");
                soft.assertThat(item.getBirthDate()).as("birth date").isEqualTo("11.11.1911");
                soft.assertThat(item.getDocNum()).as("doc num").isEqualTo("D3003");
                soft.assertThat(item.getCertSeries()).as("cert series").isEqualTo("I-TN");
                soft.assertThat(item.getCertNumber()).as("cert number").isEqualTo("3333333");
                soft.assertThat(item.getDeathPlace()).as("death place").isEqualTo("BUXORO");
                soft.assertThat(item.getBirthPlace()).as("birth place").isEqualTo("BUXORO");
                soft.assertThat(item.getCitizenCountry()).as("citizen country").isEqualTo("O‘ZBEKISTON");
                soft.assertThat(item.getGenderCode()).as("gender code").isEqualTo(1);
                soft.assertThat(item.getDeathDate()).as("death date").isEqualTo("05.01.2000");
            });
        });
    }

    @Test
    
    @DisplayName("[/deathCertificate/byPinfl][POST] Получить данные о смерти по pinfl")
    void getDeathByPinfl() {
        step("Отправить запрос на получение данных о смерти", () -> {
            var request = DeathPinflRequest.builder().build();
            var response = deathService.getDeathByPinfl(request, DeathResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(1);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Malumotlar muvaffaqiyatli ishlandi");

                soft.assertThat(response.getItems()).as("items list").hasSize(1);

                var item = response.getItems().getFirst();

                soft.assertThat(item.getDocDate()).as("doc date").isEqualTo("25.07.2010");
                soft.assertThat(item.getBranch()).as("branch").isEqualTo(1003);
                soft.assertThat(item.getCertDeathDate()).as("cert death date").isEqualTo("25.07.2010");
                soft.assertThat(item.getPnfl()).as("pnfl").isEqualTo("12345678901234");
                soft.assertThat(item.getSurname()).as("surname").isEqualTo("ESHMATOV");
                soft.assertThat(item.getName()).as("name").isEqualTo("ALI");
                soft.assertThat(item.getPatronym()).as("patronym").isEqualTo("VALI O’G’LI");
                soft.assertThat(item.getBirthDate()).as("birth date").isEqualTo("22.03.1918");
                soft.assertThat(item.getDocNum()).as("doc num").isEqualTo("D1001");
                soft.assertThat(item.getCertSeries()).as("cert series").isEqualTo("I-TN");
                soft.assertThat(item.getCertNumber()).as("cert number").isEqualTo("1234567");
                soft.assertThat(item.getDeathPlace()).as("death place").isEqualTo("TOSHKENT");
                soft.assertThat(item.getBirthPlace()).as("birth place").isEqualTo("TOSHKENT");
                soft.assertThat(item.getCitizenCountry()).as("citizen country").isEqualTo("O‘ZBEKISTON");
                soft.assertThat(item.getGenderCode()).as("gender code").isEqualTo(1);
                soft.assertThat(item.getDeathDate()).as("death date").isEqualTo("24.07.2010");
            });
        });
    }

    @Test
    
    @DisplayName("[/deathCertificate/byCert][POST] Получить данные о смерти по cert")
    void getDeathByCert() {
        step("Отправить запрос на получение данных о смерти", () -> {
            var request = DeathCertRequest.builder().build();
            var response = deathService.getDeathByCert(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(1);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Malumotlar muvaffaqiyatli ishlandi");

                soft.assertThat(response.getItems()).as("items list").hasSize(1);

                var item = response.getItems().getFirst();

                soft.assertThat(item.getDocDate()).as("doc date").isEqualTo("10.05.2011");
                soft.assertThat(item.getBranch()).as("branch").isEqualTo(1004);
                soft.assertThat(item.getCertDeathDate()).as("cert death date").isEqualTo("10.05.2011");
                soft.assertThat(item.getPnfl()).as("pnfl").isEqualTo("43210987654321");
                soft.assertThat(item.getSurname()).as("surname").isEqualTo("TOSHMATOV");
                soft.assertThat(item.getName()).as("name").isEqualTo("AKBAR");
                soft.assertThat(item.getPatronym()).as("patronym").isEqualTo("ALI O‘G‘LI");
                soft.assertThat(item.getBirthDate()).as("birth date").isEqualTo("12.07.1920");
                soft.assertThat(item.getDocNum()).as("doc num").isEqualTo("D2002");
                soft.assertThat(item.getCertSeries()).as("cert series").isEqualTo("I-TN");
                soft.assertThat(item.getCertNumber()).as("cert number").isEqualTo("7654321");
                soft.assertThat(item.getDeathPlace()).as("death place").isEqualTo("SAMARKAND");
                soft.assertThat(item.getBirthPlace()).as("birth place").isEqualTo("SAMARKAND");
                soft.assertThat(item.getCitizenCountry()).as("citizen country").isEqualTo("O‘ZBEKISTON");
                soft.assertThat(item.getGenderCode()).as("gender code").isEqualTo(1);
                soft.assertThat(item.getDeathDate()).as("death date").isEqualTo("15.05.2011");
            });
        });
    }


    @Test
    
    @DisplayName("[/deathCertificate/byPinfl][POST] Получить данные о смерти по pinfl с не правильным ид")
    void getDeathByInvalidPin() {
        step("Отправить запрос на получение данных о смерти", () -> {
            var request = DeathPinflRequest.builder().id("WAY_TOO_LONG_ID").build();
            var response = deathService.getDeathByPinfl(request, DeathResponse.class);
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
    
    @DisplayName("[/deathCertificate/byPinfl][POST] Получить данные о смерти по pinfl с не правильным ид для проверки 500")
    void getDeathByInvalidPinToGet500() {
        step("Отправить запрос на получение данных о смерти", () -> {
            var request = DeathPinflRequest.builder().id("TRIGGER500").build();
            var response = deathService.getDeathByPinfl(request, ErrorResponse.class);
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
