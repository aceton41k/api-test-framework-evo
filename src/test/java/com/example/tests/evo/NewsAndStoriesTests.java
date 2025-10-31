package com.example.tests.evo;

import com.example.api.evo.models.news.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.BaseErrorResponse;
import uz.click.evo_api.models.news.*;
import com.example.api.evo.services.news.NewsService;
import com.example.api.evo.services.stories.StoriesService;
import com.example.jupiter.annotations.SkipAuthSetup;

import java.util.Map;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.api.evo.constants.ErrorCodes.SOMETHING_WENT_WRONG;
import static com.example.api.evo.constants.ServiceData.NEWS_ID;
import static com.example.api.evo.constants.ServiceData.NEWS_ID_1;

public class NewsAndStoriesTests extends EvoBaseTest {
    @Autowired
    NewsService newsService;
    @Autowired
    StoriesService storiesService;
    Integer viewsCount;

    static Stream<Arguments> headersProvider() {
        return Stream.of(
                Arguments.of(Map.of("device_id",
                        "9989771117147A927EF0D1C9D8244638B68E10C124",
                        "session_key",
                        "328356906662464e7cee17c9b13edb108220b000701245")),
                Arguments.of(Map.of())
        );
    }

    @Test
    
    @DisplayName("[stories.list] Получение списка сторисов")
    @Disabled("Получаем ошибку не понятно из-за чего")
    void getStoriesListTest() {
        step("Запрос на получение списка сторисов", () -> {
            var params = NewsListRequest.Params.builder()
                    .build();
            NewsListResponse response = storiesService.getStoriesList(params, NewsListResponse.class,
                    headers);

            step("Проверяем каждую новость на валидность", () -> {
                soft.assertThat(response.getResult())
                        .isNotEmpty()
                        .as("Каждая новость должна соответствовать требованиям")
                        .allSatisfy(news -> {
                            assertThat(news.getNewsId())
                                    .as("ID должен быть положительным")
                                    .isGreaterThan(0);
                            assertThat(news.getNewsTitle())
                                    .as("Заголовок не должен быть пустым")
                                    .isNotBlank();
                            assertThat(news.getNewsContent())
                                    .as("Контент должен быть непустым")
                                    .isNotNull();
                            assertThat(news.getNewsImage())
                                    .as("news_image должен начинаться с https://")
                                    .startsWith("https://");
                            assertThat(news.getNewsThumbnail())
                                    .as("news_thumbnail должен начинаться с https://")
                                    .startsWith("https://");
                            assertThat(news.getAction())
                                    .as("Action не должен быть непустым")
                                    .isNotEmpty();
                            assertThat(news.getDatetime())
                                    .as("DateTime не должен быть непустым")
                                    .isNotNull();
                            assertThat(news.getViewsCount())
                                    .as("Количество просмотров должно быть >= 0")
                                    .isGreaterThanOrEqualTo(0);
                            assertThat(news.getLikesCount())
                                    .as("Количество лайков должно быть >= 0")
                                    .isGreaterThanOrEqualTo(0);
                            assertThat(news.getLanguage())
                                    .as("Язык должен быть 'RU' 'EN' 'UZ'")
                                    .isIn("RU", "EN", "UZ");
                            assertThat(news.getType())
                                    .as("Тип должен быть 'stories'")
                                    .isEqualTo("stories");
                            if (news.getUrl() != null && !news.getUrl().isBlank()) {
                                soft.assertThat(news.getUrl())
                                        .as("URL должен начинаться с https://")
                                        .startsWith("https://");
                                soft.assertThat(news.getUrlText())
                                        .as("Если задан URL, то url_text не должен быть пустым")
                                        .isNotBlank();
                            }
                        });
            });
        });
    }

    @ParameterizedTest
    @MethodSource("headersProvider")
    
