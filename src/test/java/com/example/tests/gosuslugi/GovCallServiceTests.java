package com.example.tests.gosuslugi;

import com.example.api.gosuslugi.models.call_service.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uz.click.gov_api.models.call_service.*;
import com.example.api.gosuslugi.models.login.GovApiLoginResponse;
import com.example.api.gosuslugi.services.GovGetCallService;

import java.util.Map;

import static io.qameta.allure.Allure.step;

public class GovCallServiceTests extends GovApiBaseTest {
    @Autowired
    GovGetCallService govGetCallService;

    String pinfl, serie;

    @BeforeAll
    void setUp() {
        GovApiLoginResponse loginResponse = govOperations.govLogin(userData.getUser("OLG"));
        token = loginResponse.getToken();
        webSession = loginResponse.getWebSession();
        serie = loginResponse.getUser().getPassport().getSerie();
        pinfl = loginResponse.getUser().getPassport().getPinfl();
    }

    @Test
    
    @DisplayName("[/callService] POST Успешное получение ИНН")
        void getInnServiceTest() {
        step("Формируем тело запроса", () -> {
            var request = CallServiceRequest.builder()
                    .organization("mygov")
                    .serviceId(9) // Справка об Определении ИНН
                    .pin(pinfl)
                    .passport(serie)
                    .webSession(webSession)
                    .fields(Map.of("citizen", "1"))
                    .build();

            step("Выполняем запрос /callService", () -> {
                GetInnResponse response = govGetCallService.callService(
                        request,
                        GetInnResponse.class,
                        token
                );

                step("Проверка ответа", () -> {
                    soft.assertThat(response.getResponse()).as("response").isNotNull();
                    soft.assertThat(response.getResponse().getSuccess())
                            .as("response.success")
                            .isTrue();
                    soft.assertThat(response.getResponse().getData())
                            .as("response.data")
                            .isNotNull();
                    soft.assertThat(response.getResponse().getFile())
                            .as("response.file").isNotNull();
                    soft.assertThat(response.getResponse().getFile().getHas())
                            .as("response.file.has").isEqualTo("YES");
                    soft.assertThat(response.getResponse().getFile().getId())
                            .as("response.file.id").isPositive();
                    var data = response.getResponse().getData();
                    soft.assertThat(data.getTin()).as("data.tin").isNotEmpty();
                    soft.assertThat(data.getNs10Code()).as("data.ns10Code").isNotNull();
                    soft.assertThat(data.getNs10Name()).as("data.ns10Name").isNotEmpty();
                    soft.assertThat(data.getNs10NameUz()).as("data.ns10NameUz").isNotEmpty();
                    soft.assertThat(data.getNs10NameRu()).as("data.ns10NameRu").isNotEmpty();
                    soft.assertThat(data.getNs11Code()).as("data.ns11Code").isNotNull();
                    soft.assertThat(data.getNs11Name()).as("data.ns11Name").isNotEmpty();
                    soft.assertThat(data.getNs11NameUz()).as("data.ns11NameUz").isNotEmpty();
                    soft.assertThat(data.getNs11NameRu()).as("data.ns11NameRu").isNotEmpty();
                    soft.assertThat(data.getSurName()).as("data.surName").isNotEmpty();
                    soft.assertThat(data.getFirstName()).as("data.firstName").isNotEmpty();
                    soft.assertThat(data.getMiddleName()).as("data.middleName").isNotEmpty();
                    soft.assertThat(data.getBirthDate()).as("data.birthDate").isNotEmpty();
                    soft.assertThat(data.getSex()).as("data.sex").isNotNull();
                    soft.assertThat(data.getSexName()).as("data.sexName").isNotEmpty();
                    soft.assertThat(data.getPassSeries()).as("data.passSeries").isNotEmpty();
                    soft.assertThat(data.getPassNumber()).as("data.passNumber").isNotEmpty();
                    soft.assertThat(data.getPassDate()).as("data.passDate").isNotEmpty();
                    soft.assertThat(data.getPassOrg()).as("data.passOrg").isNotEmpty();
                    soft.assertThat(data.getAddress()).as("data.address").isNotEmpty();
                    soft.assertThat(data.getNs13Code()).as("data.ns13Code").isNotNull();
                    soft.assertThat(data.getNs13Name()).as("data.ns13Name").isNotEmpty();
                    soft.assertThat(data.getTinDate()).as("data.tinDate").isNotEmpty();
                    soft.assertThat(data.getDateModify()).as("data.dateModify").isNotEmpty();
                    soft.assertThat(response.getResponse().getData().getDuty())
                            .as("response.data.duty")
                            .isIn(true, false);
                    soft.assertThat(data.getPersonalNum())
                            .as("data.personalNum")
                            .isNotEmpty();
                    soft.assertThat(data.getDocCode()).as("data.docCode").isNotEmpty();
                    soft.assertThat(data.getDocName()).as("data.docName").isNotEmpty();
                    var translations = response.getTranslations();
                    soft.assertThat(translations.get("tin").getNameRu())
                            .as("translations.tin.name_ru")
                            .isEqualTo("ИНН");
                    soft.assertThat(translations.get("tin").getNameEn())
                            .as("translations.tin.name_en")
                            .isEqualTo("TIN");
                    soft.assertThat(translations.get("tin").getNameUz())
                            .as("translations.tin.name_uz")
                            .isEqualTo("STIR");
                    soft.assertThat(translations.get("tin").getIsTitle())
                            .as("translations.tin.is_title")
                            .isIn(true, false);
                    soft.assertThat(translations.get("tin").getIsCopied())
                            .as("translations.tin.is_copied")
                            .isIn(true, false);
                    soft.assertThat(translations.get("firstName").getNameRu())
                            .as("translations.firstName.name_ru")
                            .isEqualTo("Имя");
                    soft.assertThat(translations.get("firstName").getNameEn())
                            .as("translations.firstName.name_en")
                            .isEqualTo("Firstname");
                    soft.assertThat(translations.get("firstName").getNameUz())
                            .as("translations.firstName.name_uz")
                            .isEqualTo("Ism");
                    soft.assertThat(translations.get("firstName").getIsTitle())
                            .as("translations.firstName.is_title")
                            .isIn(true, false);
                    soft.assertThat(translations.get("firstName").getIsCopied())
                            .as("translations.firstName.is_copied")
                            .isIn(true, false);
                    soft.assertThat(translations.get("surName").getNameRu())
                            .as("translations.surName.name_ru")
                            .isEqualTo("Фамилия");
                    soft.assertThat(translations.get("surName").getNameEn())
                            .as("translations.surName.name_en")
                            .isEqualTo("Surname");
                    soft.assertThat(translations.get("surName").getNameUz())
                            .as("translations.surName.name_uz")
                            .isEqualTo("Familiya");
                    soft.assertThat(translations.get("surName").getIsTitle())
                            .as("translations.surName.is_title")
                            .isIn(true, false);
                    soft.assertThat(translations.get("surName").getIsCopied())
                            .as("translations.surName.is_copied")
                            .isIn(true, false);
                    soft.assertThat(translations.get("middleName").getNameRu())
                            .as("translations.middleName.name_ru")
                            .isEqualTo("Отчество");
                    soft.assertThat(translations.get("middleName").getNameEn())
                            .as("translations.middleName.name_en")
                            .isEqualTo("Middlename");
                    soft.assertThat(translations.get("middleName").getNameUz())
                            .as("translations.middleName.name_uz")
                            .isEqualTo("Otasining ismi");
                    soft.assertThat(translations.get("middleName").getIsTitle())
                            .as("translations.middleName.is_title")
                            .isIn(true, false);
                    soft.assertThat(translations.get("middleName").getIsCopied())
                            .as("translations.middleName.is_copied")
                            .isIn(true, false);
                    soft.assertThat(translations.get("passSeries").getNameRu())
                            .as("translations.passSeries.name_ru")
                            .isEqualTo("Серия паспорта");
                    soft.assertThat(translations.get("passSeries").getNameEn())
                            .as("translations.passSeries.name_en")
                            .isEqualTo("Passport series");
                    soft.assertThat(translations.get("passSeries").getNameUz())
                            .as("translations.passSeries.name_uz")
                            .isEqualTo("Pasport seriya");
                    soft.assertThat(translations.get("passSeries").getIsTitle())
                            .as("translations.passSeries.is_title")
                            .isIn(true, false);
                    soft.assertThat(translations.get("passSeries").getIsCopied())
                            .as("translations.passSeries.is_copied")
                            .isIn(true, false);
                    soft.assertThat(translations.get("passNumber").getNameRu())
                            .as("translations.passNumber.name_ru")
                            .isEqualTo("Номер паспорта");
                    soft.assertThat(translations.get("passNumber").getNameEn())
                            .as("translations.passNumber.name_en")
                            .isEqualTo("Passport series");
                    soft.assertThat(translations.get("passNumber").getNameUz())
                            .as("translations.passNumber.name_uz")
                            .isEqualTo("Pasport raqami");
                    soft.assertThat(translations.get("passNumber").getIsTitle())
                            .as("translations.passNumber.is_title")
                            .isIn(true, false);
                    soft.assertThat(translations.get("passNumber").getIsCopied())
                            .as("translations.passNumber.is_copied")
                            .isIn(true, false);
                    soft.assertThat(translations.get("personalNum").getNameRu())
                            .as("translations.personalNum.name_ru")
                            .isEqualTo("ПИНФЛ");
                    soft.assertThat(translations.get("personalNum").getNameEn())
                            .as("translations.personalNum.name_en")
                            .isEqualTo("PINFL");
                    soft.assertThat(translations.get("personalNum").getNameUz())
                            .as("translations.personalNum.name_uz")
                            .isEqualTo("JSHSHIR");
                    soft.assertThat(translations.get("personalNum").getIsTitle())
                            .as("translations.personalNum.is_title")
                            .isIn(true, false);
                    soft.assertThat(translations.get("personalNum").getIsCopied())
                            .as("translations.personalNum.is_copied")
                            .isIn(true, false);
                    soft.assertThat(translations.get("tinDate").getNameRu())
                            .as("translations.tinDate.name_ru")
                            .isEqualTo("Дата выдачи ИНН");
                    soft.assertThat(translations.get("tinDate").getNameEn())
                            .as("translations.tinDate.name_en")
                            .isEqualTo("Date of issue of the TIN");
                    soft.assertThat(translations.get("tinDate").getNameUz())
                            .as("translations.tinDate.name_uz")
                            .isEqualTo("STIR berilgan sana");
                    soft.assertThat(translations.get("tinDate").getIsTitle())
                            .as("translations.tinDate.is_title")
                            .isIn(true, false);
                    soft.assertThat(translations.get("tinDate").getIsCopied())
                            .as("translations.tinDate.is_copied")
                            .isIn(true, false);
                });
            });
        });
    }

