package com.example.tests.evo.favorite;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.favorite.FavoriteFromPaymentRequest;
import com.example.api.evo.models.favorite.FavoriteListResponse;
import com.example.api.evo.models.favorite.FavouriteAddRequest;
import com.example.api.evo.models.favorite.FavouriteListRequest;
import com.example.api.evo.services.favorite.FavoriteService;
import com.example.tests.evo.EvoBaseTest;

import java.util.Optional;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.api.evo.constants.AccountData.XAS_PAYMENT_ID;
import static com.example.api.evo.constants.ServiceData.SERVICE_ID_BEE;

public class FavoriteServiceAddTests extends EvoBaseTest {
    @Autowired
    FavoriteService favoriteService;

    String favoriteId;

    @Test
    
    @DisplayName("[favorite.add] Добавить избранный платеж")
    void addFavoriteTest() {
        String randomFavName = RandomStringUtils.randomAlphabetic(5);
        step("Запрос на добавления избранного платежа", () -> {
            var parameters = FavouriteAddRequest.Parameters.builder()
                    .amount("1001")
                    .phoneNum(phoneNumber)
                    .favoriteName(randomFavName)
                    .build();

            var params = FavouriteAddRequest.Params.builder()
                    .serviceId(SERVICE_ID_BEE)
                    .parameters(parameters)
                    .build();
            ResponseWithOkResult response = favoriteService.favoriteAdd(params, headers);

            step("Проверка, что ответ содержит ок ответ", () -> {
                assertThat(response.getResult()).isEqualTo("ok");
            });
        });
        step("Запрос на получение списка избранных платежей", () -> {
            FavouriteListRequest.Params params = FavouriteListRequest.Params.builder()
                    .build();
            Optional<FavoriteListResponse.Result> response =
                    favoriteService.getFavoritePaymentByName(params, headers, randomFavName);
            favoriteId = response.map(FavoriteListResponse.Result::getId).orElseThrow();

            step("Проверка, что ответ содержит избранных платежей", () -> {
                assertThat(favoriteId).isNotEqualTo("0");
            });
        });
    }

    @Test
    
    @DisplayName("[favorite.from.payment] Добавления избранного платежа из оплат")
    @Disabled("Пока что отключу так как нужна оплата")
    void favoriteFromPaymentTest() {
        step("Проверка добавления избранного платежа из оплат", () -> {
            FavoriteFromPaymentRequest.Params params = FavoriteFromPaymentRequest.Params.builder()
                    .paymentId(XAS_PAYMENT_ID)
                    .build();
            ResponseWithOkResult response = favoriteService.addFavoriteFromPayment(params,
                    headers);

            step("Проверка, что в ответе получили result", () -> assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
    }
}