    @DisplayName("[stories.list] Получение списка сторисов без сессии")
    @SkipAuthSetup
    void getStoriesListWithoutSessionTest(Map<String, String> headers) {
        step("Запрос на получение списка сторисов", () -> {
            var params = NewsListRequest.Params.builder()
                    .build();
            BaseErrorResponse response = storiesService.getStoriesList(params, BaseErrorResponse.class, headers);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SOMETHING_WENT_WRONG);
            });
        });
    }

    @Test
    
    @DisplayName("[stories.get] Получение информации о сторисе")
    void getStoryByIdTest() {
        step("Запрос на получение сториса", () -> {
            var params =
                    NewsGetRequest.Params.builder().newsId(NEWS_ID).build();
            NewsGetResponse response = storiesService.getStoryById(params, headers);

            step("Проверяем базовые поля новости", () -> {
                soft.assertThat(response.getResult().getNewsId())
                        .as("ID новости должен быть положительным")
                        .isGreaterThan(0);
                soft.assertThat(response.getResult().getNewsTitle())
                        .as("Заголовок не должен быть пустым")
                        .isNotBlank();
                soft.assertThat(response.getResult().getNewsContent())
                        .as("Контент не должен быть пустым")
                        .isNotBlank();
                soft.assertThat(response.getResult().getNewsImage())
                        .as("news_image должен начинаться с https://")
                        .startsWith("https://");
                soft.assertThat(response.getResult().getNewsThumbnail())
                        .as("news_thumbnail должен начинаться с https://")
                        .startsWith("https://");
                soft.assertThat(response.getResult().getUrl())
                        .as("url должен начинаться с https://")
                        .startsWith("https://");
                soft.assertThat(response.getResult().getViewsCount())
                        .as("Просмотры должны быть >= 0")
                        .isGreaterThanOrEqualTo(0);
                soft.assertThat(response.getResult().getLikesCount())
                        .as("Лайки должны быть >= 0")
                        .isGreaterThanOrEqualTo(0);
                soft.assertThat(response.getResult().getType())
                        .as("Тип должен быть 'news' или 'stories'")
                        .isIn("news", "stories");
                soft.assertThat(response.getResult().getStyle())
                        .as("Style должен быть 'default'")
                        .isEqualTo("default");
            });
        });
    }

    @ParameterizedTest
    @MethodSource("headersProvider")
    
    @DisplayName("[stories.get] Получение информации о сторисе без сессии")
    @SkipAuthSetup
    void getStoryByIdWithoutSessionTest(Map<String, String> headers) {
        step("Запрос на получение сториса", () -> {
            var params =
                    NewsGetRequest.Params.builder().newsId(NEWS_ID).build();
            NewsGetResponse response = storiesService.getStoryById(params, headers);

            step("Проверяем базовые поля новости", () -> {
                soft.assertThat(response.getResult().getNewsId())
                        .as("ID новости должен быть положительным")
                        .isGreaterThan(0);
                soft.assertThat(response.getResult().getNewsTitle())
                        .as("Заголовок не должен быть пустым")
                        .isNotBlank();
                soft.assertThat(response.getResult().getNewsContent())
                        .as("Контент не должен быть пустым")
                        .isNotBlank();
                soft.assertThat(response.getResult().getNewsImage())
                        .as("news_image должен начинаться с https://")
                        .startsWith("https://");
                soft.assertThat(response.getResult().getNewsThumbnail())
                        .as("news_thumbnail должен начинаться с https://")
                        .startsWith("https://");
                soft.assertThat(response.getResult().getUrl())
                        .as("url должен начинаться с https://")
                        .startsWith("https://");
                soft.assertThat(response.getResult().getViewsCount())
                        .as("Просмотры должны быть >= 0")
                        .isGreaterThanOrEqualTo(0);
                soft.assertThat(response.getResult().getLikesCount())
                        .as("Лайки должны быть >= 0")
                        .isGreaterThanOrEqualTo(0);
                soft.assertThat(response.getResult().getType())
                        .as("Тип должен быть 'news' или 'stories'")
                        .isIn("news", "stories");
                soft.assertThat(response.getResult().getStyle())
                        .as("Style должен быть 'default'")
                        .isIn("default", "promo-image");
            });
        });
    }
    
    @Test
    @DisplayName("[news.view] Просмотр новости")
    void viewNewsByIdTest() {
        step("Запрос на получение новости", () -> {
            var params =
                    NewsGetRequest.Params.builder().newsId(NEWS_ID).build();
            NewsGetResponse response = storiesService.getStoryById(params, headers);
            viewsCount = response.getResult().getViewsCount();
        });
        step("Запрос на просмотр новости", () -> {
            var params = NewsGetRequest.Params.builder().newsId(NEWS_ID).build();
            NewsViewResponse response = newsService.viewNewsById(params, headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult().getNewsId()).isEqualTo(NEWS_ID);
                soft.assertThat(response.getResult().getViewsCount()).isGreaterThan(viewsCount);
            });
        });

        step("Проверка параметра news_view_count для запроса news.get", () -> {
            var params =
                    NewsGetRequest.Params.builder().newsId(NEWS_ID).build();
            NewsGetResponse response = storiesService.getStoryById(params, headers);
            soft.assertThat(response.getResult().getViewsCount()).isGreaterThan(viewsCount);
        });
    }

    @Test
    
    @DisplayName("[news.like] Лайкать новость")
    void likeNewsByIdTest() {
        step("Запрос на news.like", () -> {
            var params = NewsGetRequest.Params.builder().newsId(NEWS_ID).build();
            NewsLikeResponse unlikeResponse = newsService.unlikeNewsById(params,
                    NewsLikeResponse.class,
                    headers);
            NewsLikeResponse response = newsService.likeNewsById(params,
                    NewsLikeResponse.class,
                    headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult().getNewsId()).isEqualTo(NEWS_ID);
                soft.assertThat(response.getResult().getLikesCount()).isGreaterThan(unlikeResponse.getResult()
                        .getLikesCount());
            });

            step("Снова запрос на news.like", () -> {
                NewsLikeResponse secResponse = newsService.likeNewsById(params,
                        NewsLikeResponse.class,
                        headers);

                step("Проверка ответа что лайк не добавился", () -> {
                    soft.assertThat(secResponse.getResult().getLikesCount())
                            .isEqualTo(response.getResult().getLikesCount());
                });
            });
        });
    }

    @ParameterizedTest
    @MethodSource("headersProvider")
    
    @DisplayName("[news.like] Лайкать новость без сессии")
    @SkipAuthSetup
    void likeNewsByIdWithoutSessionTest(Map<String, String> headers) {
        step("Запрос на news.like", () -> {
            var params = NewsGetRequest.Params.builder().newsId(NEWS_ID).build();
            BaseErrorResponse response = newsService.likeNewsById(params, BaseErrorResponse.class, headers);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SOMETHING_WENT_WRONG);
            });
        });
    }

    @Test
    
    @DisplayName("[news.unlike] Отмена лайка на новость")
    void unlikeNewsByIdTest() {
        step("Запрос на unlike.news", () -> {
            var params = NewsGetRequest.Params.builder().newsId(NEWS_ID_1).build();
            NewsLikeResponse likeResponse = newsService.likeNewsById(params,
                    NewsLikeResponse.class,
                    headers);
            NewsLikeResponse response = newsService.unlikeNewsById(params,
                    NewsLikeResponse.class,
                    headers);

            step("Проверка ответа", () -> {
                assertThat(response.getResult().getNewsId()).isEqualTo(NEWS_ID_1);
                assertThat(response.getResult().getLikesCount()).isLessThan(likeResponse.getResult().getLikesCount());
            });

            step("Снова запрос на news.unlike", () -> {
                NewsLikeResponse secResponse = newsService.unlikeNewsById(params,
                        NewsLikeResponse.class,
                        headers);

                step("Проверка ответа что лайк не добавился", () -> {
                    soft.assertThat(secResponse.getResult().getLikesCount())
                            .isEqualTo(response.getResult().getLikesCount());
                });
            });
        });
    }

    @ParameterizedTest
    @MethodSource("headersProvider")
    
    @DisplayName("[news.unlike] Отмена лайка на новость без сессии")
    @SkipAuthSetup
    void unlikeNewsByIdWithoutSessionTest(Map<String, String> headers) {
        step("Запрос на unlike.news", () -> {
            var params = NewsGetRequest.Params.builder().newsId(NEWS_ID_1).build();
            BaseErrorResponse response = newsService.unlikeNewsById(params, BaseErrorResponse.class, headers);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SOMETHING_WENT_WRONG);
            });
        });
    }
}
