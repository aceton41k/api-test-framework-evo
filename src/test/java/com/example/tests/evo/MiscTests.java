package com.example.tests.evo;

import com.example.api.evo.models.misc.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.constants.AccountData;
import com.example.api.evo.constants.ServiceData;
import com.example.api.evo.models.BaseErrorResponse;
import uz.click.evo_api.models.misc.*;
import com.example.api.evo.services.misc.EventsCountService;
import com.example.api.evo.services.misc.FormSyncService;
import com.example.api.evo.services.misc.MiscService;
import com.example.jupiter.annotations.SkipAuthSetup;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static com.example.api.evo.constants.ErrorCodes.SESSION_BLOCKED;
import static com.example.api.evo.constants.ErrorCodes.SOMETHING_WENT_WRONG;

public class MiscTests extends EvoBaseTest {
    @Autowired
    EventsCountService eventsCountService;

    @Autowired
    FormSyncService formSyncService;

    @Autowired
    MiscService miscService;

    Map<String, String> emptyHeader = Collections.emptyMap();

    @Test
    
    @DisplayName("[events.count] Получить счетчик ивентов")
    void testEventsCount() {
        step("Запрос на получение счетчика", () -> {
            var response = eventsCountService.getEventsCount(EventsCountResponse.class,
                    headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult().getOverallCount()).isNotNull();
                soft.assertThat(response.getResult().getUnreadCount()).isNotNull();
                soft.assertThat(response.getResult().getRequestsCount()).isNotNull();
                soft.assertThat(response.getResult().getReceiptCount()).isNotNull();
                soft.assertThat(response.getResult().getInvoiceCount()).isNotNull();
                soft.assertThat(response.getResult().getUnreadNewsCount()).isNotNull();
                soft.assertThat(response.getResult().getMenuMoreCount()).isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[events.count] Получить счетчик ивентов без сессии")
    @SkipAuthSetup
    void testEventsCountWithoutSession() {
        step("Запрос на получение счетчика", () -> {
            var response = eventsCountService.getEventsCount(BaseErrorResponse.class, emptyHeader);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SESSION_BLOCKED);
            });
        });
    }

    @Test
    
    @DisplayName("[form.sync] Синхронизация формы")
    @SkipAuthSetup
    void testFormSync() {
        step("Запрос на получение sync form", () -> {
            var parameters = FormSyncRequest.Params.Parameters.builder()
                    .serviceId(ServiceData.SERVICE_ID_UMS)
                    .build();
            var params = FormSyncRequest.Params.builder()
                    .parameters(parameters)
                    .build();
            Response response = formSyncService.getFormSync(params);

            step("Проверка схемы", () -> {
                        soft.assertThat(response).validateJsonSchema("json_schema/form_sync.json");
                        soft.assertThat(response.jsonPath().getList("result.service_id")).isNotEmpty();
                    }
            );
        });
    }

    @Test
    
    @DisplayName("[service.images] Получение изображений сервисов")
    @Disabled("504 ошибка")
    @SkipAuthSetup
    void testServiceImages() {
        Response response = step("Запрос на получение изображений сервисов", () -> formSyncService.getServiceImages());
        step("Проверка, что contentType application/octet-stream", () -> {
            ContentType contentType = ContentType.fromContentType(response.getContentType());
            soft.assertThat(contentType).isEqualTo(ContentType.BINARY);
        });
    }

    @Test
    
    @DisplayName("[category.images] Получение изображений категорий")
    @Disabled("504 ошибка")
    @SkipAuthSetup
    void testCategoryImages() {
        Response response =
                step("Запрос на получение изображений категорий", () -> formSyncService.getCategoryImages());
        step("Проверка, что contentType application/octet-stream", () -> {
            ContentType contentType = ContentType.fromContentType(response.getContentType());
            assertThat(contentType).isEqualTo(ContentType.BINARY);
        });
    }

    @Test
    
