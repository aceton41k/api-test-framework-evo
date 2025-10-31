package com.example.tests.vitrina.taom_api;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.vitrina.taom_api.constants.TaomConstants;
import com.example.api.vitrina.taom_api.models.TaomCartDeliveryRequest;
import com.example.api.vitrina.taom_api.models.TaomCartResponse;
import com.example.api.vitrina.taom_api.services.TaomCartService;
import com.example.api.vitrina.taom_api.services.TaomProductService;
import com.example.tests.vitrina.VitrinaBaseTest;

import java.util.Arrays;
import java.util.stream.IntStream;

import static io.qameta.allure.Allure.step;


public class TaomCartTest extends VitrinaBaseTest {
    @Autowired
    TaomCartService taomCartService;

    @Autowired
    TaomProductService taomProductService;

    String token;

    @BeforeAll
    public void getToken() {
        token = operations.login(userData.getUser("NIK")).getWebSession();
    }

    @BeforeEach
    public void cleanUp() {
        Arrays.asList(TaomConstants.SINGLE_IKPU_SERVICE_ID, TaomConstants.MULTIPLE_IKPU_SERVICE_ID)
                .forEach(serviceId -> taomCartService.clearCart(serviceId, token));
    }

    @ParameterizedTest
    @ValueSource(strings = {TaomConstants.SINGLE_IKPU_SERVICE_ID, TaomConstants.MULTIPLE_IKPU_SERVICE_ID})
    
