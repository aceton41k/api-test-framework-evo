package com.example.tests.evo.menu;

import com.example.api.evo.models.menu.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.BaseErrorResponse;
import uz.click.evo_api.models.menu.*;
import com.example.api.evo.services.menu.GetMenuService;
import com.example.api.evo.services.menu.MenuService;
import com.example.tests.evo.EvoBaseTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static io.qameta.allure.Allure.step;
import static com.example.api.evo.constants.ServiceData.DEVICE_TYPE_ANDROID;
import static com.example.api.evo.constants.ServiceData.DEVICE_TYPE_IOS;

public class MenuTests extends EvoBaseTest {
    @Autowired
    GetMenuService getMenuService;

    @Autowired
    MenuService menuService;

    @Test
    
    @DisplayName("[menu.favorite.add] Добавить сервис (меню) в избранное")
    void addMenuFavoritesTest() {
        step("Получаем id меню", () -> {
            List<String> menuIds =
                    getMenuService.getMenu(GetMenuRequest.Params.builder().build(), headers)
                            .getResult()
                            .stream()
                            .limit(3) // берём первые три иды сервиса
                            .map(GetMenuResponse.Result::getId)
                            .toList();

            step("Добавляем в избранное и проверяем ответ", () -> {
                var params =
                        MenuFavoritesAddRequest.Params.builder().navIds(menuIds).build();
                MenuFavoritesAddResponse response =
                        menuService.addFavoriteMenus(params,
                                headers,
                                MenuFavoritesAddResponse.class);
                // проверка список navId совпадает c тем, что отправили
                soft.assertThat(response.getResult())
                        .extracting(MenuFavoritesAddResponse.Result::getNavId)
                        .containsExactlyInAnyOrderElementsOf(menuIds);
                // все priority != null
                soft.assertThat(response.getResult())
                        .extracting(MenuFavoritesAddResponse.Result::getPriority)
                        .doesNotContainNull();
            });
        });
    }

    @Test
    
    @Tag("negative")
    @DisplayName("[menu.favorite.add] Отправить запрос на добавление в избранное только с пустым параметром")
    void addMenuFavoritesNegativeTest() {
        step("Получаем id меню", () -> {
            List<String> menuIds = Collections.singletonList("");

            step("Добавляем в избранное и проверяем ответ", () -> {
                var params =
                        MenuFavoritesAddRequest.Params.builder().navIds(menuIds).build();
                BaseErrorResponse response = menuService.addFavoriteMenus(params,
                        headers,
                        BaseErrorResponse.class);

                step("Проверяем корректность белого списка", () -> {
                    soft.assertThat(response.getError().getCode()).isEqualTo(-32500);
                });
            });
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {DEVICE_TYPE_ANDROID, DEVICE_TYPE_IOS})
    
    @DisplayName("[menu.favorites.list] Получение список избранных меню")
    void getMenuFavoritesListTest(int deviceType) {
        step("Запрос на получение избранных меню", () -> {
            var params = GetMenuRequest.Params.builder().deviceType(deviceType).build();
            GetMenuResponse response = menuService.getMenuFavoritesList(params, headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult()).allSatisfy(r -> {
                    soft.assertThat(r.getId()).as("id").isNotEmpty();
                    soft.assertThat(r.getLabel()).as("label").isNotNull();
                    soft.assertThat(r.getAnalyticsVariable())
                            .as("analytics variable")
                            .isNotEmpty();
                    soft.assertThat(r.getPriorityFavorite())
                            .as("priority favorite")
                            .isNotNull();
                    soft.assertThat(r.getTitle()).as("title").isNotEmpty();
                    soft.assertThat(r.getCategory()).allSatisfy(c -> {
                        soft.assertThat(c.getId()).as("category id").isNotEmpty();
                        soft.assertThat(c.getPriority()).as("category priority").isNotNull();
                    });
                    soft.assertThatUrl(r.getUrl()).as("url").isReachable();
                });
            });
        });
    }

    @Test
    
    @DisplayName("[webview.whitelist] Получение списка URL из белого списка")
    void getWhiteListTest() {
        step("Запрос на получение URL", () -> {
            WhiteListResponse response = menuService.getWebViewWhiteList();

            step("Проверка корректности белого списка", () -> {
                soft.assertThat(response.getResult())
                        .allSatisfy(p -> {
                            soft.assertThat(p).as("пустой элемент").isNotEmpty();
                            soft.assertThat(p).as("должен начинаться с https").startsWith("^https:\\/\\/");
                            //noinspection ResultOfMethodCallIgnored
                            soft.assertThatCode(() -> Pattern.compile(p))
                                    .as("некорректный regex: %s", p)
                                    .doesNotThrowAnyException();
                        });
            });
        });
    }