    @Test
    
    @DisplayName("[/callService] POST Успешное получение услуги Справка о постоянном месте проживания")
        void getProofOfResidenceServiceTest() {
        step("Формируем тело запроса", () -> {
            var request = BaseCallServiceRequest.builder()
                    .organization("mygov")
                    .serviceId(4) // Справка о постоянном месте проживания
                    .pin(pinfl)
                    .passport(serie)
                    .webSession(webSession)
                    .build();

            step("Выполняем запрос /callService", () -> {
                GetProofOfResidenceResponse response = govGetCallService.callService(
                        request,
                        GetProofOfResidenceResponse.class,
                        token
                );

                step("Проверка ответа", () -> {
                    soft.assertThat(response.getResponse()).as("response").isNotNull();
                    soft.assertThat(response.getResponse().getSuccess()).as("response.success").isTrue();
                    var file = response.getResponse().getFile();
                    soft.assertThat(file).as("response.file").isNotNull();
                    soft.assertThat(file.getHas()).as("response.file.has").isEqualTo("YES");
                    soft.assertThat(file.getId()).as("response.file.id").isPositive();
                    soft.assertThat(response.getResponse().getData()).as("response.data").isNotNull();
                    soft.assertThat(response.getResponse().getData().getPermanentRegistration()).as("data.permanentRegistration").isNotNull();
                    var reg = response.getResponse().getData().getPermanentRegistration();
                    soft.assertThat(reg.getCadastre()).as("cadastre").isNotEmpty();
                    soft.assertThat(reg.getCountry()).as("country").isNotNull();
                    soft.assertThat(reg.getRegion()).as("region").isNotNull();
                    soft.assertThat(reg.getDistrict()).as("district").isNotNull();
                    soft.assertThat(reg.getMaxalla()).as("maxalla").isNotNull();
                    soft.assertThat(reg.getStreet()).as("street").isNotNull();
                    soft.assertThat(reg.getAddress()).as("address").isNotEmpty();
                    soft.assertThat(reg.getRegistrationDate()).as("registrationDate").isNotEmpty();
                    soft.assertThat(reg.getCountry().getId()).as("country.id").isNotNull();
                    soft.assertThat(reg.getCountry().getValue()).as("country.value").isNotEmpty();
                    soft.assertThat(reg.getCountry().getIdValue()).as("country.idValue").isNotEmpty();
                    soft.assertThat(reg.getRegion().getId()).as("region.id").isNotNull();
                    soft.assertThat(reg.getRegion().getValue()).as("region.value").isNotEmpty();
                    soft.assertThat(reg.getRegion().getIdValue()).as("region.idValue").isNotEmpty();
                    soft.assertThat(reg.getDistrict().getId()).as("district.id").isNotNull();
                    soft.assertThat(reg.getDistrict().getValue()).as("district.value").isNotEmpty();
                    soft.assertThat(reg.getDistrict().getIdValue()).as("district.idValue").isNotEmpty();
                    soft.assertThat(reg.getMaxalla().getId()).as("maxalla.id").isNotNull();
                    soft.assertThat(reg.getMaxalla().getGuid()).as("maxalla.guid").isNotEmpty();
                    soft.assertThat(reg.getMaxalla().getValue()).as("maxalla.value").isNotEmpty();
                    soft.assertThat(reg.getStreet().getId()).as("street.id").isNotNull();
                    soft.assertThat(reg.getStreet().getGuid()).as("street.guid").isNotEmpty();
                    soft.assertThat(reg.getStreet().getValue()).as("street.value").isNotEmpty();
                    var translation = response.getTranslations().getPermanentRegistration();
                    soft.assertThat(translation).as("translations.PermanentRegistration").isNotNull();
                    soft.assertThat(translation.getNameRu()).as("translations.name_ru").isEqualTo("Постоянная регистрация");
                    soft.assertThat(translation.getNameEn()).as("translations.name_en").isEqualTo("Permanent Registration");
                    soft.assertThat(translation.getNameUz()).as("translations.name_uz").isEqualTo("Doimiy yashash joyi");
                    soft.assertThat(translation.getIsTitle()).as("translations.is_title").isIn(true, false);
                    soft.assertThat(translation.getIsCopied()).as("translations.is_copied").isIn(true, false);
                });
            });
        });

    }