    @DisplayName("[/:merchant_id/:service_id/cart] [GET] Получение пустой корзины")
    void testGetEmptyCart(String serviceId) {
        step("GET /:merchant_id/:service_id/cart", () -> {
            Response response = taomCartService.getCart(serviceId, token);

            step("Expected Result", () -> {
                step("Проверка статус кода и валидация по json_schema", () -> {
                    soft.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response).validateJsonSchema("json_schema/vitrina/empty-cart.schema.json");
                });
            });
        });
    }

    @ParameterizedTest
    @CsvSource({
            TaomConstants.SINGLE_IKPU_SERVICE_ID + ", 9, 10",
            TaomConstants.SINGLE_IKPU_SERVICE_ID + ", 10, 10",
            TaomConstants.MULTIPLE_IKPU_SERVICE_ID + ", 9, 10",
            TaomConstants.MULTIPLE_IKPU_SERVICE_ID + ", 10, 10"
    })
    
    @DisplayName("[/:merchant_id/:service_id/cart/add-item/:product_id] [POST]  Добавление/увеличение количества товара в корзину")
    void testAddItemToCart(String serviceId, int initialAmount, int expectedAmount) {
        step("Сформировать корзину");
        String productId = taomProductService.getAllProducts(serviceId).jsonPath().getString("[0].id");
        IntStream.range(0, initialAmount)
                .forEach(i -> taomCartService.addItemToCart(serviceId, productId, token));

        step("Отправить POST запрос", () -> {
            Response response = taomCartService.addItemToCart(serviceId, productId, token);

            step("Expected Result", () -> {
                step("Проверка статус кода и валидация по json_schema", () -> {
                    soft.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response).validateJsonSchema("json_schema/vitrina/cart.schema.json");
                });

                step("Проверка количества товара", () -> {
                    TaomCartResponse cartResponse = response.as(TaomCartResponse.class);
                    soft.assertThat(cartResponse.getProductsAmount()).isEqualTo(expectedAmount);
                    soft.assertThat(cartResponse.getProducts().getFirst().getAmount()).isEqualTo(expectedAmount);
                    soft.assertThat(cartResponse.getProducts().getFirst().getId()).isEqualTo(productId);
                });
            });
        });

    }

    @ParameterizedTest
    @CsvSource({
            TaomConstants.SINGLE_IKPU_SERVICE_ID + ", 10, 9",
            TaomConstants.MULTIPLE_IKPU_SERVICE_ID + ", 10, 9"
    })
    
    @DisplayName("[/:merchant_id/:service_id/cart/minus-item/product_id] [POST] Уменьшение количества товара")
    void testSubItemFromCart(String serviceId, int initialAmount, int expectedAmount) {
        step("Сформировать корзину");
        String productId = taomProductService.getAllProducts(serviceId).jsonPath().getString("[0].id");
        IntStream.range(0, initialAmount)
                .forEach(i -> taomCartService.addItemToCart(serviceId, productId, token));

        step("Отправить POST-запрос", () -> {
            Response response = taomCartService.subItemFromCart(serviceId, productId, token);

            step("Expected Result", () -> {
                step("Проверка статус кода и валидация по json_schema", () -> {
                    soft.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response).validateJsonSchema("json_schema/vitrina/cart.schema.json");
                });
                step("Проверка количества товара", () -> {

                    TaomCartResponse cartResponse = response.as(TaomCartResponse.class);
                    soft.assertThat(cartResponse.getProductsAmount()).isEqualTo(expectedAmount);
                    soft.assertThat(cartResponse.getProducts().getFirst().getAmount()).isEqualTo(expectedAmount);
                    soft.assertThat(cartResponse.getProducts().getFirst().getId()).isEqualTo(productId);
                });
            });
        });


    }

    @ParameterizedTest
    @CsvSource({
            TaomConstants.SINGLE_IKPU_SERVICE_ID + ", 1",
            TaomConstants.SINGLE_IKPU_SERVICE_ID + ", 0",
            TaomConstants.MULTIPLE_IKPU_SERVICE_ID + ", 1",
            TaomConstants.MULTIPLE_IKPU_SERVICE_ID + ", 0"
    })
    
    @DisplayName("[/:merchant_id/:service_id/cart/minus-item/product_id] [POST] Удаление товара из корзины")
    void testDeleteItemFromCart(String serviceId, int initialAmount) {
        step("Сформировать корзину");
        String productId = taomProductService.getAllProducts(serviceId).jsonPath().getString("[0].id");
        IntStream.range(0, initialAmount)
                .forEach(i -> taomCartService.addItemToCart(serviceId, productId, token));

        step("Отправить POST-запрос", () -> {
            Response response = taomCartService.subItemFromCart(serviceId, productId, token);

            step("Expected Result", () -> {
                step("Проверка статус кода и валидация по json_schema", () -> {
                    soft.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response).validateJsonSchema("json_schema/vitrina/empty-cart.schema.json");
                });
            });
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {TaomConstants.SINGLE_IKPU_SERVICE_ID, TaomConstants.MULTIPLE_IKPU_SERVICE_ID})
    
    @DisplayName("[/:merchant_id/service_id/cart/clear-cart] [POST]  Очистка корзины")
    void testClearCart(String serviceId) {
        step("Сформировать корзину");
        String productId = taomProductService.getAllProducts(serviceId).jsonPath().getString("[0].id");
        taomCartService.addItemToCart(serviceId, productId, token);

        step("Отправить POST-запрос", () -> {
            Response response = taomCartService.clearCart(serviceId, token);

            step("Expected Result", () -> {
                step("Проверка статус кода и валидация по json_schema", () -> {
                    soft.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response).validateJsonSchema("json_schema/vitrina/empty-cart.schema.json");
                });
            });
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {TaomConstants.SINGLE_IKPU_SERVICE_ID, TaomConstants.MULTIPLE_IKPU_SERVICE_ID})
    
    @DisplayName("[/:merchant_id/:service_id/cart/delivery-info/:order_id] [POST] Заполнить данные доставки заказа")
    void testCartDeliveryInfoUpdate(String serviceId) {
        step("Сформировать тело запроса");
        String productId = taomProductService.getAllProducts(serviceId).jsonPath().getString("[0].id");
        String orderId = taomCartService.addItemToCart(serviceId, productId, token).jsonPath().getString("id");

        TaomCartDeliveryRequest body = TaomCartDeliveryRequest.builder()
                .lat(41.326713572481474)
                .lng(69.31327051749189)
                .deliveryDate("Thu Oct 23 2025 23:50:13 GMT+0500 (Узбекистан, стандартное время)")
                .deliveryTime("10:00 - 11:00")
                .address("Саларбуйи улица,Дархан махалля,Мирзо-Улугбекский район,Ташкент")
                .houseNumber("35A")
                .type("delivery")
                .build();

        step("Отправить POST запрос", () -> {
            Response response = taomCartService.updateCartDeliveryOrPickupInfo(body, serviceId, orderId, token);

            step("Expected Result", () -> {
                step("Проверка статус кода и валидация по json_schema", () -> {
                    soft.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response).validateJsonSchema("json_schema/vitrina/cart.schema.json");
                });
                step("Проверка ответа", () -> {
                    TaomCartResponse cartResponse = response.as(TaomCartResponse.class);

                    soft.assertThat(cartResponse.getType()).isEqualTo("delivery");
                    soft.assertThat(cartResponse.getAddress()).isEqualTo("Саларбуйи улица,Дархан махалля,Мирзо-Улугбекский район,Ташкент");
                    soft.assertThat(cartResponse.getHouseNumber()).isEqualTo("35A");
                });
            });
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {TaomConstants.SINGLE_IKPU_SERVICE_ID, TaomConstants.MULTIPLE_IKPU_SERVICE_ID})
    
    @DisplayName("[/:merchant_id/:service_id/cart/delivery-info/:order_id] [POST] Заполнить данные самовывоза заказа")
    void testCartPickInfoUpdate(String serviceId) {
        step("Сформировать тело запроса");
        String productId = taomProductService.getAllProducts(serviceId).jsonPath().getString("[0].id");
        String orderId = taomCartService.addItemToCart(serviceId, productId, token).jsonPath().getString("id");

        TaomCartDeliveryRequest body = TaomCartDeliveryRequest.builder()
                .type("pickup")
                .build();

        step("Отправить POST запрос", () -> {
            Response response = taomCartService.updateCartDeliveryOrPickupInfo(body, serviceId, orderId, token);
            step("Expected Result", () -> {
                step("Проверка статус кода и валидация по json_schema", () -> {
                    soft.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
                    soft.assertThat(response).isNotNull();
                    soft.assertThat(response).validateJsonSchema("json_schema/vitrina/cart.schema.json");
                });
                step("Проверка ответа", () -> {
                    TaomCartResponse cartResponse = response.as(TaomCartResponse.class);

                    soft.assertThat(cartResponse.getType()).isEqualTo("pickup");
                });
            });
        });

    }
}
