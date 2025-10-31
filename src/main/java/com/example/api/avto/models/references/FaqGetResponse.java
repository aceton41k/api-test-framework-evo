package com.example.api.avto.models.references;

import lombok.Getter;
import com.example.api.avto.BaseModelResponse;

import java.util.List;

@Getter
public class FaqGetResponse extends BaseModelResponse<List<FaqGetResponse.Category>> {

    @Getter
    public static class Category {
        private Integer id;
        private String title;
        private String description;
        private List<Question> questions;

        @Getter
        public static class Question {
            private Integer priority;
            private String question;
            private String answer;
        }
    }
}