    @Test
    
    @DisplayName("[/callService] POST Успешное получение услуги Проверка запрета на выезд заграницу")
        void getTravelBanStatusTest() {
        step("Формируем тело запроса", () -> {
            var request = CallServiceRequest.builder()
                    .organization("mygov")
                    .serviceId(14) // Проверка запрета на выезд
                    .pin(pinfl)
                    .passport(serie)
                    .webSession(webSession)
                    .fields(Map.of("pass_serial_number", serie))
                    .build();

            step("Выполняем запрос /callService", () -> {
                GetTravelBanStatusResponse response = govGetCallService.callService(
                        request,
                        GetTravelBanStatusResponse.class,
                        token
                );

                step("Проверка ответа", () -> {
                    soft.assertThat(response.getResponse()).as("response").isNotNull();
                    soft.assertThat(response.getResponse().getSuccess()).as("response.success").isTrue();
                    var file = response.getResponse().getFile();
                    soft.assertThat(file).as("response.file").isNotNull();
                    soft.assertThat(file.getHas()).as("response.file.has").isEqualTo("NO");
                    soft.assertThat(file.getId()).as("response.file.id").isNull();
                    var data = response.getResponse().getData();
                    soft.assertThat(data).as("response.data").isNotNull();
                    soft.assertThat(data.getResultCode()).as("data.result_code").isNotNull();
                    soft.assertThat(data.getResultMessage()).as("data.result_message").isNotEmpty();
                    var translation = response.getTranslations().get("result_message");
                    soft.assertThat(translation).as("translations.result_message").isNotNull();
                    soft.assertThat(translation.getNameRu()).as("translations.result_message.name_ru").isEqualTo("Результат");
                    soft.assertThat(translation.getNameEn()).as("translations.result_message.name_en").isEqualTo("Result");
                    soft.assertThat(translation.getNameUz()).as("translations.result_message.name_uz").isEqualTo("Natija");
                    soft.assertThat(translation.getIsTitle()).as("translations.result_message.is_title").isIn(true, false);
                    soft.assertThat(translation.getIsCopied()).as("translations.result_message.is_copied").isIn(true, false);
                });
            });
        });
    }

