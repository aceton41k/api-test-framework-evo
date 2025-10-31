package com.example.tests.evo.favorite;

import com.example.api.evo.models.favorite.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.ResponseWithOkResult;
import uz.click.evo_api.models.favorite.*;
import com.example.api.evo.services.favorite.FavoriteService;
import com.example.tests.evo.EvoBaseTest;

import java.util.Optional;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.api.evo.constants.ServiceData.SERVICE_ID_BEE;

public class FavoriteServiceTests extends EvoBaseTest {
    @Autowired
    FavoriteService favoriteService;

    String favoriteId;

    @BeforeEach
    void setUp() {
        step("Создать избранный платеж и получить его ид", () -> {
            var parameters = FavouriteAddRequest.Parameters.builder()
                    .amount("1001")
                    .phoneNum(phoneNumber)
                    .favoriteName(phoneNumber)
                    .build();

            var params = FavouriteAddRequest.Params.builder()
                    .serviceId(SERVICE_ID_BEE)
                    .parameters(parameters)
                    .build();

            favoriteService.favoriteAdd(params, headers);
            var paramsForFavoriteList = FavouriteListRequest.Params.builder()
                    .build();
            Optional<FavoriteListResponse.Result> response =
                    favoriteService.getFavoritePaymentByName(paramsForFavoriteList,
                            headers,
                            phoneNumber);
            assertThat(response.isPresent()).isTrue();
            favoriteId = response.map(FavoriteListResponse.Result::getId).orElseThrow();
        });
    }

    @Test
    
    @DisplayName("[favorite.list] Получение списка избранных платежей")
    void favoriteListTest() {
        step("Получение избранных платежей", () -> {
            var paramsForFavoriteList = FavouriteListRequest.Params.builder()
                    .build();
            var response =
                    favoriteService.getFavoriteList(paramsForFavoriteList, headers);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult().getFirst().getServiceId()).isNotNull();
                soft.assertThat(response.getResult().getFirst().getName())
                        .isEqualTo(phoneNumber);
            });

        });
    }

    @Test
    
    @Order(2)
    @DisplayName("[favorite.edit] Изменить избранный платеж")
    void editFavoriteTest() {
        step("Запрос на изменения избранного платежа", () -> {
            var parameters = FavoriteEditRequest.Parameters.builder()
                    .favoriteName(phoneNumber + " edited")
                    .phoneNum(phoneNumber)
                    .amount(1001)
                    .build();

            var params = FavoriteEditRequest.Params.builder()
                    .id(favoriteId)
                    .parameters(parameters)
                    .build();

            ResponseWithOkResult response = favoriteService.editFavorite(params, headers);

            step("Проверка что в ответе получили ок", () -> assertThat(response.getResult())
                    .isEqualTo("ok"));
        });
        step("Получить список избранных платежей и проверить что избранный платеж изменился", () -> {
            var paramsForFavoriteList = FavouriteListRequest.Params.builder()
                    .build();
            assertThat(favoriteService
                    .getFavoritePaymentByName(paramsForFavoriteList,
                            headers,
                            phoneNumber + " edited")
                    .isPresent()).isTrue();
        });
    }

    @Test
    
    @DisplayName("[favorite.data] Информация о форме избранного платежа")
    void favoriteDataTest() {
        step("Получаем информацию о полях избранного платежа", () -> {
            var params = FavoriteDataRequest.Params.builder()
                    .id(favoriteId)
                    .build();
            FavoriteDataResponse response = favoriteService.getFavoriteData(params, headers);

            step("Проверка что в ответе получили result", () -> assertThat(response.getResult())
                    .isNotNull());
        });
    }
}
