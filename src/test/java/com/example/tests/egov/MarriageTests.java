package com.example.tests.egov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.egov.models.marriage.MarriageCertRequest;
import com.example.api.egov.models.marriage.MarriagePinflRequest;
import com.example.api.egov.models.marriage.MarriageRequest;
import com.example.api.egov.services.MarriageService;

import static io.qameta.allure.Allure.step;

public class MarriageTests extends EGovApiBaseTest {

    @Autowired
    MarriageService marriageService;

    @Test
    
    @DisplayName("[/marriageCertificate/byFullNameAndBirthDate][POST] Получить данные об браке по FIO")
    void getMarriageByFio() {
        step("Отправить запрос на получение данных о браке", () -> {
            var request = MarriageRequest.builder().build();
            var response = marriageService.getMarriageByFio(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(1);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Malumotlar muvaffaqiyatli ishlandi");

                soft.assertThat(response.getItems()).as("items").hasSize(1);
                var item = response.getItems().getFirst();

                soft.assertThat(item.getDocDate()).as("doc_date").isEqualTo("11.12.2013");
                soft.assertThat(item.getBranch()).as("branch").isEqualTo(1010);
                soft.assertThat(item.getCertDate()).as("cert_date").isEqualTo("11.12.2013");
                soft.assertThat(item.getHBirthDay()).as("h_birth_day").isEqualTo("20.01.1990");
                soft.assertThat(item.getWBirthDay()).as("w_birth_day").isEqualTo("21.12.1990");
                soft.assertThat(item.getDocNum()).as("doc_num").isEqualTo("30303");
                soft.assertThat(item.getCertSeries()).as("cert_series").isEqualTo("I-TN");
                soft.assertThat(item.getCertNumber()).as("cert_number").isEqualTo("1234567");

                // муж
                soft.assertThat(item.getHFamily()).as("h_family").isEqualTo("ESHMATОВ");
                soft.assertThat(item.getHFamilyAfter()).as("h_family_after").isEqualTo("ESHMATОВ");
                soft.assertThat(item.getHFirstName()).as("h_first_name").isEqualTo("ALI");
                soft.assertThat(item.getHPatronym()).as("h_patronym").isEqualTo("VALI O’G’LI");
                soft.assertThat(item.getHAddress()).as("h_address").isEqualTo("O`ZBEKISTON");
                soft.assertThat(item.getHSitizen()).as("h_sitizen").isEqualTo("O`ZBEKISTON");
                soft.assertThat(item.getHPnfl()).as("h_pnfl").isEqualTo("12345678901234");

                // жена
                soft.assertThat(item.getWFamily()).as("w_family").isEqualTo("МУХТОРОВА");
                soft.assertThat(item.getWFamilyAfter()).as("w_family_after").isEqualTo("ЭШМАТОВА");
                soft.assertThat(item.getWFirstName()).as("w_first_name").isEqualTo("МАЛИКА");
                soft.assertThat(item.getWPatronym()).as("w_patronym").isEqualTo("ТОШМАТОВНА");
                soft.assertThat(item.getWAddress()).as("w_address").isEqualTo("O`ЗBEKISTON");
                soft.assertThat(item.getWSitizen()).as("w_sitizen").isEqualTo("O`ЗBEKISTON");
                soft.assertThat(item.getWPnfl()).as("w_pnfl").isEqualTo("98765432109876");
            });
        });
    }


    @Test
    
    @DisplayName("[/marriageCertificate/certNumber][POST] Получить данные об браке по cert")
    void getMarriageByCert() {
        step("Отправить запрос на получение данных о браке", () -> {
            var request = MarriageCertRequest.builder().build();
            var response = marriageService.getMarriageByCert(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(1);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Malumotlar muvaffaqiyatli ishlandi");

                soft.assertThat(response.getItems()).as("items").hasSize(1);
                var item = response.getItems().getFirst();

                soft.assertThat(item.getDocDate()).as("doc_date").isEqualTo("11.12.2013");
                soft.assertThat(item.getBranch()).as("branch").isEqualTo(1010);
                soft.assertThat(item.getCertDate()).as("cert_date").isEqualTo("11.12.2013");
                soft.assertThat(item.getHBirthDay()).as("h_birth_day").isEqualTo("20.01.1990");
                soft.assertThat(item.getWBirthDay()).as("w_birth_day").isEqualTo("21.12.1990");
                soft.assertThat(item.getDocNum()).as("doc_num").isEqualTo("20202");
                soft.assertThat(item.getCertSeries()).as("cert_series").isEqualTo("I-TN");
                soft.assertThat(item.getCertNumber()).as("cert_number").isEqualTo("1234567");

                // муж
                soft.assertThat(item.getHFamily()).as("h_family").isNotNull();
                soft.assertThat(item.getHFamilyAfter()).as("h_family_after").isNotNull();
                soft.assertThat(item.getHFirstName()).as("h_first_name").isEqualTo("ALI");
                soft.assertThat(item.getHPatronym()).as("h_patronym").isEqualTo("VALI O’G’LI");
                soft.assertThat(item.getHAddress()).as("h_address").isNotNull();
                soft.assertThat(item.getHSitizen()).as("h_sitizen").isNotNull();
                soft.assertThat(item.getHPnfl()).as("h_pnfl").isEqualTo("12345678901234");

                // жена
                soft.assertThat(item.getWFamily()).as("w_family").isEqualTo("МУХТОРОВА");
                soft.assertThat(item.getWFamilyAfter()).as("w_family_after").isEqualTo("ЭШМАТОВА");
                soft.assertThat(item.getWFirstName()).as("w_first_name").isEqualTo("МАЛИКА");
                soft.assertThat(item.getWPatronym()).as("w_patronym").isEqualTo("ТОШМАТОВНА");
                soft.assertThat(item.getWAddress()).as("w_address").isNotNull();
                soft.assertThat(item.getWSitizen()).as("w_sitizen").isNotNull();
                soft.assertThat(item.getWPnfl()).as("w_pnfl").isEqualTo("98765432109876");
            });
        });
    }

    @Test
    
    @DisplayName("[/marriageCertificate/byPinfl][POST] Получить данные об браке по pinfl")
    void getMarriageByPin() {
        step("Отправить запрос на получение данных о браке", () -> {
            var request = MarriagePinflRequest.builder().build();
            var response = marriageService.getMarriageByPinfl(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(1);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Malumotlar muvaffaqiyatli ishlandi");

                soft.assertThat(response.getItems()).as("items").hasSize(1);
                var item = response.getItems().getFirst();

                soft.assertThat(item.getDocDate()).as("doc_date").isEqualTo("11.12.2013");
                soft.assertThat(item.getBranch()).as("branch").isEqualTo(1010);
                soft.assertThat(item.getCertDate()).as("cert_date").isEqualTo("11.12.2013");
                soft.assertThat(item.getHBirthDay()).as("h_birth_day").isEqualTo("20.01.1990");
                soft.assertThat(item.getWBirthDay()).as("w_birth_day").isEqualTo("21.12.1990");
                soft.assertThat(item.getDocNum()).as("doc_num").isEqualTo("10101");
                soft.assertThat(item.getCertSeries()).as("cert_series").isEqualTo("I-TN");
                soft.assertThat(item.getCertNumber()).as("cert_number").isEqualTo("1234567");

                // муж
                soft.assertThat(item.getHFamily()).as("h_family").isNotNull();
                soft.assertThat(item.getHFamilyAfter()).as("h_family_after").isNotNull();
                soft.assertThat(item.getHFirstName()).as("h_first_name").isEqualTo("ALI");
                soft.assertThat(item.getHPatronym()).as("h_patronym").isEqualTo("VALI O’G’LI");
                soft.assertThat(item.getHAddress()).as("h_address").isNotNull();
                soft.assertThat(item.getHSitizen()).as("h_sitizen").isNotNull();
                soft.assertThat(item.getHPnfl()).as("h_pnfl").isEqualTo("12345678901234");

                // жена
                soft.assertThat(item.getWFamily()).as("w_family").isEqualTo("МУХТОРОВА");
                soft.assertThat(item.getWFamilyAfter()).as("w_family_after").isEqualTo("ЭШМАТОВА");
                soft.assertThat(item.getWFirstName()).as("w_first_name").isEqualTo("МАЛИКА");
                soft.assertThat(item.getWPatronym()).as("w_patronym").isEqualTo("ТОШМАТОВНА");
                soft.assertThat(item.getWAddress()).as("w_address").isNotNull();
                soft.assertThat(item.getWSitizen()).as("w_sitizen").isNotNull();
                soft.assertThat(item.getWPnfl()).as("w_pnfl").isEqualTo("98765432109876");
            });
        });
    }

    @Test
    
    @DisplayName("[/marriageCertificate/byPinfl][POST] Получить данные об браке по pinfl с не правильным ид")
    void getMarriageByInvalidPin() {
        step("Отправить запрос на получение данных о браке", () -> {
            var request = MarriagePinflRequest.builder().id("WAY_TOO_LONG_ID").build();
            var response = marriageService.getMarriageByPinfl(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getId()).as("id").isEqualTo("111");
                soft.assertThat(response.getResultCode()).as("result code").isEqualTo(5);
                soft.assertThat(response.getResultMessage()).as("result message")
                    .isEqualTo("Неправильный формат данных");

                soft.assertThat(response.getItems()).as("items").hasSize(0);

            });
        });
    }

    // TODO надо будет прописать мок на 500 и тест тоже

}
