package com.example.tests.evo;

import com.example.api.evo.models.services.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.ResponseWithOkResult;
import uz.click.evo_api.models.services.*;
import com.example.api.evo.services.service.ServicesService;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ServicesTests extends EvoBaseTest {
    @Autowired
    ServicesService servicesService;

    @Test
    
    @DisplayName("[category.list] Список категорий")
    void categoryListTest() {
        step("Запрос на получение списка категорий [category.list]", () -> {
            CategoryListResponse response = servicesService.getCategoryList(headers);

            step("Проверка ответа что имя категории не пустой", () -> {
                assertThat(response.getResult().getFirst().getName()).isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[service.list] Список сервисов")
    void serviceList() {
        step("Запрос на получение списка сервисов [service.list]", () -> {
            var params = ServiceListRequest.Params.builder()
                    .categoryId(1L)
                    .build();
            ServiceListResponse response = servicesService.getServiceList(params, headers);

            step("Проверка ответа что имя первого сервиса не пустой", () -> {
                assertThat(response.getResult().getFirst().getName()).isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[communal.service.list] Список коммунальных сервисов")
    void communalServiceList() {
        step("Запрос на получение списка коммунальных сервисов [communal.service.list]", () -> {
            ServiceListResponse response = servicesService.getCommunalServiceList(headers);

            step("Проверка ответа что имя первого сервиса не пустой и параметр myhome_permission = true", () -> {
                assertThat(response.getResult().getFirst().getName()).isNotNull();
                assertThat(response.getResult().getFirst().getMyhomePermission())
                        .isTrue();
            });
        });
    }

    @Test
    
    @DisplayName("[service.data] Информация о сервисе")
    void serviceData() {
        step("Запрос на получение информации о сервисе [service.data]", () -> {
            var params = ServiceDataRequest.Params.builder().serviceId(15).build();
            ServiceDataResponse response = servicesService.getServiceData(params, headers);

            step("Проверка ответа что имя сервиса не пустой", () -> {
                assertThat(response.getResult().getName()).isNotEmpty();
            });
        });
    }

    @Test
    
    @DisplayName("[service.meta] Информация о сервисе")
    void serviceMeta() {
        step("Запрос на получение информации о сервисе [service.meta]", () -> {
            ServiceDataRequest.Params params =
                    ServiceDataRequest.Params.builder().serviceId(41878).build();
            ServiceMetaResponse response = servicesService.getServiceMeta(params, headers);

            step("Проверка ответа что кешбэк не пустой", () -> {
                assertThat(response.getResult().getCashbackSettings()).isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[service.search] Поиск сервисов")
    @Disabled("504 ошибку показывает потом поправлю")
    void serviceSearch() {
        step("Поиск сервисов [service.search]", () -> {
            ServiceSearchRequest.Params params = ServiceSearchRequest.Params.builder()
                    .search("авто")
                    .build();
            ServiceSearchResponse response = servicesService.searchServices(params, headers);

            step("Проверка ответа что result не пустой", () -> {
                assertThat(response.getResult().getServices()).isNotNull();
            });
        });
    }

    @Test
    
    @DisplayName("[track.search.keyword] Трекинг слов по поиску")
    void trackSearchKeyWord() {
        step("Запрос на [track.search.keyword]", () -> {
            TrackSearchKeywordRequest.Params params = TrackSearchKeywordRequest.Params.builder()
                    .keyword("suv")
                    .type(1)
                    .isFound(0)
                    .build();
            ResponseWithOkResult response = servicesService.trackSearchKeyword(params, headers);

            step("Проверка ответа что result ok", () -> {
                assertThat(response.getResult()).isEqualTo("ok");
            });
        });
    }
}
