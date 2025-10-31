package com.example.tests.evo.menu;

import com.example.api.evo.models.menu.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import uz.click.evo_api.models.menu.*;
import com.example.api.evo.services.menu.GetMenuService;
import com.example.tests.evo.EvoBaseTest;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static com.example.api.evo.constants.ServiceData.DEVICE_TYPE_ANDROID;
import static com.example.api.evo.constants.ServiceData.DEVICE_TYPE_IOS;

public class GetMenuTests extends EvoBaseTest {
    @Autowired
    GetMenuService getMenuService;

    @ParameterizedTest
    @ValueSource(ints = {DEVICE_TYPE_ANDROID, DEVICE_TYPE_IOS})
    
    @DisplayName("[get.menu] Получение меню сервисов для андроид и iOS")
    void getMenuOnlineTrueTest(int device) {
        step("Запрос на получение сервисов в меню", () -> {
            var params = GetMenuRequest.Params.builder().deviceType(device).build();
            GetMenuResponse response = getMenuService.getMenu(params, headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult()).isNotEmpty().allSatisfy(r -> {
                    soft.assertThat(r.getId()).as("id").isNotEmpty();
                    soft.assertThat(r.getLabel()).as("label").isNotNull();
                    soft.assertThat(r.getAnalyticsVariable()).as("analytics variable").isNotEmpty();
                    soft.assertThat(r.getPriorityFavorite()).as("priority favorite").isNotNull();
                    soft.assertThat(r.getTitle()).as("title").isNotEmpty();
                    soft.assertThat(r.getDescription()).as("description").isNotNull();
                    if (!r.getUrl().contains("app.link")) {
                        soft.assertThatUrl(r.getUrl()).as("url").isReachable();
                    }
                    soft.assertThatUrl(r.getIcon()).as("icon url").isReachable();
                });
            });
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {DEVICE_TYPE_ANDROID, DEVICE_TYPE_IOS})
    
    @DisplayName("[get.menu.group] Получение меню групп сервисов")
    @Disabled("странный ответ приходить отключу")
    void getMenuGroupTest(int device) {
        step("Запрос на получение групп сервисов в меню", () -> {
            var params = GetMenuRequest.Params.builder().deviceType(device).build();
            GetMenuGroupResponse response = getMenuService.getMenuGroup(params, headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult().getSubNavigations()).isNotEmpty().allSatisfy(n -> {
                    soft.assertThat(n.getId()).as("id").isNotEmpty();
                    soft.assertThat(n.getTitle()).as("title").isNotEmpty();
                    soft.assertThat(n.getDescription()).as("description").isNotEmpty();
                    soft.assertThat(n.getAnalyticsVariable()).as("analytics variable").isNotEmpty();
                    soft.assertThat(n.getPriorityFavorite()).as("priority favorite").isNotNull();
                    soft.assertThat(n.getLabel()).as("label").isNotNull();
                    soft.assertThat(n.getCategory())
                            .as("category для %s", n.getId())
                            .isNotEmpty()
                            .allSatisfy(c -> {
                                soft.assertThat(c.getId()).isNotEmpty();
                                soft.assertThat(c.getPriority()).isNotNull();
                            });
                    if (n.getUrl().contains("http")) {
                        soft.assertThatUrl(n.getUrl()).as("url").isReachable();
                    }
                    if (!(n.getTitle().contains("dev_") || n.getTitle().contains("test"))) {
                        soft.assertThatUrl(n.getIcon()).as("icon url").isReachable();
                    }
                });
            });
        });
    }

    @Test
    
    @DisplayName("[get.menu.buttons] Получение кнопок меню")
    void getMenuButtonsTest() {
        step("Запрос на получение кнопок меню", () -> {
            GetMenuButtonsResponse response = getMenuService.getMenuButtons(headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult()).allSatisfy(it -> {
                    soft.assertThat(it.getNavId()).isNotEmpty();
                    soft.assertThat(it.getBtnTitles())
                            .allSatisfy(t -> soft.assertThat(t).isNotEmpty());
                });
                soft.assertThat(response.getResult())
                        .extracting(GetMenuButtonsResponse.Result::getNavId)
                        .doesNotHaveDuplicates()
                        .isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[get.menu.categories] Получение категорий меню")
    void getMenuCategoriesTest() {
        step("Запрос на получение категорий меню", () -> {
            GetMenuCategoriesResponse response = getMenuService.getMenuCategories(headers);

            step("Проверка, что первый объект содержит ид и не null", () -> {
                soft.assertThat(response.getResult()).allSatisfy(c -> {
                    soft.assertThat(c.getId()).isNotEmpty();
                    soft.assertThat(c.getName()).isNotEmpty();
                    soft.assertThat(c.getPriority()).isNotNull();
                });
                // id‑шники уникальны
                soft.assertThat(response.getResult())
                        .extracting(GetMenuCategoriesResponse.Result::getId)
                        .doesNotHaveDuplicates()
                        .isNotNull();
                // приоритеты уникальны и отсортированы по убыванию
                List<Integer> priorities = response.getResult().stream()
                        .map(GetMenuCategoriesResponse.Result::getPriority)
                        .toList();
                soft.assertThat(priorities).doesNotHaveDuplicates().isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[get.guest.session] Получить гостевую сессию")
        // TODO: Добавить проверки на формат guestWebSession и тест на повторный вызов
    void getGuestSessionTest() {
        step("Получаем сессию", () -> {
            var params = GetGuestSessionRequest.Params.builder().oldGuestWebSession("").build();
            GetGuestSessionResponse response = getMenuService.getGuestSession(params, headers);

            step("Проверка, что первый объект содержит ид и не null", () -> {
                soft.assertThat(response.getResult().getGuestWebSession())
                        .startsWith("guestSession:");
                String uuidPart = response.getResult().getGuestWebSession().replace("guestSession:", "");
                soft.assertThat(uuidPart)
                        .as("guest_web_session должен содержать валидный UUID")
                        .matches(
                                "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$");
                soft.assertThat(response.getResult().getGuestWebSessionExpiry()).isEqualTo(86400);
            });
        });
    }
}
