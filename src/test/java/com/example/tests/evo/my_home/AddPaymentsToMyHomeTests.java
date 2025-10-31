package com.example.tests.evo.my_home;

import com.example.api.evo.models.myhome.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.constants.AccountData;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.favorite.FavoriteListResponse;
import com.example.api.evo.models.favorite.FavouriteAddRequest;
import com.example.api.evo.models.favorite.FavouriteDeleteRequest;
import com.example.api.evo.models.favorite.FavouriteListRequest;
import uz.click.evo_api.models.myhome.*;
import com.example.api.evo.services.favorite.FavoriteService;
import com.example.api.evo.services.myhome.MyHomePaymentsService;
import com.example.api.evo.services.myhome.MyHomeService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.api.evo.constants.ServiceData.SERVICE_ID_BEE;


public class AddPaymentsToMyHomeTests extends EvoBaseTest {
    @Autowired
    MyHomeService myHomeService;

    @Autowired
    MyHomePaymentsService myHomePaymentsService;

    @Autowired
    FavoriteService favoriteService;

    Long homeId;
    String favoriteId;
    String randomPhoneNumber = "998" + RandomStringUtils.randomNumeric(9);

    @BeforeEach
    void setUpBeforeEach() {
        step("Создать дом перед тестом", () -> {
            var params = MyHomeSaveRequest.Params.builder()
                    .myhomeId(0L)
                    .name("Test Home")
                    .build();
            homeId = myHomeService.saveHomeById(params, headers).getResult().getMyhomeId();
        });
    }

    @Test
    
    @DisplayName("[myhome.payments.save] Добавить платеж в дом")
    void createPaymentForMyHomeTest() {
        step("Запрос на добавление платежа", () -> {
            var parameters = MyHomePaymentsSaveRequest.Parameters.builder()
                    .amount("1000.0")
                    .phoneNum(randomPhoneNumber)
                    .build();

            var params = MyHomePaymentsSaveRequest.Params.builder()
                    .serviceId(SERVICE_ID_BEE)
                    .myHomeId(homeId)
                    .parameters(parameters)
                    .build();

            ResponseWithOkResult response = myHomePaymentsService.addPaymentToMyHome(params,
                    headers);

            step("Проверка, что ответ содержит ок ответ", () -> assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
        step("Проверка, что платеж есть в доме", () -> {
            MyHomePaymentsListRequest.Params params =
                    MyHomePaymentsListRequest.Params.builder().myhomeId(homeId).build();
            MyHomePaymentsListResponse response = myHomePaymentsService.getMyHomePaymentList(params,
                    headers);

            step("Проверка, что ответ содержит платежей", () -> {
                soft.assertThat(response.getResult().getFirst().getId()).isNotEqualTo(0);
            });
        });
    }

    @Test
    
    @DisplayName("[myhome.from.payment] Добавить платеж в мой дом из платежей")
    @Disabled("пока что отключу нужна оплата")
    void addMyHomePaymentFromPaymentTest() {
        step("Запрос на добавление платежа в мой дом из оплат", () -> {
            var params = MyHomeFromPaymentRequest.Params.builder()
                    .paymentId(AccountData.XAS_PAYMENT_ID)
                    .myhomeId(homeId)
                    .build();
            ResponseWithOkResult response = myHomePaymentsService.addToMyHomeFromPayment(params,
                    headers);

            step("Проверка, что ответ содержит ок ответ", () -> assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
        step("Проверка, что платеж есть в доме", () -> {
            MyHomePaymentsListRequest.Params params =
                    MyHomePaymentsListRequest.Params.builder().myhomeId(homeId).build();
            MyHomePaymentsListResponse response = myHomePaymentsService.getMyHomePaymentList(params,
                    headers);

            step("Проверка, что ответ содержит платежей", () -> {
                soft.assertThat(response.getResult().getFirst().getId()).isNotEqualTo(0);
            });
        });
    }

    @Test
    
    @DisplayName("[myhome.from.favorite] Добавить платеж в мой дом из избранных платежей")
    void addMyHomePaymentFromFavoriteTest() {
        step("Добавить избранный платеж перед тестом и получить его ид", () -> {
            var parameters = FavouriteAddRequest.Parameters.builder()
                    .amount("1001")
                    .phoneNum(phoneNumber)
                    .favoriteName("test")
                    .build();

            var params = FavouriteAddRequest.Params.builder()
                    .serviceId(SERVICE_ID_BEE)
                    .parameters(parameters)
                    .build();

            favoriteService.favoriteAdd(params, headers);
            var paramsForFavoriteList = FavouriteListRequest.Params.builder().build();

            FavoriteListResponse response = favoriteService.getFavoriteList(paramsForFavoriteList,
                    headers);
            favoriteId = response.getResult().getFirst().getId();
        });
        step("Запрос на добавление платежа в мой дом из избранных", () -> {
            var params = MyHomeFromFavoriteRequest.Params.builder()
                    .id(favoriteId)
                    .myhomeId(homeId)
                    .build();
            ResponseWithOkResult response = myHomePaymentsService.addToMyHomeFromFavorite(params,
                    headers);

            step("Проверка, что ответ содержит ок ответ", () -> soft.assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
        step("Проверка, что платеж есть в доме", () -> {
            MyHomePaymentsListRequest.Params params =
                    MyHomePaymentsListRequest.Params.builder().myhomeId(homeId).build();
            MyHomePaymentsListResponse response = myHomePaymentsService.getMyHomePaymentList(params,
                    headers);

            step("Проверка, что ответ содержит платеж", () -> {
                soft.assertThat(response.getResult().getFirst().getId()).isNotNull();
                soft.assertThat(response.getResult().getFirst().getId()).isNotEqualTo(0);
            });
        });
        step("Удаляем избранный платеж после теста", () -> {
            FavouriteDeleteRequest.Params params =
                    FavouriteDeleteRequest.Params.builder().id(favoriteId).build();
            favoriteService.deleteFavorite(params, headers);
        });
    }
}
