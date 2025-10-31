package com.example.tests.avto.notifications;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.avto.BaseModelResponse;
import com.example.api.avto.models.notifications.HideWidgetPostRequest;
import com.example.api.avto.models.notifications.MetadataDeleteResponse;
import com.example.api.avto.models.notifications.MetadataPostRequest;
import com.example.api.avto.models.notifications.NotificationsResponse;
import com.example.api.avto.services.MyCarsService;
import com.example.tests.avto.MyAutoBaseTest;

import static io.qameta.allure.Allure.step;
import static com.example.api.avto.constants.TestConfig.INTERNAL_API_KEY;
import static com.example.api.avto.constants.TestConfig.KENIG_CLIENT_ID;

public class NotificationsTest extends MyAutoBaseTest {

    @Autowired
    MyCarsService myCarsService;
    String userToken;

    @BeforeAll
    void setup() {
        userToken = myCarsService.getUserToken(INTERNAL_API_KEY, KENIG_CLIENT_ID).getToken();
    }

    @Test
    
    @DisplayName("[/v2/notifications][GET] Получение инфо об уведомлениях")
    void getNotificationInfoTest() {
        step("Запрос на получение информации об уведомлениях юзера ", () -> {
            NotificationsResponse notifResponse = myCarsService.getNotif(userToken);

            step("Проверка ответа", () -> {
                soft.assertThat(notifResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(notifResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("[/v2/notifications/read][POST] Отметить push-уведомление как прочитанное")
    void markAllNotificationsATest() {
        step("Запрос на отметку всех полученных уведомлений", () -> {
            NotificationsResponse notifResponse = myCarsService.markAllNotif(userToken);

            step("Проверка ответа", () -> {
                soft.assertThat(notifResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(notifResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("[/v2/reminders/hide][POST] Скрыть виджет напоминаний")
    void setWidgetHideTest() {
        step("Запрос на скрытие виджета", () -> {

            var params = HideWidgetPostRequest.Params.builder().widgetKey("osago_615105").build();

            BaseModelResponse notifResponse = myCarsService.setWidgetHide(userToken, params);

            step("Проверка ответа", () -> {
                soft.assertThat(notifResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(notifResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("/v2/meta][POST] Добавить или обновить метаданные пользователя")
    void setMetaDataTest() {
        step("Запрос на обновление мета данных юзера", () -> {

            var params = MetadataPostRequest.Params.builder()
                    .key("test")
                    .value("test")
                    .build();
            BaseModelResponse notifResponse = myCarsService.setMetadata(userToken, params);

            step("Проверка ответа", () -> {
                soft.assertThat(notifResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(notifResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });
        });
    }

    @Test
    
    @DisplayName("")
    void deleteMetaDataTest() {
        step("Удаление мета данных", () -> {

            MetadataDeleteResponse delMetadataResponse = myCarsService.deleteMetadata(userToken, "test");

            step("Проверка ответа", () -> {
                soft.assertThat(delMetadataResponse.getData()).as("Объект не должен быть пустым").isNotNull();
                soft.assertThat(delMetadataResponse.getError()).as("Пришла ошибка").isEqualTo(false);
            });

            var params = MetadataPostRequest.Params.builder()
                    .key("test")
                    .value("test")
                    .build();

            myCarsService.setMetadata(userToken, params);

        });
    }
}
