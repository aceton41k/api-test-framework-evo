package com.example.tests.ofd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.ofd.models.price_list_ms.GoodsPricesErrorResponse;
import com.example.api.ofd.models.price_list_ms.GoodsPricesRequest;
import com.example.api.ofd.models.price_list_ms.GoodsPricesResponse;
import com.example.api.ofd.services.OfdPriceListMsService;

import java.util.Map;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;

public class PriceListMsTests extends OfdApiBaseTest {

    static Long priceId = 5L;
    static int serviceId = 56036;
    static String mxikCode = "02710001003000000";
    @Autowired
    OfdPriceListMsService ofdPriceListMsService;

    private static Stream<Arguments> requiredParametersForGoodsPriceRequest() {
        return Stream.of(Arguments.of(priceId, serviceId, null), Arguments.of(priceId, null, mxikCode),
                Arguments.of(null, serviceId, mxikCode), Arguments.of(priceId, serviceId, mxikCode));
    }
    /*негативные сценарии*/

    private static Stream<Arguments> requiredInvalidParametersForGoodsPriceRequest() {
        return Stream.of(
                // id serviceId mxikCode проверки без комбинации
                //id
                Arguments.of(-1L, null, null), Arguments.of(Long.MAX_VALUE, null, null),
                //serviceId
                Arguments.of(null, -1, null), Arguments.of(null, 0, null), Arguments.of(null, 10271001, null),
                //mxikCode
                Arguments.of(null, null, "ABCDEF"), Arguments.of(null, null, "0271000100300000012354124"),

                //проверки с комбинацией
                Arguments.of(priceId, 12345123, null), Arguments.of(priceId, null, "12341234123"),
                Arguments.of(null, serviceId, "12341234123"));
    }

    @Test
    