    @DisplayName("[version.info] Получить информацию о версии приложения которого нужно обновить")
    void getAppVersionTestWhichShouldUpdate() {
        step("Получить информацию о версии приложения", () -> {
            var params = VersionInfoRequest.Params.builder()
                    .version(60000021)
                    .build();
            VersionInfoResponse response = miscService.getVersionInfo(params, headers);
            step("Проверка, что ответ содержит version и level", () -> {
                soft.assertThat(response.getResult().getVersion()).isNotNull();
                soft.assertThat(response.getResult().getLevel()).isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[version.info] Получить информацию о версии приложения которого не нужно обновить")
    void getAppVersionTest() {
        step("Получить информацию о версии приложения", () -> {
            var params = VersionInfoRequest.Params.builder()
                    .version(8192)
                    .build();
            VersionInfoResponse response = miscService.getVersionInfo(params, headers);

            step("Проверка, что result пустой ({})", () -> {
                soft.assertThat(response.getResult().getVersion()).isNull();
                soft.assertThat(response.getResult().getLevel()).isNull();
            });
        });
    }

    @Test
    
    @DisplayName("[transfer.barcode] Получить баркод по карте(accountId)")
    @Disabled("Не понятно из чего падает")
    void testTransferBarcode() {
        step("Получить баркод по карте(accountId)", () -> {
            var params = TransferBarcodeRequest.Params.builder()
                    .accountId(AccountData.XAS_ACCOUNT_ID_1)
                    .build();
            TransferBarcodeResponse response = eventsCountService.getTransferBarcode(params,
                    TransferBarcodeResponse.class,
                    headers);

            step("Проверка, что ответ содержит data и downloadUrl", () -> {
                soft.assertThat(response.getResult().getData())
                        .as("result.data должен быть непустым URL")
                        .isNotBlank()
                        .startsWith("https://");

                soft.assertThat(response.getResult().getDownloadUrl())
                        .as("result.download_url должен быть непустым URL на .pdf")
                        .isNotBlank()
                        .startsWith("https://")
                        .endsWith(".pdf");
            });
        });
    }

    @Test
    
    @DisplayName("[transfer.barcode] Получить баркод по карте(accountId) без сессии")
    @SkipAuthSetup
    void testTransferBarcodeWithoutSession() {
        step("Получить баркод по карте(accountId)", () -> {
            var params = TransferBarcodeRequest.Params.builder()
                    .accountId(AccountData.XAS_ACCOUNT_ID_1)
                    .build();
            var response = eventsCountService.getTransferBarcode(params, BaseErrorResponse.class, emptyHeader);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SOMETHING_WENT_WRONG);
            });
        });
    }

    @Test
    
    @DisplayName("[event.list] Получить список ивентов")
        //To do нужно будет проверить связке с создванием инвойса, перевода денего по номеру телефона и на запросе средств
    void testEventList() {
        step("Получить список ивентов", () -> {
            EventListResponse response = eventsCountService.getEventList(headers);

            step("Проверка, что ответ содержит ивенты и первый ид не null", () -> {
                assertThat(response.getResult()).isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[nearby.users] Проверка информации о пользователях")
    void testNearbyUsers() {
        step("Получить информацию о пользователе", () -> {
            var params = NearbyUsersDataRequest.Params.builder()
                    .users(List.of(4252823, 2459517))
                    .build();
            var response = eventsCountService.getNearbyUsers(params,
                    NearbyUsersResponse.class,
                    headers);

            step("Проверка, что клиент существует", () -> {
                soft.assertThat(response.getResult())
                        .as("Result не должен быть пустым")
                        .isNotEmpty()
                        .allSatisfy(result -> {
                            soft.assertThat(result.getClientId()).as("clientId не должен быть null").isNotNull();
                            soft.assertThat(result.getName()).as("name не должен пустым").isNotNull();
                            soft.assertThat(result.getPhoneNumber())
                                    .as("phoneNumber должен начаться с 9989")
                                    .startsWith("9989");
                        });

            });
        });
    }

    @Test
    
    @DisplayName("[nearby.users] Проверка информации о пользователях без сессии")
    @SkipAuthSetup
    void testNearbyUsersWithoutSession() {
        step("Получить информацию о пользователе без сессии", () -> {
            var params = NearbyUsersDataRequest.Params.builder()
                    .users(List.of(4252823, 2459517))
                    .build();
            var response = eventsCountService.getNearbyUsers(params, BaseErrorResponse.class, emptyHeader);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SESSION_BLOCKED);
            });
        });
    }

    @Test
    
    @DisplayName("Получение safe mode")
    @SkipAuthSetup
    void testSafeMode() {
        SafeModeResponse response = step("Получение safe mode", () -> miscService.getSafeMode());
        step("Проверка, что ответ содержит safeMode", () -> {
            assertThat(response.getSafemode()).isIn(true, false);
        });
    }
}