    @Test
    
    @DisplayName("[menu.search] Поиск меню по тексту")
    @Disabled("504 ошибка пока отключу")
    void searchMenuTest() {
        step("Запрос на поиск меню", () -> {
            var params = MenuSearchRequest.Params.builder().search("travel").build();
            GetMenuResponse response = menuService.searchMenu(params, deprecatedHeaders);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult()).isNotEmpty().allSatisfy(r -> {
                    soft.assertThat(r.getId()).isNotEmpty();
                    soft.assertThat(r.getLabel()).isNotNull();
                    soft.assertThat(r.getAnalyticsVariable()).isNotEmpty();
                    soft.assertThat(r.getPriorityFavorite()).isNotNull();
                    soft.assertThat(r.getTitle()).isNotEmpty();
                    soft.assertThat(r.getTitle())
                            .as("title должен содержать текст 'travel'")
                            .containsIgnoringCase("travel");
                    soft.assertThat(r.getCategory()).allSatisfy(c -> {
                        soft.assertThat(c.getId()).isNotEmpty();
                        soft.assertThat(c.getPriority()).isNotNull();
                    });
                    // проверяем, что картинки/иконки доступны
                    soft.assertThatUrl(r.getUrl()).isReachable();
                });
            });
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "test123test123test"})
    
    @DisplayName("[menu.search] Поиск меню по тексту")
    @Disabled("504 ошибка пока отключу")
    void searchMenuNegativeTest(String searchText) {
        step("Запрос на поиск меню", () -> {
            var params = MenuSearchRequest.Params.builder().search(searchText).build();
            GetMenuResponse response = menuService.searchMenu(params, deprecatedHeaders);

            step("Проверка ответа", () ->
                    soft.assertThat(response.getResult()).isEmpty());
        });
    }

    @Test
    
    @DisplayName("[menu.search.get.defaults] Получение дефолтных меню")
    void searchMenuGetDefaultsTest() {
        step("Запрос на получение меню", () -> {
            MenuSearchGetDefaultsResponse response = menuService.searchMenuGetDefaults(headers);

            step("Проверка ответа", () -> {
                // проверка категорий
                soft.assertThat(response.getResult()).allSatisfy(r ->
                        soft.assertThat(r.getCategoryTitle()).isNotEmpty());
                // проверка сервисов в первом категории
                soft.assertThat(response.getResult().getFirst().getServices()).allSatisfy(r -> {
                    soft.assertThat(r.getId()).isNotEmpty();
                    soft.assertThat(r.getLabel()).isNotNull();
                    soft.assertThat(r.getTitle()).isNotEmpty();
                    soft.assertThat(r.getDescription()).isNotEmpty();
                    soft.assertThat(r.getAnalyticsVariable()).isNotEmpty();
                    soft.assertThat(r.getPriorityFavorite()).isNotNull();
                    soft.assertThat(r.getCategory()).allSatisfy(c -> {
                        soft.assertThat(c.getId()).isNotEmpty();
                        soft.assertThat(c.getPriority()).isNotNull();
                    });
                    // проверяем, что картинки/иконки доступны
                    soft.assertThatUrl(r.getUrl()).isReachable();
                    Optional.ofNullable(r.getBackground())
                            .filter(bg -> !bg.isBlank())
                            .ifPresent(bg -> soft.assertThatUrl(bg).isReachable());
                });
            });
        });
    }

    @Test
    
    @DisplayName("[mini.app.get.regions] Получить список регионов для фильтра по регионам")
    void getRegionListForRegionFilterTest() {
        step("Запрос на получение меню", () -> {
            GetRegionListForRegionFilterResponse response = menuService.getRegionListForRegionFilter(headers);
            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult()).isNotEmpty().allSatisfy(r -> {

                    soft.assertThat(r.getId()).isNotEmpty();
                    soft.assertThat(r.getTitle()).isNotEmpty();
                    soft.assertThat(r.getIsSelected()).isIn(true, false);
                });
            });
        });
    }
}