    @DisplayName("[api/goods-prices/{id}][GET] Получение запись об ценах топливо")
    void getGoodsPriceListById() {
        var request = GoodsPricesRequest.builder().id(priceId).build();
        step("Отправить запрос на получение цены", () -> {
            var response = ofdPriceListMsService.getPriceList(request, GoodsPricesResponse.class);
            step("Проверка ответа", () -> {

                soft.assertThat(response.getPage()).as("page").isEqualTo(0);
                soft.assertThat(response.getTotalPages()).as("totalPages").isEqualTo(1);
                soft.assertThat(response.getTotalElements()).as("totalElements").isEqualTo(1);

                soft.assertThat(response.getGoodsPriceOutputDtos()).as("goodsPriceOutputDtos").isNotEmpty().hasSize(1)
                    .allSatisfy(item -> {
                        soft.assertThat(item.getId()).as("id").isEqualTo(priceId);
                        soft.assertThat(item.getServiceId()).as("serviceId").isEqualTo(serviceId);
                        soft.assertThat(item.getMxikCode()).as("mxikCode").isEqualTo(mxikCode);
                        soft.assertThat(item.getPricePerUnit()).as("pricePerUnit").isNotNull();
                        soft.assertThat(item.getPriceDate()).as("priceDate").isEqualTo("2024-09-26T12:00:00.000Z");
                    });
            });
        });

    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15})
    
    @DisplayName("[api/goods-prices/{size}][GET] Получение запись об ценах топливо когда size позитивные(5 10 15)")
    void getGoodsPriceListBySize(int size) {
        var request = GoodsPricesRequest.builder().size(size).build();
        step("Отправить запрос", () -> {
            var response = ofdPriceListMsService.getPriceList(request, GoodsPricesResponse.class);
            step("Проверка ответа", () -> {

                soft.assertThat(response.getPage()).as("page").isEqualTo(0);
                soft.assertThat(response.getTotalPages()).as("totalPages").isGreaterThan(0);
                soft.assertThat(response.getTotalElements()).as("totalElements").isGreaterThan(0);

                soft.assertThat(response.getGoodsPriceOutputDtos()).as("goodsPriceOutputDtos").hasSize(size);
            });
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    
    @Tag("negative")
    @DisplayName("[api/goods-prices/{size}][GET] Получение запись об ценах топливо когда size -1 и 0")
    void getGoodsPriceListBySizeNegative(int size) {
        var request = GoodsPricesRequest.builder().size(size).build();
        step("Отправить запрос", () -> {
            var response = ofdPriceListMsService.getPriceList(request, GoodsPricesResponse.class);
            step("Проверка ответа", () -> {

                soft.assertThat(response.getPage()).as("page").isEqualTo(0);
                soft.assertThat(response.getTotalPages()).as("totalPages").isGreaterThan(0);
                soft.assertThat(response.getTotalElements()).as("totalElements").isGreaterThan(0);

                soft.assertThat(response.getGoodsPriceOutputDtos()).as("goodsPriceOutputDtos").hasSize(10);
            });
        });
    }

    @Test
    
    @DisplayName("[api/goods-prices/{page}][GET] Проверка пагинации")
    void getGoodsPriceListByPage() {

        step("Get prices by pages", () -> {
            var req0 = GoodsPricesRequest.builder().page(0).build();
            var req1 = GoodsPricesRequest.builder().page(1).build();
            var r0 = ofdPriceListMsService.getPriceList(req0, GoodsPricesResponse.class);
            var r1 = ofdPriceListMsService.getPriceList(req1, GoodsPricesResponse.class);

            step("Проверка ответа", () -> {
                soft.assertThat(r0.getPage()).as("page0").isEqualTo(0);
                soft.assertThat(r1.getPage()).as("page1").isEqualTo(1);

                var ids0 = r0.getGoodsPriceOutputDtos().stream().map(GoodsPricesResponse.GoodsPriceOutputDto::getId)
                             .toList();
                var ids1 = r1.getGoodsPriceOutputDtos().stream().map(GoodsPricesResponse.GoodsPriceOutputDto::getId)
                             .toList();

                soft.assertThat(ids0).as("ids(page0) vs ids(page1)").doesNotContainAnyElementsOf(ids1);

                soft.assertThat(r0.getGoodsPriceOutputDtos().getFirst().getId()).as("first id differs")
                    .isNotEqualTo(r1.getGoodsPriceOutputDtos().getFirst().getId());
            });
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    @Tag("negative")
    
    @DisplayName("[api/goods-prices/{page}][GET] Получение запись об ценах топливо когда page -1 и 0")
    void getGoodsPriceListByPageNegative(int page) {
        var request = GoodsPricesRequest.builder().page(page).build();
        step("Отправить запрос", () -> {
            var response = ofdPriceListMsService.getPriceList(request, GoodsPricesResponse.class);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getPage()).as("page").isEqualTo(0);
                soft.assertThat(response.getTotalPages()).as("totalPages").isGreaterThan(0);
                soft.assertThat(response.getTotalElements()).as("totalElements").isGreaterThan(0);

                soft.assertThat(response.getGoodsPriceOutputDtos()).as("goodsPriceOutputDtos").hasSize(10);
            });

        });

    }


    @Test
    
    @DisplayName("[api/goods-prices/{page}][GET] Получение запись об ценах топливо когда page n, проверка последней page")
    void getGoodsPriceListByLastPage() {
        var request = GoodsPricesRequest.builder().build();
        step("Отправить запрос на получения последней страницы, потом запрос на получение страницы", () -> {
            var response = ofdPriceListMsService.getPriceList(request, GoodsPricesResponse.class);
            int totalPages = response.getTotalPages();
            // -1 из за того что массив страниц
            var secRequest = GoodsPricesRequest.builder().page(totalPages - 1).build();
            var secResponse = ofdPriceListMsService.getPriceList(secRequest, GoodsPricesResponse.class);

            step("Проверка ответа", () -> {
                soft.assertThat(secResponse.getPage()).as("page").isEqualTo(totalPages - 1);
                soft.assertThat(secResponse.getTotalPages()).as("totalPages").isGreaterThan(0);
                soft.assertThat(secResponse.getTotalElements()).as("totalElements").isGreaterThan(0);

                soft.assertThat(secResponse.getGoodsPriceOutputDtos()).as("goodsPriceOutputDtos").isNotEmpty();
            });
        });

    }

    @Test
    
    @DisplayName("[api/goods-prices/{page}][GET] Получение запись об ценах топливо когда page несуществующая страница")
    void getGoodsPriceListByPageInvalidParameter() {
        var request = GoodsPricesRequest.builder().page(999999).build();
        step("Отправить запрос", () -> {
            var response = ofdPriceListMsService.getPriceList(request, GoodsPricesErrorResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response).as("response").isNotNull();
                soft.assertThat(response.getError()).as("error").isNotNull();

                soft.assertThat(response.getError().getCode()).as("error.code").isEqualTo("404");
                soft.assertThat(response.getError().getMessage()).as("error.message")
                    .isEqualTo("Ошибка: Ресурс не найден. Запрашиваемая запись не существует в системе.");
                soft.assertThat(response.getError().getTarget()).as("error.target").isNull();

                soft.assertThat(response.getError().getLocalize().getMessage()).as("error.localize.message")
                    .isEqualTo("Ресурс не найден");
                soft.assertThat(response.getError().getLocalize().getExtra()).as("error.localize.extra")
                    .isEqualTo("Проверьте правильность введенных данных или убедитесь, что запись существует.");
            });
        });


    }

    @Test
    
    @DisplayName("[api/goods-prices/{mxikCode}][GET] Получение запись об ценах топливо когда mxikCode правильный")
    void getGoodsPriceListByMxikCode() {
        // пока что оставлю статичный код, потом если будут траблы сделаю что бы генерился через хп
        var request = GoodsPricesRequest.builder().mxikCode(mxikCode).build();
        step("Отправить запрос", () -> {
            var response = ofdPriceListMsService.getPriceList(request, GoodsPricesResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getGoodsPriceOutputDtos()).as("goodsPriceOutputDtos").isNotEmpty()
                    .allSatisfy(item -> {
                        soft.assertThat(item.getId()).as("id").isNotNull();
                        soft.assertThat(item.getServiceId()).as("serviceId").isNotNull();
                        soft.assertThat(item.getMxikCode()).as("mxikCode").isEqualTo(mxikCode);
                        soft.assertThat(item.getPricePerUnit()).as("pricePerUnit").isGreaterThan(0L);
                        soft.assertThat(item.getPriceDate()).as("priceDate").isNotNull();
                    });
            });
                });
    }

    @Test
    
    @DisplayName("[api/goods-prices/{serviceId}][GET] Получение запись об ценах топливо когда serviceId правильный")
    void getGoodsPriceListByServiceId() {
        // пока что оставлю статичный ид, потом если будут траблы сделаю что бы генерился через хп
        var request = GoodsPricesRequest.builder().serviceId(serviceId).build();
        step("Отправить запрос", () -> {
            var response = ofdPriceListMsService.getPriceList(request, GoodsPricesResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getGoodsPriceOutputDtos()).as("goodsPriceOutputDtos").isNotEmpty()
                    .allSatisfy(item -> {
                        soft.assertThat(item.getId()).as("id").isNotNull();
                        soft.assertThat(item.getServiceId()).as("serviceId").isEqualTo(serviceId);
                        soft.assertThat(item.getMxikCode()).as("mxikCode").isNotBlank();
                        soft.assertThat(item.getPricePerUnit()).as("pricePerUnit").isGreaterThan(0L);
                        soft.assertThat(item.getPriceDate()).as("priceDate").isNotNull();
                    });
            });
        });
    }

    @ParameterizedTest
    @MethodSource("requiredParametersForGoodsPriceRequest")
    
    @DisplayName("[api/goods-prices/{id}{serviceId}{mxikCode}][GET] Получение запись об ценах топливо c параметрами id serviceId и mxikCode")
    void getGoodsPriceListByRequiredParameters(Long id, Integer serviceId, String mxikCode) {
        var request = GoodsPricesRequest.builder().id(id).serviceId(serviceId).mxikCode(mxikCode).build();
        step("Отправить запрос", () -> {
            var response = ofdPriceListMsService.getPriceList(request, GoodsPricesResponse.class);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getPage()).as("page").isEqualTo(0);
                soft.assertThat(response.getTotalPages()).as("totalPages").isGreaterThan(0);
                soft.assertThat(response.getTotalElements()).as("totalElements").isGreaterThan(0);

                soft.assertThat(response.getGoodsPriceOutputDtos()).as("goodsPriceOutputDtos").isNotEmpty()
                    .allSatisfy(item -> {
                        soft.assertThat(item.getId()).as("id").isNotNull();
                        soft.assertThat(item.getServiceId()).as("serviceId").isEqualTo(this.serviceId);
                        soft.assertThat(item.getMxikCode()).as("mxikCode").isEqualTo(this.mxikCode);
                        soft.assertThat(item.getPricePerUnit()).as("pricePerUnit").isGreaterThan(0L);
                        soft.assertThat(item.getPriceDate()).as("priceDate").isNotNull();
                    });
            });
                });
    }

    @ParameterizedTest
    @MethodSource("requiredInvalidParametersForGoodsPriceRequest")
    
    @DisplayName("[api/goods-prices/{id}{serviceId}{mxikCode}][GET] Получение запись об ценах топливо c не правильными параметрами id serviceId и mxikCode")
    void getGoodsPriceListByRequiredInvalidParameters(Long id, Integer serviceId, String mxikCode) {
        var request = GoodsPricesRequest.builder().id(id).serviceId(serviceId).mxikCode(mxikCode).build();
        step("Отправить запрос", () -> {
            var response = ofdPriceListMsService.getPriceList(request, GoodsPricesErrorResponse.class);
            step("Проверка ответа", () -> {

                soft.assertThat(response).as("response").isNotNull();
                soft.assertThat(response.getError()).as("error").isNotNull();

                soft.assertThat(response.getError().getCode()).as("error.code").isEqualTo("404");
                soft.assertThat(response.getError().getMessage()).as("error.message")
                    .isEqualTo("Ошибка: Ресурс не найден. Запрашиваемая запись не существует в системе.");
                soft.assertThat(response.getError().getTarget()).as("error.target").isNull();

                soft.assertThat(response.getError().getLocalize().getMessage()).as("error.localize.message")
                    .isEqualTo("Ресурс не найден");
                soft.assertThat(response.getError().getLocalize().getExtra()).as("error.localize.extra")
                    .isEqualTo("Проверьте правильность введенных данных или убедитесь, что запись существует.");
            });
        });
    }

    @Test
    
    @DisplayName("[api/goods-prices/{id}{serviceId}{mxikCode}{page}{size}][GET] Получение запись об ценах топливо cо всеми пустыми параметрами")
    void getGoodsPriceListByEmptyParameters() {
        Map<String, Object> request = new java.util.HashMap<>(Map.of());
        request.put("id", "");
        request.put("serviceId", "");
        request.put("mxikCode", "");
        request.put("page", "");
        request.put("size", "");
        step("Отправить запрос", () -> {
            var response = ofdPriceListMsService.getPriceListWithParams(request, GoodsPricesResponse.class);
            step("Проверка ответа", () -> {

                soft.assertThat(response.getPage()).as("page").isEqualTo(0);
                soft.assertThat(response.getTotalPages()).as("totalPages").isGreaterThan(0);
                soft.assertThat(response.getTotalElements()).as("totalElements").isGreaterThan(0);
                soft.assertThat(response.getGoodsPriceOutputDtos()).as("goodsPriceOutputDtos").isNotEmpty();
            });
        });
    }
}
