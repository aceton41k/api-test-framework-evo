package com.example.tests.avto.references;

import com.example.api.avto.models.references.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import uz.click.click_avto_api.models.references.*;
import com.example.api.avto.services.MyCarsService;
import com.example.api.avto.services.ReferencesService;
import com.example.tests.avto.MyAutoBaseTest;

import java.util.List;
import java.util.Map;

import static io.qameta.allure.Allure.step;
import static com.example.api.avto.constants.TestConfig.INTERNAL_API_KEY;
import static com.example.api.avto.constants.TestConfig.KENIG_CLIENT_ID;

public class ReferencesTests extends MyAutoBaseTest {

    @Autowired
    MyCarsService myCarsService;
    @Autowired
    ReferencesService referencesService;
    String userToken;

    @BeforeAll
    void setup() {
        userToken = myCarsService.getUserToken(INTERNAL_API_KEY, KENIG_CLIENT_ID).getToken();
    }

    @Test
    
    @DisplayName("[v2/faq][GET] Получить FAQ")
    void getFaqTest() {
        step("Запрос на получение информации FAQ", () -> {
            FaqGetResponse getFaqResponse = referencesService.getFaqInfo(userToken);

            step("Проверка, что ответ не пустой", () -> {
                soft.assertThat(getFaqResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(getFaqResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    
    @DisplayName("[v2/map/tags][GET] Получить теги для фильтрации карты")
    @ParameterizedTest
    @CsvSource({
            "1, false",
            "2, false",
            "3, false"
    })
    void getMapTagsTest(int type, Boolean errorStatus) {
        step("Запрос на получение информации о тегах", () -> {
            MapTagsGetResponse mapTagsGetResponse = referencesService.mapTagsInfo(userToken, type);

            step("Проверка ответа", () -> {
                soft.assertThat(mapTagsGetResponse.getError())
                        .as("Пришла ошибка " + errorStatus)
                        .isEqualTo(errorStatus);
            });
        });
    }

    
    @DisplayName("[/v2/map][GET] Получить элементы карты")
    @ParameterizedTest
    @CsvSource({
            "1, 20, false",
            "2, 20, false",
            "3, 20, false"
    })
    void getMapTagsTest(int type, int vehicleId, Boolean errorStatus) {
        step("Запрос на получение информации о тегах для конкретной машины", () -> {

            Map<String, Object> queryParams = Map.of(
                    "vehicle_id", vehicleId,
                    "type", type
            );

            MapResponse mapGetResponse = referencesService.mapInfo(userToken, queryParams);

            step("Проверка ответа", () -> {
                soft.assertThat(mapGetResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(mapGetResponse.getError()).as("Пришла ошибка " + errorStatus).isEqualTo(errorStatus);
            });
        });
    }

    
    @DisplayName("[/v2/map][POST] Установить фильтр для карты по типу")
    @ParameterizedTest
    @CsvSource({
            "1, 20, 41.323384, 69.361012, 1, false",
            "1, 20, 41.323384, 69.361012, 2, false",
            "1, 20, 41.323384, 69.361012, 3, false",
            "1, 20, , , 1, false"
    })
    void getSetTagsOnMapTest(int type, int vehicleId, String lat, String lng, int tags, Boolean errorStatus) {
        step("Запрос на установку фильтров тегов на карте", () -> {
            var params = MapPostRequest.Params.builder()
                    .type(type)
                    .vehicleId(vehicleId)
                    .lat("".equals(lat) ? null : lat)
                    .lng("".equals(lng) ? null : lng)
                    .tags(List.of(tags))
                    .build();
            MapResponse mapResponse = referencesService.setFilterOnMap(userToken, params);
            step("Проверка ответа", () -> {
                soft.assertThat(mapResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(mapResponse.getError()).as("Пришла ошибка " + errorStatus).isEqualTo(errorStatus);
            });
        });
    }

    @Test
    
    @DisplayName("Получить штрафы за нарушение ПДД")
    void getTrafficLinesTest() {
        step("Запрос на получение информации о трафике", () -> {
            TrafficLinesGetResponse getTrafficResponse = referencesService.trafficInfo(userToken);

            step("Проверка, что ответ не пустой", () -> {
                soft.assertThat(getTrafficResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(getTrafficResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }
}
