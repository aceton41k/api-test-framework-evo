package com.example.tests.evo.favorite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.ResponseWithOkResult;
import com.example.api.evo.models.favorite.FavouriteAddRequest;
import com.example.api.evo.models.favorite.FavouriteDeleteRequest;
import com.example.api.evo.models.favorite.FavouriteListRequest;
import com.example.api.evo.services.favorite.FavoriteService;
import com.example.tests.evo.EvoBaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.api.evo.constants.ServiceData.SERVICE_ID_BEE;

public class FavoriteServiceDeleteTests extends EvoBaseTest {
    @Autowired
    FavoriteService favoriteService;

    String favoriteId;

    @BeforeEach
    void setUp() {
        var parameters = FavouriteAddRequest.Parameters.builder()
                .amount("1001")
                .favoriteName("test")
                .phoneNum(phoneNumber)
                .build();

        var params = FavouriteAddRequest.Params.builder()
                .serviceId(SERVICE_ID_BEE)
                .parameters(parameters)
                .build();

        favoriteService.favoriteAdd(params, headers);
    }

    @Test
    
    @DisplayName("[favorite.remove] Удалить избранный платеж")
    void deleteFavoriteTest() {
        step("Получаем ид первого избранного платежа", () -> {
            FavouriteListRequest.Params params = FavouriteListRequest.Params.builder()
                    .build();
            favoriteId = favoriteService
                    .getFavoriteList(params, headers)
                    .getResult()
                    .getFirst()
                    .getId();
        });
        step("Запрос на удаление избранного платежа", () -> {
            FavouriteDeleteRequest.Params params =
                    FavouriteDeleteRequest.Params.builder().id(favoriteId).build();
            ResponseWithOkResult response = favoriteService.deleteFavorite(params, headers);

            step("Проверка, что ответ содержит избранных платежей", () -> assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
    }
}