    @Test
    
    @DisplayName("[/callService] POST Успешное получение услуги Выписка из трудовой книжки")
        void getLaborBookExtractTest() {
        step("Формируем тело запроса", () -> {
            var request = BaseCallServiceRequest.builder()
                    .organization("mygov")
                    .serviceId(11) // Выписка из трудовой книжки
                    .pin(pinfl)
                    .passport(serie)
                    .webSession(webSession)
                    .build();

            step("Выполняем запрос /callService", () -> {
                GetLaborBookExtractTestResponse response = govGetCallService.callService(
                        request,
                        GetLaborBookExtractTestResponse.class,
                        token
                );

                step("Проверка ответа", () -> {
                    soft.assertThat(response.getResponse()).as("response").isNotNull();
                    soft.assertThat(response.getResponse().getSuccess()).as("response.success").isTrue();
                    var file = response.getResponse().getFile();
                    soft.assertThat(file).as("response.file").isNotNull();
                    soft.assertThat(file.getHas()).as("response.file.has").isEqualTo("YES");
                    soft.assertThat(file.getId()).as("response.file.id").isPositive();
                    var data = response.getResponse().getData();
                    soft.assertThat(data).as("response.data").isNotNull();
                    soft.assertThat(data).as("response.data").isNotEmpty();
                    var firstRecord = data.getFirst();
                    soft.assertThat(firstRecord.getActionTypeId()).as("data[0].action_type_id").isNotNull();
                    soft.assertThat(firstRecord.getActualCompanyProfileName()).as("data[0].actual_company_profile_name").isNotEmpty();
                    soft.assertThat(firstRecord.getCompanyProfileName()).as("data[0].company_profile_name").isNotEmpty();
                    soft.assertThat(firstRecord.getCompanyTin()).as("data[0].company_tin").isNotEmpty();
                    soft.assertThat(firstRecord.getDateStart()).as("data[0].date_start").isNotEmpty();
                    soft.assertThat(firstRecord.getDateStop()).as("data[0].date_stop").isNotEmpty();
                    soft.assertThat(firstRecord.getId()).as("data[0].id").isNotNull();
                    soft.assertThat(firstRecord.getOrderDate()).as("data[0].order_date").isNotEmpty();
                    soft.assertThat(firstRecord.getOrderNumber()).as("data[0].order_number").isNotEmpty();
                    soft.assertThat(firstRecord.getPositionName()).as("data[0].position_name").isNotEmpty();
                    soft.assertThat(firstRecord.getPositionNameRu()).as("data[0].position_name_ru").isNotEmpty();
                    soft.assertThat(firstRecord.getStructureName()).as("data[0].structure_name").isNotEmpty();
                    soft.assertThat(firstRecord.getStructureNameRu()).as("data[0].structure_name_ru").isNotEmpty();
                    soft.assertThat(firstRecord.getWorkType()).as("data[0].work_type").isNotNull();
                    var translations = response.getTranslations();
                    var positionTranslation = translations.get("position_name");
                    soft.assertThat(positionTranslation).as("translations.position_name").isNotNull();
                    soft.assertThat(positionTranslation.getNameRu()).as("translations.position_name.name_ru").isEqualTo("Должность");
                    soft.assertThat(positionTranslation.getNameEn()).as("translations.position_name.name_en").isEqualTo("Position");
                    soft.assertThat(positionTranslation.getNameUz()).as("translations.position_name.name_uz").isEqualTo("Lavozim");
                    soft.assertThat(positionTranslation.getIsTitle()).as("translations.position_name.is_title").isIn(true, false);
                    soft.assertThat(positionTranslation.getIsCopied()).as("translations.position_name.is_copied").isIn(true, false);
                    var tinTranslation = translations.get("company_tin");
                    soft.assertThat(tinTranslation).as("translations.company_tin").isNotNull();
                    soft.assertThat(tinTranslation.getNameRu()).as("translations.company_tin.name_ru").isEqualTo("ИНН организации");
                    soft.assertThat(tinTranslation.getNameEn()).as("translations.company_tin.name_en").isEqualTo("Organization TIN");
                    soft.assertThat(tinTranslation.getNameUz()).as("translations.company_tin.name_uz").isEqualTo("Tashkilot STIR");
                    soft.assertThat(tinTranslation.getIsTitle()).as("translations.company_tin.is_title").isIn(true, false);
                    soft.assertThat(tinTranslation.getIsCopied()).as("translations.company_tin.is_copied").isIn(true, false);
                    var dateStartTranslation = translations.get("date_start");
                    soft.assertThat(dateStartTranslation).as("translations.date_start").isNotNull();
                    soft.assertThat(dateStartTranslation.getNameRu()).as("translations.date_start.name_ru").isEqualTo("Дата начала  работы");
                    soft.assertThat(dateStartTranslation.getNameEn()).as("translations.date_start.name_en").isEqualTo("Start date");
                    soft.assertThat(dateStartTranslation.getNameUz()).as("translations.date_start.name_uz").isEqualTo("Ish boshlangan sanasi");
                    soft.assertThat(dateStartTranslation.getIsTitle()).as("translations.date_start.is_title").isIn(true, false);
                    soft.assertThat(dateStartTranslation.getIsCopied()).as("translations.date_start.is_copied").isIn(true, false);
                    var dateStopTranslation = translations.get("date_stop");
                    soft.assertThat(dateStopTranslation).as("translations.date_stop").isNotNull();
                    soft.assertThat(dateStopTranslation.getNameRu()).as("translations.date_stop.name_ru").isEqualTo("Дата окончания работы");
                    soft.assertThat(dateStopTranslation.getNameEn()).as("translations.date_stop.name_en").isEqualTo("End date");
                    soft.assertThat(dateStopTranslation.getNameUz()).as("translations.date_stop.name_uz").isEqualTo("Ish tugatgan sanasi");
                    soft.assertThat(dateStopTranslation.getIsTitle()).as("translations.date_stop.is_title").isIn(true, false);
                    soft.assertThat(dateStopTranslation.getIsCopied()).as("translations.date_stop.is_copied").isIn(true, false);
                    var structureTranslation = translations.get("structure_name");
                    soft.assertThat(structureTranslation).as("translations.structure_name").isNotNull();
                    soft.assertThat(structureTranslation.getNameRu()).as("translations.structure_name.name_ru").isEqualTo("Отдел");
                    soft.assertThat(structureTranslation.getNameEn()).as("translations.structure_name.name_en").isEqualTo("Department");
                    soft.assertThat(structureTranslation.getNameUz()).as("translations.structure_name.name_uz").isEqualTo("Bo'lim");
                    soft.assertThat(structureTranslation.getIsTitle()).as("translations.structure_name.is_title").isIn(true, false);
                    soft.assertThat(structureTranslation.getIsCopied()).as("translations.structure_name.is_copied").isIn(true, false);
                    var orgTranslation = translations.get("actual_company_profile_name");
                    soft.assertThat(orgTranslation).as("translations.actual_company_profile_name").isNotNull();
                    soft.assertThat(orgTranslation.getNameRu()).as("translations.actual_company_profile_name.name_ru").isEqualTo("Организация");
                    soft.assertThat(orgTranslation.getNameEn()).as("translations.actual_company_profile_name.name_en").isEqualTo("Organization");
                    soft.assertThat(orgTranslation.getNameUz()).as("translations.actual_company_profile_name.name_uz").isEqualTo("Tashkilot");
                    soft.assertThat(orgTranslation.getIsTitle()).as("translations.actual_company_profile_name.is_title").isIn(true, false);
                    soft.assertThat(orgTranslation.getIsCopied()).as("translations.actual_company_profile_name.is_copied").isIn(true, false);
                });
            });
        });

    }

