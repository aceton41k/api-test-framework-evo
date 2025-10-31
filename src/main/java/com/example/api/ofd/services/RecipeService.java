package com.example.api.ofd.services;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.ofd.OfdRequestSpecification;
import com.example.api.ofd.models.settings_ms.ChronologyOfChangesResponse;
import com.example.api.ofd.models.settings_ms.RecipeRequestFactory;
import com.example.api.ofd.models.settings_ms.RecipeResponse;

import java.util.Map;

@Service
public class RecipeService {
    private static final String OFD_RECIPE_BASE_PATH = "/v3/recipe";

    @Autowired
    private OfdRequestSpecification ofdReqSpec;

    public RecipeResponse createRecipe() {
        return ofdReqSpec.getSettingsReqSpec()
                .basePath(OFD_RECIPE_BASE_PATH)
                .body(RecipeRequestFactory.createDefaultRecipeRequest())
                .post()
                .as(RecipeResponse.class);
    }

    public Response deleteRecipe(int recipeId) {
        return ofdReqSpec.getSettingsReqSpec()
                .basePath(OFD_RECIPE_BASE_PATH)
                .param("id", recipeId)
                .delete();
    }

    public ChronologyOfChangesResponse[] getChronologyOfChanges(Map<String, Object> queryParams) {
        return ofdReqSpec.getSettingsReqSpec()
                .basePath(OFD_RECIPE_BASE_PATH)
                .queryParams(queryParams)
                .get("/chronology-of-changes")
                .as(ChronologyOfChangesResponse[].class);
    }

    public RecipeResponse[] getRecipes(Map<String, Object> queryParams) {
        return ofdReqSpec.getSettingsReqSpec()
                .basePath(OFD_RECIPE_BASE_PATH)
                .queryParams(queryParams)
                .get()
                .as(RecipeResponse[].class);
    }

    public RecipeResponse updateRecipe(int recipeId) {
        return ofdReqSpec.getSettingsReqSpec()
                .basePath(OFD_RECIPE_BASE_PATH)
                .queryParam("id", recipeId)
                .body(RecipeRequestFactory.createUpdateRecipeRequest())
                .put()
                .as(RecipeResponse.class);
    }
}