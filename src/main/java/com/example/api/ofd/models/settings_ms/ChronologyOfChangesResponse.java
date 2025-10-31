package com.example.api.ofd.models.settings_ms;

import lombok.Getter;

import java.util.List;

@Getter
public class ChronologyOfChangesResponse {
    private Integer historyId;
    private Integer recipeId;
    private Integer serviceId;
    private String executedOperation;
    private String operationExecutedAt;
    private String operationExecutedBy;
    private RecipeResponse dataAfterExecutedOperation;

    @Getter
    public static class ChronologyList {
        private List<ChronologyOfChangesResponse> chronologies;
    }
}
