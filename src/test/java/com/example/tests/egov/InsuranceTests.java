package com.example.tests.egov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.egov.models.ErrorResponse;
import com.example.api.egov.models.insurance.InsurancePolicyByGovNumberResponse;
import com.example.api.egov.services.InsuranceService;

import java.util.Map;

import static io.qameta.allure.Allure.step;

public class InsuranceTests extends EGovApiBaseTest {

    @Autowired
    InsuranceService insuranceService;


    @Test
    
    @DisplayName("[insurancePolicy/byPinfl][POST] Получить данные об страховке по PINFL")
    void getInsurancePolicyByPinfl() {
        step("Отправить запрос на получение данных о страховке", () -> {
            Map<String, String> pinfl = Map.of("pinfl", "12345678901234");
            var response = insuranceService.getInsurancePolicyByPinfl(pinfl);
            step("Проверка ответа", () -> {

                soft.assertThat(response.getError()).as("error").isEqualTo(0);
                soft.assertThat(response.getErrorMessage()).as("error message").isEmpty();

                var policy = response.getResult().getFirst();

                soft.assertThat(policy.getApplicantName()).as("applicant name").isEqualTo("USER BY PINFL");
                soft.assertThat(policy.getApplicantType()).as("applicant type").isEqualTo("PERSON");
                soft.assertThat(policy.getInsuranceOrgName()).as("insurance org").isEqualTo("Test Insurance");
                soft.assertThat(policy.getPolicySeria()).as("policy seria").isEqualTo("EFGH");
                soft.assertThat(policy.getPolicyNumber()).as("policy number").isEqualTo("99999");
                soft.assertThat(policy.getGovNumber()).as("gov number").isNull();
                soft.assertThat(policy.getPolicyType()).as("policy type").isNull();
                soft.assertThat(policy.getPolicyStartDate()).as("policy start date").isEqualTo("2023-01-01");
                soft.assertThat(policy.getPolicyEndDate()).as("policy end date").isEqualTo("2023-12-31");
                soft.assertThat(policy.getVehicleModel()).as("vehicle model").isEqualTo("COBALT");
                soft.assertThat(policy.getInsurancePremium()).as("insurance premium").isEqualTo("70000");
                soft.assertThat(policy.getInsuranceSum()).as("insurance sum").isEqualTo("50000000");
                soft.assertThat(policy.getDrivers()).as("drivers").isNull();
                soft.assertThat(policy.getAccidentData()).as("accident data").isEmpty();

            });
        });
    }

    @Test
    
