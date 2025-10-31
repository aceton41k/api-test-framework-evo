package com.example.tests.ofd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.ofd.services.RecipeService;

import java.util.Map;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static com.example.api.ofd.constants.OfdData.DEFAULT_PAGE_NUMBER;
import static com.example.api.ofd.constants.OfdData.DEFAULT_PAGE_SIZE;

public class RecipeTests extends OfdApiBaseTest {
    Map<String, Object> defaultParams = Map.of(
            "service_id", 1,
            "page-number", DEFAULT_PAGE_NUMBER,
            "page-size", DEFAULT_PAGE_SIZE);

    @Autowired
    RecipeService recipeService;

    @Test
    
    @DisplayName("[v3/recipe][POST] Создание рецептов")
    void createRecipeTest() {
        var response = recipeService.getRecipes(defaultParams);
        if (response.length != 0) {
            step("Удалить созданный рецепт",
                    () -> recipeService.deleteRecipe(response[0].getId()));
        }
        var createResponse = step("Создать рецепт", () -> recipeService.createRecipe());
        step("Проверка ответа", () -> {
            assertThat(createResponse).isNotNull();
            assertThat(createResponse.getId()).isNotNull();
            assertThat(createResponse.getId()).isNotEqualTo(0);
            assertThat(createResponse.getServiceId()).isEqualTo(1);
        });
        step("Удалить созданный рецепт",
                () -> recipeService.deleteRecipe(createResponse.getId()));
    }

    @Test
    
    @DisplayName("[v3/recipe][DELETE]Удаление рецептов")
    void deleteRecipeTest() {
        Integer recipeId;
        var response = recipeService.getRecipes(defaultParams);
        if (response.length == 0) {
            recipeId = step("Создать рецепт",
                    () -> recipeService.createRecipe().getId());
        } else recipeId = response[0].getId();
        var responseDelete = step("Отправить запрос на удаление рецепта",
                () -> recipeService.deleteRecipe(recipeId));
        step("Проверка ответа", () -> {
            assertThat(responseDelete.statusCode()).isEqualTo(204);
        });
    }

    @Test
    
    @DisplayName("[v3/recipe][GET] Получение рецептов по service id")
    void getRecipesTest() {
        var createResponse = step("Создать рецепт", () -> recipeService.createRecipe());
        var recipes = step("Получение рецептов", () -> recipeService.getRecipes(defaultParams));
        step("Проверка ответа", () -> {
            assertThat(recipes).isNotEmpty();
            assertThat(recipes[0].getId()).isNotNull();
            assertThat(recipes[0].getServiceId()).isEqualTo(createResponse.getServiceId());
        });
        step("Удалить созданный рецепт",
                () -> recipeService.deleteRecipe(createResponse.getId()));
    }

    @Test
    
    @DisplayName("[v3/recipe][PUT] Обновление рецептов")
    void updateRecipeTest() {
        Integer recipeId;
        String sourceField;
        var getRecipeResponse = recipeService.getRecipes(defaultParams);
        if (getRecipeResponse.length == 0) {
            var createResponse = step("Создать рецепт", () -> recipeService.createRecipe());
            recipeId = createResponse.getId();
            sourceField = createResponse.getConditions().getFirst().getSourceField();
        } else {
            recipeId = getRecipeResponse[0].getId();
            sourceField = getRecipeResponse[0].getConditions().getFirst().getSourceField();
        }
        var updateResponse = step("Обновить рецепт", () -> recipeService.updateRecipe(recipeId));
        step("Проверка ответа", () -> {
            assertThat(updateResponse).isNotNull();
            assertThat(updateResponse.getId()).isNotNull();
            assertThat(updateResponse.getServiceId()).isEqualTo(1);
            assertThat(updateResponse.getPriority()).isEqualTo(2);
            assertThat(updateResponse.getConditions().getFirst().getSourceField())
                    .isEqualTo("upd" + sourceField);
        });
        step("Удалить обновлённый рецепт",
                () -> recipeService.deleteRecipe(updateResponse.getId()));
    }

    @Test
    
    @DisplayName("[v3/recipe/chronology-of-changes][GET] Получение истории изменений рецептов")
    void getRecipeHistoryTest() {
        var createResponse = step("Создать рецепт", () -> recipeService.createRecipe());
        var updateResponse = step("Обновить рецепт", () -> recipeService.updateRecipe(createResponse.getId()));
        step("Удалить созданный рецепт",
                () -> recipeService.deleteRecipe(updateResponse.getId()));
        Map<String, Object> params = Map.of(
                "recipe_id", String.valueOf(createResponse.getId()),
                "service_id", createResponse.getServiceId());
        step("Получить историю изменений", () -> {
            var recipeService = this.recipeService.getChronologyOfChanges(params);

            step("Проверка ответа", () -> {
                assertThat(recipeService).isNotEmpty();
                assertThat(recipeService[0].getHistoryId()).isNotNull();
                assertThat(recipeService[0].getRecipeId()).isEqualTo(createResponse.getId());
                assertThat(recipeService[0].getServiceId()).isEqualTo(createResponse.getServiceId());
                assertThat(recipeService[0].getExecutedOperation()).isEqualTo("INSERT");
                assertThat(recipeService[1].getHistoryId()).isNotNull();
                assertThat(recipeService[1].getServiceId()).isEqualTo(updateResponse.getServiceId());
                assertThat(recipeService[1].getDataAfterExecutedOperation().getPriority())
                        .isEqualTo(updateResponse.getPriority());
                assertThat(recipeService[1].getExecutedOperation()).isEqualTo("UPDATE");
                assertThat(recipeService[2].getHistoryId()).isNotNull();
                assertThat(recipeService[2].getExecutedOperation()).isEqualTo("DELETE");
            });
        });
    }
}