    @Test
    
    @DisplayName("[/callService] POST Получение услуги Получение справки с места учебы (ВУЗ)")
        void GetUniversityStudyCertificateTest() {
        step("Формируем тело запроса", () -> {
            var request = BaseCallServiceRequest.builder()
                    .organization("mygov")
                    .serviceId(13) // Получение справки с места учебы
                    .pin(pinfl)
                    .passport(serie)
                    .webSession(webSession)
                    .build();


            step("Выполняем запрос /callService (raw)", () -> {
                Response response = govGetCallService.callRawService(request, token);

                step("Проверка ответа", () -> {
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response).validateJsonSchema("json_schema/university_study_certificate.json");
                });
            });
        });
    }

    @Test
    
    @DisplayName("[/callService] POST Успешное получение Информации о юридических лицах")
        void getLegalEntityInfoTest() {
        step("Формируем тело запроса", () -> {
            var request = CallServiceRequest.builder()
                    .organization("mygov")
                    .serviceId(17) // Получение информации о юридических лицах
                    .pin(pinfl)
                    .passport(serie)
                    .webSession(webSession)
                    .fields(Map.of("tin", "302134733"))
                    .build();


            step("Выполняем запрос /callService (raw)", () -> {
                Response response = govGetCallService.callRawService(request, token);

                step("Проверка ответа", () -> {
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response).validateJsonSchema("json_schema/legal_entity_info.json");
                });
            });
        });

    }

    @Test
    
    @DisplayName("[/callService] POST Успешное получение услуги Информация об автотранспорте")
        void getVehicleInfoTest() {
        step("Формируем тело запроса", () -> {
            var request = CallServiceRequest.builder()
                    .organization("mygov")
                    .serviceId(2) // Получение информации об автотранспорте
                    .pin(pinfl)
                    .passport(serie)
                    .webSession(webSession)
                    .fields(Map.of(
                            "texp_number",      "4397317",
                            "texp_sery",        "AAF",
                            "registration_number", "01R083TB"
                    ))
                    .build();

            step("Выполняем запрос /callService (raw)", () -> {
                Response response = govGetCallService.callRawService(request, token);

                step("Проверка ответа", () -> {
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response)
                            .validateJsonSchema("json_schema/vehicle_info.json");
                });
            });
        });
    }

    @Test
    
    @DisplayName("[/callService] POST Успешное получение услуги Проверка очереди в детский сад")
        void getCheckingTheKindergartenQueueTest() {
        step("Формируем тело запроса", () -> {
            var request = CallServiceRequest.builder()
                    .organization("mygov")
                    .serviceId(16) // Проверка очереди в детский сад
                    .pin(pinfl)
                    .passport(serie)
                    .webSession(webSession)
                    .fields(Map.of("app_number", "24019635"))
                    .build();

            step("Выполняем запрос /callService (raw)", () -> {
                Response response = govGetCallService.callRawService(request, token);

                step("Проверка ответа", () -> {
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response)
                            .validateJsonSchema("json_schema/checking_the_kindergarten_queue.json");
                });
            });
        });
    }
}