    @DisplayName("[insurancePolicy/byInn][POST] Получить данные об страховке по INN")
    void getInsurancePolicyByINN() {
        step("Отправить запрос на получение данных о страховке", () -> {
            Map<String, String> inn = Map.of("inn", "123456789");
            var response = insuranceService.getInsurancePolicyByInn(inn);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getError()).as("error").isEqualTo(0);
                soft.assertThat(response.getErrorMessage()).as("error message").isEmpty();

                var policy = response.getResult().getFirst();

                soft.assertThat(policy.getApplicantName()).as("applicant name").isEqualTo("ООО ТЕСТ");
                soft.assertThat(policy.getApplicantType()).as("applicant type").isEqualTo("ORGANIZATION");
                soft.assertThat(policy.getInsuranceOrgName()).as("insurance org").isEqualTo("Biznes Sug'urta");
                soft.assertThat(policy.getPolicySeria()).as("policy seria").isEqualTo("WXYZ");
                soft.assertThat(policy.getPolicyNumber()).as("policy number").isEqualTo("111222");
                soft.assertThat(policy.getPolicyStartDate()).as("policy start date").isEqualTo("2022-05-01");
                soft.assertThat(policy.getPolicyEndDate()).as("policy end date").isEqualTo("2023-05-01");
                soft.assertThat(policy.getVehicleModel()).as("vehicle model").isEqualTo("NEXIA");
                soft.assertThat(policy.getInsurancePremium()).as("insurance premium").isEqualTo("80000");
                soft.assertThat(policy.getInsuranceSum()).as("insurance sum").isEqualTo("60000000");
                soft.assertThat(policy.getAccidentData()).as("accident data").isEmpty();

            });
        });
    }

    @Test
    
    @DisplayName("[insurancePolicy/byGovNumber][POST] Получить данные об страховке по govNumber")
    void getInsurancePolicyByGovNumber() {
        step("Отправить запрос на получение данных о страховке", () -> {
            Map<String, String> govNumber = Map.of("govNumber", "01X123BA");
            var response = insuranceService.getInsurancePolicyByGovNumber(govNumber,
                    InsurancePolicyByGovNumberResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getError()).as("error").isEqualTo(0);
                soft.assertThat(response.getErrorMessage()).as("error message").isEmpty();

                var policy = response.getResult().getFirst();

                soft.assertThat(policy.getApplicantName()).as("applicant name").isEqualTo("ESHMATOV ALI VALI O’G’LI");
                soft.assertThat(policy.getApplicantType()).as("applicant type").isEqualTo("PERSON");
                soft.assertThat(policy.getInsuranceOrgName()).as("insurance org").isEqualTo("\"Кapital Sug'urta\" AJ");
                soft.assertThat(policy.getPolicySeria()).as("policy seria").isEqualTo("ABCD");
                soft.assertThat(policy.getPolicyNumber()).as("policy number").isEqualTo("0120123");
                soft.assertThat(policy.getGovNumber()).as("gov number").isEqualTo("01X123BA");
                soft.assertThat(policy.getPolicyType()).as("policy type").isEqualTo("С ограничениями");
                soft.assertThat(policy.getPolicyStartDate()).as("policy start date").isEqualTo("2022-06-27");
                soft.assertThat(policy.getPolicyEndDate()).as("policy end date").isEqualTo("2023-06-26");
                soft.assertThat(policy.getVehicleModel()).as("vehicle model").isEqualTo("MATIZ");
                soft.assertThat(policy.getInsurancePremium()).as("insurance premium").isEqualTo("56000");
                soft.assertThat(policy.getInsuranceSum()).as("insurance sum").isEqualTo("40000000");

                soft.assertThat(policy.getDrivers()).as("drivers list").hasSize(1);
                var driver = policy.getDrivers().getFirst();
                soft.assertThat(driver.getPinfl()).as("driver pinfl").isEqualTo("12345678901234");
                soft.assertThat(driver.getFirstname()).as("driver firstname").isEqualTo("ESHMATOV");
                soft.assertThat(driver.getLastname()).as("driver lastname").isEqualTo("ALI");
                soft.assertThat(driver.getMiddlename()).as("driver middlename").isEqualTo("VALI O’G’LI");

                soft.assertThat(policy.getAccidentData()).as("accident data").isEmpty();
            });
        });
    }

    @Test
    
    @DisplayName("[insurancePolicy/byGovNumber][POST] Получить данные об страховке по не правильному govNumber TRIGGER500")
    void getInsurancePolicyByInvalidGovNumber() {
        step("Отправить запрос на получение данных о страховке", () -> {
            Map<String, String> govNumber = Map.of("govNumber", "TRIGGER500");
            var response = insuranceService.getInsurancePolicyByGovNumber(govNumber, ErrorResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getCode()).as("code").isEqualTo("egov_api_error");
                soft.assertThat(response.getMessage()).as("message").isEqualTo("Server error");
                soft.assertThat(response.getTarget()).as("target").isEqualTo("egov_server");
                soft.assertThat(response.getLocale().getMessage()).as("message").isNotNull();
                soft.assertThat(response.getLocale().getExtra()).as("extra").isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[insurancePolicy/byGovNumber][POST] Получить пустые данные об страховке по не правильному govNumber 99Z999ZZ")
    void getInsurancePolicyByInvalidGovNumberToGetEmptyResponse() {
        step("Отправить запрос на получение данных о страховке", () -> {
            Map<String, String> govNumber = Map.of("govNumber", "99Z999ZZ");
            var response = insuranceService.getInsurancePolicyByGovNumber(govNumber,
                    InsurancePolicyByGovNumberResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getError()).as("error").isEqualTo(0);
                soft.assertThat(response.getResult()).as("result").isEmpty();
                soft.assertThat(response.getErrorMessage()).as("error message").isEmpty();
            });
        });
    }


}
