package com.example.tests.egov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.egov.services.DriverLicenceService;

import java.util.Map;

import static io.qameta.allure.Allure.step;

public class DriverLicenceTests extends EGovApiBaseTest {

    @Autowired
    DriverLicenceService driverLicenceService;

    @Test
    
    @DisplayName("[/driverLicence/byPinflAndPassportData][POST] Получить данные о удостоверений")
    void getDriverLicence() {
        step("Отправить запрос на получение данных о удостоверений", () -> {
            var request = Map.of("pRequestID", "Query56", "pSery", "AA", "pNumber", "7654321", "applicantPinpp",
                    "12345678901234");
            var response = driverLicenceService.getDriverLicence(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getPOwnerDate()).as("pOwner_date").isEqualTo("30.01.1996");
                soft.assertThat(response.getPBegin()).as("pBegin").isEqualTo("22.02.2019");
                soft.assertThat(response.getPEnd()).as("pEnd").isEqualTo("21.02.2029");
                soft.assertThat(response.getPResult()).as("pResult").isEqualTo(1);
                soft.assertThat(response.getPComment()).as("pComment").isEqualTo("Ok");
                soft.assertThat(response.getPPinpp()).as("pPinpp").isEqualTo("12345678901234");
                soft.assertThat(response.getPDoc()).as("pDoc").isEqualTo("AA7654321");
                soft.assertThat(response.getPOwner()).as("pOwner").isEqualTo("ESHMATOV ALI VALI O’G’LI");
                soft.assertThat(response.getPIssuedBy()).as("pIssuedBy").isNull();
                soft.assertThat(response.getPSerialNumber()).as("pSerialNumber").isEqualTo("AA9876543");

                soft.assertThat(response.getCategories()).as("categories").hasSize(1);

                var cat = response.getCategories().getFirst();
                soft.assertThat(cat.getPBegin()).as("category pBegin").isEqualTo("05.04.2012");
                soft.assertThat(cat.getPEnd()).as("category pEnd").isEqualTo("21.02.2029");
                soft.assertThat(cat.getPCategory()).as("category").isEqualTo("B");
                soft.assertThat(cat.getPComment()).as("category comment").isNull();
            });
        });
    }

    //TODO добавить кейсы


}
