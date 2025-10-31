package com.example.tests.evo;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.constants.UserData;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.auto_pay.AutoPayDataRequest;
import com.example.api.evo.models.auto_pay.AutoPayListResponse;
import com.example.api.evo.models.auto_pay.AutoPaySaveRequest;
import com.example.api.evo.models.services.ServiceListRequest;
import com.example.api.evo.services.DataBaseService;
import com.example.api.evo.services.auto_pay.AutoPayService;
import com.example.api.evo.services.service.ServicesService;
import com.example.tests.BaseTest;
import com.example.utils.DateTimeUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Disabled("Нужна карта для проверки этих методов")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutoPayTests extends BaseTest {
    public Map<String, String> headers1;
    @Autowired
    DataBaseService dataBaseService;
    @Autowired
    ServicesService servicesService;
    @Autowired
    AutoPayService autopayService;
    Long accountId, categoryId, ucellServiceId;
    UserData.User user;

    @BeforeAll
    void setUp() {
        user = userData.getUser("REG");
        String imei = user.getPhoneNumber() + "000";
        headers1 = operations.userRegister(user, imei).getEvoHeaders();

        dataBaseService.removeDeviceRestrictions(imei);

        accountId = Objects.requireNonNull(dataBaseService.findClientId(Long.parseLong(user.getPhoneNumber())));
        categoryId = servicesService.getCategoryList(headers1).getResult().getFirst().getId();

        ServiceListRequest.Params params = ServiceListRequest.Params.builder()
                .pageNumber(1)
                .categoryId(categoryId)
                .build();

        ucellServiceId = servicesService.getServiceList(params, headers1).getResult().stream()
                .filter(result1 -> result1.getName().equals("Ucell"))
                .findAny()
                .orElseThrow()
                .getId();
    }

    @AfterAll
    void afterAll() {
        dataBaseService.deleteUserRegistration(user.getPhoneNumber());
    }

    @Test
    
    @DisplayName("[autopay.save] Создание автоплатежа")
    void autoPaySave() {
        step("Запрос на создание автоплатежа [autopay.save]", () -> {
            AutoPaySaveRequest.Params params = AutoPaySaveRequest.Params.builder()
                    .serviceId(ucellServiceId)
                    .accountId(accountId)
                    .autopayType(SCHEDULE)
                    .parameters(AutoPaySaveRequest.Parameters.builder()
                            .phoneNum(user.getPhoneNumber().substring(3))
                            .amount(1552.56F)
                            .autopayType(MONTHLY)
                            .autopayMonthlyDay(20)
                            .build())
                    .build();

            step("Проверка ответа", () -> {
                ResponseWithOkResult response = autopayService.autoPaySave(params, headers1);
                soft.assertThat(response.getResult()).isEqualTo("ok");
                soft.assertThat(response.getId()).isNotEqualTo(0);
            });
        });
    }

    @Nested
    class AutoPayListEditDeleteTests extends BaseTest {
        AutoPayListResponse.Autopay savedAutopay;

        @BeforeEach
        void createAutoPayAndGet() {
            String autopayName = "AutoPayTest " + DateTimeUtil.getCurrentTimestamp();
            step("Предусловие. Создание автоплатежа и получение", () -> {
                AutoPaySaveRequest.Params params = AutoPaySaveRequest.Params.builder()
                        .serviceId(ucellServiceId)
                        .accountId(accountId)
                        .parameters(AutoPaySaveRequest.Parameters.builder()
                                .phoneNum(user.getPhoneNumber().substring(3))
                                .autopayType(MONTHLY)
                                .autopayName(autopayName)
                                .autopayMonthlyDay(20)
                                .autopayPayTime("16:37")
                                .amount(1094.98F)
                                .build())
                        .build();

                autopayService.autoPaySave(params, headers1);

                AutoPayListResponse response = autopayService.autoPayList(headers1);

                savedAutopay = response.getResult().stream()
                        .filter(autopay -> autopay.getName().equals(autopayName))
                        .findFirst()
                        .orElseThrow();
            });
        }

        @Test
        
        @DisplayName("[autopay.list] Получение списка автоплатежей")
                void autopayList() {
            step("Запрос на получение списка автоплатежей [autopay.list]", () -> {
                AutoPayListResponse response = autopayService.autoPayList(headers1);

                AutoPayListResponse.Autopay savedAutoPay = response.getResult().stream()
                        .filter(autopay -> autopay.getName().equals(savedAutopay.getName()))
                        .findFirst()
                        .orElseThrow();

                step("Проверка, что ответ содержит созданный автоплатеж", () -> {
                    soft.assertThat(response.getResult()).isNotEmpty();
                    soft.assertThat(savedAutoPay.getName()).isEqualTo(savedAutopay.getName());
                    soft.assertThat(savedAutoPay.getAutopayType()).isEqualTo(SCHEDULE);
                    soft.assertThat(savedAutoPay.getId()).isNotEqualTo(0);
                    soft.assertThat(savedAutoPay.getValue()).isEqualTo(user.getPhoneNumber().substring(3));
                    soft.assertThat(savedAutoPay.getServiceId()).isEqualTo(ucellServiceId);
                    soft.assertThat(savedAutoPay.getAccountId()).isEqualTo(accountId);
                    soft.assertThat(savedAutoPay.getStatusText()).isEqualTo("Активный");
                    soft.assertThat(savedAutoPay.getDescription()).isEqualTo("20-го числа каждого месяца в 16:37");
                    soft.assertThat(savedAutoPay.getCardTypes())
                            .containsExactlyInAnyOrderElementsOf(List.of(HUMO.toString(),
                                    WALLET.toString(),
                                    SMARTV.toString()));
                });
            });
        }

        @Test
        
        @DisplayName("[autopay.save] Изменение автоплатежа")
        void autoPayEdit() {
            step("Запрос на изменение автоплатежа", () -> {
                AutoPaySaveRequest.Params params = AutoPaySaveRequest.Params.builder()
                        .serviceId(ucellServiceId)
                        .autopayId(savedAutopay.getId())
                        .parameters(AutoPaySaveRequest.Parameters.builder()
                                .autopayName(savedAutopay.getName() + " edited")
                                .autopayType(WEEKLY)
                                .autopayWeeklyDay(3)
                                .amount(9999F)
                                .phoneNum("508934570")
                                .build())
                        .build();

                ResponseWithOkResult response = autopayService.autoPaySave(params, headers1);
                step("Проверка ответа", () -> {
                    soft.assertThat(response.getResult()).isEqualTo("ok");
                    soft.assertThat(response.getId()).isNotEqualTo(0);
                });

                step("Проверка, что платеж изменился", () -> {
                    AutoPayListResponse listResponse = autopayService.autoPayList(headers1);
                    AutoPayListResponse.Autopay editedAutoPay = listResponse.getResult().stream()
                            .filter(autopay -> autopay.getId().equals(savedAutopay.getId()))
                            .findAny()
                            .orElseThrow();
                    soft.assertThat(editedAutoPay.getId()).isEqualTo(savedAutopay.getId());
                    soft.assertThat(editedAutoPay.getName()).isEqualTo(savedAutopay.getName() + " edited");
                    soft.assertThat(editedAutoPay.getAmount()).isEqualTo(9999F);
                    soft.assertThat(editedAutoPay.getValue()).isEqualTo("508934570");
                });
            });

        }

        @Test
        
        @DisplayName("[autopay.delete] Удаление автоплатежа")
        void autoPayDelete() {
            step("Запрос на удаление автоплатежа", () -> {
                ResponseWithOkResult deleteResponse =
                        autopayService.autoPayDelete(savedAutopay.getId(), savedAutopay.getAutopayType(), headers1);
                step("Проверка ответа", () -> {
                    soft.assertThat(deleteResponse.getResult()).isEqualTo("ok");
                    soft.assertThat(deleteResponse.getId()).isNotEqualTo(0);
                });

                step("Проверка, что платеж удалился из списка платежей", () -> {
                    AutoPayListResponse listResponse = autopayService.autoPayList(headers1);
                    boolean present = listResponse.getResult().stream()
                            .anyMatch(autopay -> autopay.getId().equals(savedAutopay.getId()));
                    assertThat(present).isFalse();
                });
            });

        }


        @Test
        
        @DisplayName("[autopay.data] Получение данных об автоплатеже")
        void autoPayData() {
            step("Запрос на получение данных автоплатежа [autopay.data]", () -> {

                AutoPayDataRequest.Params params = AutoPayDataRequest.Params.builder()
                        .serviceId(ucellServiceId)
                        .build();

                Response response = autopayService.autoPayData(params, headers1);

                step("Проверка ответа", () -> {
                    soft.assertThat(response.getStatusCode()).isEqualTo(200);
                    soft.assertThat(response).validateJsonSchema("json_schema/autopay_data.json");
                });

            });
        }
    }
}
