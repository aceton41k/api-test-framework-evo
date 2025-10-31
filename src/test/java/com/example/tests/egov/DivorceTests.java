package com.example.tests.egov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.egov.models.ErrorResponse;
import com.example.api.egov.models.divorce.DivorceCertRequest;
import com.example.api.egov.models.divorce.DivorcePinflRequest;
import com.example.api.egov.models.divorce.DivorceRequest;
import com.example.api.egov.models.divorce.DivorceResponse;
import com.example.api.egov.services.DivorceService;

import static io.qameta.allure.Allure.step;

public class DivorceTests extends EGovApiBaseTest {

    @Autowired
    DivorceService divorceService;

    @Test
    
    @DisplayName("[/divorceCertificate/byFullNameAndBirthDate][POST] Получить данные об разводе по FIO")
    void getDivorceByFio() {
        step("Отправить запрос на получение данных о разводе", () -> {
            var request = DivorceRequest.builder().build();
            var response = divorceService.getDivorceByFio(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(1);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Malumotlar muvaffaqiyatli ishlandi");

                soft.assertThat(response.getItems()).as("items list").hasSize(1);

                var item = response.getItems().getFirst();

                soft.assertThat(item.getDocDate()).as("doc date").isEqualTo("01.01.2012");
                soft.assertThat(item.getBranch()).as("branch").isEqualTo(1017);
                soft.assertThat(item.getDocNum()).as("doc num").isEqualTo("9999");

                // husband
                soft.assertThat(item.getHFamily()).as("h family").isEqualTo("ESHMATOV");
                soft.assertThat(item.getHFamilyAfter()).as("h family after").isNull();
                soft.assertThat(item.getHFirstName()).as("h first name").isEqualTo("ALI");
                soft.assertThat(item.getHPatronym()).as("h patronym").isEqualTo("VALI O’G’LI");
                soft.assertThat(item.getHBirthDay()).as("h birth day").isEqualTo("25.01.1988");
                soft.assertThat(item.getHCertSeries()).as("h cert series").isEqualTo("I-TN");
                soft.assertThat(item.getHCertNumber()).as("h cert number").isEqualTo("111111");
                soft.assertThat(item.getHCertDate()).as("h cert date").isEqualTo("01.01.2012");
                soft.assertThat(item.getHPnfl()).as("h pnfl").isEqualTo("12345678901234");

                // wife
                soft.assertThat(item.getWFamily()).as("w family").isEqualTo("ESHMATOVA");
                soft.assertThat(item.getWFamilyAfter()).as("w family after").isNull();
                soft.assertThat(item.getWFirstName()).as("w first name").isEqualTo("MALIKA");
                soft.assertThat(item.getWPatronym()).as("w patronym").isEqualTo("AKBAROVНА");
                soft.assertThat(item.getWBirthDay()).as("w birth day").isEqualTo("25.06.1988");
                soft.assertThat(item.getWCertSeries()).as("w cert series").isEqualTo("I-TN");
                soft.assertThat(item.getWCertNumber()).as("w cert number").isEqualTo("222222");
                soft.assertThat(item.getWCertDate()).as("w cert date").isEqualTo("01.01.2012");
                soft.assertThat(item.getWPnfl()).as("w pnfl").isEqualTo("43210987654321");
            });
        });
    }

    @Test
    
    @DisplayName("[/divorceCertificate/byPinfl][POST] Получить данные об разводе по pinfl")
    void getDivorceByPinfl() {
        step("Отправить запрос на получение данных о разводе", () -> {
            var request = DivorcePinflRequest.builder().build();
            var response = divorceService.getDivorceByPinfl(request, DivorceResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(1);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Malumotlar muvaffaqiyatli ishlandi");

                soft.assertThat(response.getItems()).as("items list").hasSize(1);

                var item = response.getItems().getFirst();

                soft.assertThat(item.getDocDate()).as("doc date").isEqualTo("26.08.2015");
                soft.assertThat(item.getBranch()).as("branch").isEqualTo(1015);
                soft.assertThat(item.getDocNum()).as("doc num").isEqualTo("1234");

                // husband
                soft.assertThat(item.getHFamily()).as("h family").isEqualTo("ESHMATOV");
                soft.assertThat(item.getHFamilyAfter()).as("h family after").isNull();
                soft.assertThat(item.getHFirstName()).as("h first name").isEqualTo("ALI");
                soft.assertThat(item.getHPatronym()).as("h patronym").isEqualTo("VALI O’G’LI");
                soft.assertThat(item.getHBirthDay()).as("h birth day").isEqualTo("25.01.1988");
                soft.assertThat(item.getHCertSeries()).as("h cert series").isEqualTo("I-TN");
                soft.assertThat(item.getHCertNumber()).as("h cert number").isEqualTo("012345");
                soft.assertThat(item.getHCertDate()).as("h cert date").isEqualTo("26.08.2015");
                soft.assertThat(item.getHPnfl()).as("h pnfl").isEqualTo("12345678901234");

                // wife
                soft.assertThat(item.getWFamily()).as("w family").isEqualTo("ESHMATOVA");
                soft.assertThat(item.getWFamilyAfter()).as("w family after").isNull();
                soft.assertThat(item.getWFirstName()).as("w first name").isEqualTo("MALIKA");
                soft.assertThat(item.getWPatronym()).as("w patronym").isEqualTo("AKBAROVNA");
                soft.assertThat(item.getWBirthDay()).as("w birth day").isEqualTo("25.06.1988");
                soft.assertThat(item.getWCertSeries()).as("w cert series").isEqualTo("I-TN");
                soft.assertThat(item.getWCertNumber()).as("w cert number").isEqualTo("012346");
                soft.assertThat(item.getWCertDate()).as("w cert date").isEqualTo("26.08.2015");
                soft.assertThat(item.getWPnfl()).as("w pnfl").isEqualTo("43210987654321");
            });
        });
    }

    @Test
    
    @DisplayName("[/divorceCertificate/byPinfl][POST] Получить данные об разводе по cert")
    void getDivorceByCert() {
        step("Отправить запрос на получение данных о разводе", () -> {
            var request = DivorceCertRequest.builder().build();
            var response = divorceService.getDivorceByCert(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(1);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Malumotlar muvaffaqiyatli ishlandi");

                soft.assertThat(response.getItems()).as("items list").hasSize(1);

                var item = response.getItems().getFirst();

                soft.assertThat(item.getDocDate()).as("doc date").isEqualTo("10.05.2010");
                soft.assertThat(item.getBranch()).as("branch").isEqualTo(1016);
                soft.assertThat(item.getDocNum()).as("doc num").isEqualTo("5678");

                // husband
                soft.assertThat(item.getHFamily()).as("h family").isEqualTo("ESHMATOV");
                soft.assertThat(item.getHFamilyAfter()).as("h family after").isNull();
                soft.assertThat(item.getHFirstName()).as("h first name").isEqualTo("ALI");
                soft.assertThat(item.getHPatronym()).as("h patronym").isEqualTo("VALI O’Г’ЛИ");
                soft.assertThat(item.getHBirthDay()).as("h birth day").isEqualTo("25.01.1988");
                soft.assertThat(item.getHCertSeries()).as("h cert series").isEqualTo("I-TN");
                soft.assertThat(item.getHCertNumber()).as("h cert number").isEqualTo("1234567");
                soft.assertThat(item.getHCertDate()).as("h cert date").isEqualTo("10.05.2010");
                soft.assertThat(item.getHPnfl()).as("h pnfl").isEqualTo("12345678901234");

                // wife
                soft.assertThat(item.getWFamily()).as("w family").isEqualTo("ESHMATOVA");
                soft.assertThat(item.getWFamilyAfter()).as("w family after").isNull();
                soft.assertThat(item.getWFirstName()).as("w first name").isEqualTo("MALIKA");
                soft.assertThat(item.getWPatronym()).as("w patronym").isEqualTo("AKBAROVНА");
                soft.assertThat(item.getWBirthDay()).as("w birth day").isEqualTo("25.06.1988");
                soft.assertThat(item.getWCertSeries()).as("w cert series").isEqualTo("I-TN");
                soft.assertThat(item.getWCertNumber()).as("w cert number").isEqualTo("7654321");
                soft.assertThat(item.getWCertDate()).as("w cert date").isEqualTo("10.05.2010");
                soft.assertThat(item.getWPnfl()).as("w pnfl").isEqualTo("43210987654321");
            });
        });
    }

    @Test
    
    @DisplayName("[/divorceCertificate/byPinfl][POST] Получить данные об разводе по pinfl с не правильным ид")
    void getMarriageByInvalidPin() {
        step("Отправить запрос на получение данных о браке", () -> {
            var request = DivorcePinflRequest.builder().id("WAY_TOO_LONG_ID").build();
            var response = divorceService.getDivorceByPinfl(request, DivorceResponse.class);
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
    
    @DisplayName("[/divorceCertificate/byPinfl][POST] Получить данные об разводе по pinfl с не правильным ид для проверки 500")
    void getMarriageByInvalidPinToGet500() {
        step("Отправить запрос на получение данных о браке", () -> {
            var request = DivorcePinflRequest.builder().id("TRIGGER500").build();
            var response = divorceService.getDivorceByPinfl(request, ErrorResponse.class);
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
