package com.example.tests.egov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.egov.models.passport_info.PassportInfoPinBirthRequest;
import com.example.api.egov.models.passport_info.PassportInfoPinSerialBirthRequest;
import com.example.api.egov.models.passport_info.PassportInfoPinSerialRequest;
import com.example.api.egov.models.passport_info.PassportInfoResponse;
import com.example.api.egov.services.PassportInfoService;

import static io.qameta.allure.Allure.step;

public class PassportInfoTests extends EGovApiBaseTest {

    @Autowired
    PassportInfoService passportInfoService;

    @Test
    
    @DisplayName("[/passportInfo/byPinflAndSeriaNumber][POST] Получить данные о паспорте по pinfl и serialNumber")
    void getPassportInfoByPinflSerialNumber() {
        step("Отправить запрос на получение данных о паспорте", () -> {
            var request = PassportInfoPinSerialRequest.builder().build();
            var response = passportInfoService.getPassportInfoByPinflSerialNumber(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult()).as("result").isEqualTo("1");
                soft.assertThat(response.getComments()).as("comments").isEmpty();
                soft.assertThat(response.getData()).as("data list").hasSize(1);

                var data = response.getData().getFirst();

                soft.assertThat(data.getPinpps()).as("pinpps").containsExactly("12345678901234");
                soft.assertThat(data.getSurnameLat()).as("surnameLat").isEqualTo("TESTOV");
                soft.assertThat(data.getNameLat()).as("nameLat").isEqualTo("TEST");
                soft.assertThat(data.getPatronymLat()).as("patronymLat").isEqualTo("TESTOVICH");
                soft.assertThat(data.getSurnameCyr()).as("surnameCyr").isEqualTo("ТЕСТОВ");
                soft.assertThat(data.getNameCyr()).as("nameCyr").isEqualTo("ТЕСТ");
                soft.assertThat(data.getPatronymCyr()).as("patronymCyr").isEqualTo("ТЕСТОВИЧ");
                soft.assertThat(data.getBirthplace()).as("birthplace").isEqualTo("O‘RTACHIRCHIQ TUMANI");
                soft.assertThat(data.getBirthCountry()).as("birthCountry").isEqualTo("УЗБЕКИСТАН");
                soft.assertThat(data.getBirthCountryId()).as("birthCountryId").isEqualTo(182);
                soft.assertThat(data.getLiveStatus()).as("liveStatus").isEqualTo(1);
                soft.assertThat(data.getNationality()).as("nationality").isEqualTo("КАЗАХ/КАЗАШКА");
                soft.assertThat(data.getNationalityId()).as("nationalityId").isEqualTo(19);
                soft.assertThat(data.getCitizenship()).as("citizenship").isEqualTo("УЗБЕКИСТАН");
                soft.assertThat(data.getCitizenshipId()).as("citizenshipId").isEqualTo(182);
                soft.assertThat(data.getSex()).as("sex").isEqualTo(1);
                soft.assertThat(data.getTransactionId()).as("transaction_id").isEqualTo(3);
                soft.assertThat(data.getCurrentPinpp()).as("current_pinpp").isEqualTo("12345678901234");
                soft.assertThat(data.getCurrentDocument()).as("current_document").isEqualTo("AA1234567");
                soft.assertThat(data.getBirthDate()).as("birth_date").isEqualTo("1997-07-08");

                soft.assertThat(data.getDocuments()).as("documents list").hasSize(2);

                var doc1 = data.getDocuments().get(0);
                soft.assertThat(doc1.getDocument()).as("doc1.document").isEqualTo("AA7654321");
                soft.assertThat(doc1.getType()).as("doc1.type").isEqualTo("IDMS_RECV_CITIZ_DOCUMENTS");
                soft.assertThat(doc1.getDocGivePlace()).as("doc1.docgiveplace")
                    .isEqualTo("УРТАЧИРЧИКСКИЙ РОВД ТАШКЕНТСКОЙ ОБЛАСТИ");
                soft.assertThat(doc1.getDocGivePlaceId()).as("doc1.docgiveplaceid").isEqualTo(27253);
                soft.assertThat(doc1.getDateBegin()).as("doc1.datebegin").isEqualTo("2014-06-28");
                soft.assertThat(doc1.getDateEnd()).as("doc1.dateend").isEqualTo("2024-06-27");
                soft.assertThat(doc1.getStatus()).as("doc1.status").isEqualTo(4);

                var doc2 = data.getDocuments().get(1);
                soft.assertThat(doc2.getDocument()).as("doc2.document").isEqualTo("AA7654321");
                soft.assertThat(doc2.getType()).as("doc2.type").isEqualTo("IDMS_RECV_MVD_IDCARD_CITIZEN");
                soft.assertThat(doc2.getDocGivePlace()).as("doc2.docgiveplace")
                    .isEqualTo("ЦГУ ЯШНАБАДСКОГО РАЙОНА Г. ТАШКЕНТА");
                soft.assertThat(doc2.getDocGivePlaceId()).as("doc2.docgiveplaceid").isEqualTo(60013);
                soft.assertThat(doc2.getDateBegin()).as("doc2.datebegin").isEqualTo("2023-07-25");
                soft.assertThat(doc2.getDateEnd()).as("doc2.dateend").isEqualTo("2033-07-24");
                soft.assertThat(doc2.getStatus()).as("doc2.status").isEqualTo(2);
            });
        });
    }

    @Test
    
    @DisplayName("[/passportInfo/byPinflAndSeriaNumberAndBirthDate][POST] Получить данные о паспорте по pinfl, serialNumber и дате рождении")
    void getPassportInfoByPinflSerialNumberBirthDate() {
        step("Отправить запрос на получение данных о паспорте", () -> {
            var request = PassportInfoPinSerialBirthRequest.builder().build();
            var response = passportInfoService.getPassportInfoByPinflSerialNumberBirth(request);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult()).as("result").isEqualTo("1");
                soft.assertThat(response.getComments()).as("comments").isEmpty();
                soft.assertThat(response.getData()).as("data list").hasSize(1);

                var data = response.getData().getFirst();

                soft.assertThat(data.getPinpps()).as("pinpps").containsExactly("12345678901234");
                soft.assertThat(data.getDocuments()).as("documents").isNull();

                soft.assertThat(data.getSurnameLat()).as("surnamelat").isEqualTo("IVANOV");
                soft.assertThat(data.getNameLat()).as("namelat").isEqualTo("IVAN");
                soft.assertThat(data.getPatronymLat()).as("patronymlat").isEqualTo("IVANOVICH");

                soft.assertThat(data.getSurnameCyr()).as("surnamecyr").isNull();
                soft.assertThat(data.getNameCyr()).as("namecyr").isNull();
                soft.assertThat(data.getPatronymCyr()).as("patronymcyr").isNull();
                soft.assertThat(data.getEngSurname()).as("engsurname").isNull();
                soft.assertThat(data.getEngName()).as("engname").isNull();

                soft.assertThat(data.getBirthplace()).as("birthplace").isEqualTo("TOSHKENT");
                soft.assertThat(data.getBirthCountry()).as("birthcountry").isEqualTo("УЗБЕКИСТАН");
                soft.assertThat(data.getBirthCountryId()).as("birthcountryid").isNull();
                soft.assertThat(data.getLiveStatus()).as("livestatus").isNull();
                soft.assertThat(data.getNationality()).as("nationality").isNull();
                soft.assertThat(data.getNationalityId()).as("nationalityid").isNull();
                soft.assertThat(data.getCitizenship()).as("citizenship").isEqualTo("УЗБЕКИСТАН");
                soft.assertThat(data.getCitizenshipId()).as("citizenshipid").isNull();

                soft.assertThat(data.getSex()).as("sex").isEqualTo(1);
                soft.assertThat(data.getPhoto()).as("photo").isNotEmpty();
                soft.assertThat(data.getTransactionId()).as("transaction_id").isEqualTo(3);
                soft.assertThat(data.getCurrentPinpp()).as("current_pinpp").isEqualTo("12345678901234");
                soft.assertThat(data.getCurrentDocument()).as("current_document").isEqualTo("AA1234567");
                soft.assertThat(data.getBirthDate()).as("birth_date").isEqualTo("1995-07-08");
            });
        });
    }

    @Test
    
    @DisplayName("[/passportInfo/byPinflAndBirthDate][POST] Получить данные о паспорте по pinfl и дате рождении")
    void getPassportInfoByPinflBirthDate() {
        step("Отправить запрос на получение данных о паспорте", () -> {
            var request = PassportInfoPinBirthRequest.builder().build();
            var response = passportInfoService.getPassportInfoByPinAndBirth(request, PassportInfoResponse.class);
            step("Проверка ответа", () -> {

                soft.assertThat(response.getResult()).as("result").isEqualTo("1");
                soft.assertThat(response.getComments()).as("comments").isEmpty();
                soft.assertThat(response.getData()).as("data list").hasSize(1);

                var data = response.getData().getFirst();

                soft.assertThat(data.getPinpps()).as("pinpps").containsExactly("12345678901234");
                soft.assertThat(data.getDocuments()).as("documents").isNull();

                soft.assertThat(data.getSurnameLat()).as("surnamelat").isEqualTo("ALI");
                soft.assertThat(data.getNameLat()).as("namelat").isEqualTo("ESH");
                soft.assertThat(data.getPatronymLat()).as("patronymlat").isEqualTo("VALI");

                soft.assertThat(data.getSurnameCyr()).as("surnamecyr").isNull();
                soft.assertThat(data.getNameCyr()).as("namecyr").isNull();
                soft.assertThat(data.getPatronymCyr()).as("patronymcyr").isNull();
                soft.assertThat(data.getEngSurname()).as("engsurname").isNull();
                soft.assertThat(data.getEngName()).as("engname").isNull();

                soft.assertThat(data.getBirthplace()).as("birthplace").isEqualTo("SAMARQAND");
                soft.assertThat(data.getBirthCountry()).as("birthcountry").isEqualTo("УЗБЕКИСТАН");
                soft.assertThat(data.getBirthCountryId()).as("birthcountryid").isNull();
                soft.assertThat(data.getLiveStatus()).as("livestatus").isNull();
                soft.assertThat(data.getNationality()).as("nationality").isNull();
                soft.assertThat(data.getNationalityId()).as("nationalityid").isNull();
                soft.assertThat(data.getCitizenship()).as("citizenship").isEqualTo("УЗБЕКИСТАН");
                soft.assertThat(data.getCitizenshipId()).as("citizenshipid").isNull();

                soft.assertThat(data.getSex()).as("sex").isEqualTo(1);
                soft.assertThat(data.getPhoto()).as("photo").isNotEmpty();
                soft.assertThat(data.getTransactionId()).as("transaction_id").isEqualTo(3);
                soft.assertThat(data.getCurrentPinpp()).as("current_pinpp").isEqualTo("12345678901234");
                soft.assertThat(data.getCurrentDocument()).as("current_document").isEqualTo("AA1234567");
                soft.assertThat(data.getBirthDate()).as("birth_date").isEqualTo("1995-07-08");
            });
        });
    }

    // TODO добавить кейсы 500

}
