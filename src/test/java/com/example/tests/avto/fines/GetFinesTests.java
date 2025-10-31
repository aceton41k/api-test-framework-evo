package com.example.tests.avto.fines;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.avto.models.fines.FineGetResponse;
import com.example.api.avto.models.fines.FinesListGetResponse;
import com.example.api.avto.services.MyCarsService;
import com.example.tests.avto.MyAutoBaseTest;

import java.util.HashMap;
import java.util.Map;

import static io.qameta.allure.Allure.step;
import static com.example.api.avto.constants.TestConfig.INTERNAL_API_KEY;
import static com.example.api.avto.constants.TestConfig.KENIG_CLIENT_ID;

public class GetFinesTests extends MyAutoBaseTest {

    @Autowired
    MyCarsService myCarsService;
    String userToken;

    @BeforeAll
    void setup() {
        userToken = myCarsService.getUserToken(INTERNAL_API_KEY, KENIG_CLIENT_ID).getToken();
    }

    @Disabled("Флаки тест. Нужно закрепить за тестовой машиной штрафы в тестовой базе")
    
    @DisplayName("[/v2/fines][GET] Штрафы. Получить список штрафов")
    @ParameterizedTest
    @CsvSource(value = {
            "51, true",
            "51, false",
            " , true",
            " , "
    })
    void getFinesListTest(Integer vehicleId, Boolean paidFines) {
        step("Запрос на получение списка штрафов", () -> {
            Map<String, Object> queryParams = new HashMap<>();
            if (vehicleId != null) {
                queryParams.put("vehicle_id", vehicleId);
            }
            if (paidFines != null) {
                queryParams.put("paid", paidFines);
            }

            FinesListGetResponse finesListResponse = myCarsService.getFinesList(userToken, queryParams);

            step("Проверка ответа", () -> {
                soft.assertThat(finesListResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(finesListResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Disabled("Флаки тест. Нужно закрепить за тестовой машиной штрафы в тестовой базе")
    @Test
    
    @DisplayName("[/v2/fines/:id/info][GET] Штрафы. Получить информацию по штрафу")
    void getFineByIdTest() {
        step("Запрос на получение штрафа по id", () -> {
            FinesListGetResponse finesListResponse = myCarsService.getFinesList(userToken, new HashMap<>());

            Integer fineId = finesListResponse.getData().get(0).getId();

            FineGetResponse fineByIdResponse = myCarsService.getFine(userToken, fineId);

            step("Проверка ответа", () -> {
                soft.assertThat(fineByIdResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(fineByIdResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }
}
