package com.example.tests.ofd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.ofd.models.settings_ms.dictionary.HazelcastRequest;
import com.example.api.ofd.models.settings_ms.dictionary.HazelcastResponse;
import com.example.api.ofd.models.settings_ms.dictionary.MatchingResponse;
import com.example.api.ofd.services.DictionaryService;

import java.util.*;
import java.util.stream.Collectors;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class DictionaryTests extends OfdApiBaseTest {

    @Autowired
    private DictionaryService dictionaryService;

    @Test
    
    @DisplayName("[v3/dictionary/mxik-status][POST] Проверка ACTIVE MxikCode")
    void getMxikStatusActiveTest() {
        var response = step("Отправить запрос", () ->
                dictionaryService.getStatusByMxikCode(List.of(ACTIVE_MXIK_CODE)));
        step("Проверка ACTIVE статуса", () ->
                assertThat(response[0].getStatus()).isEqualTo("ACTIVE")
        );
    }

    @Test
    
    @DisplayName("[v3/dictionary/mxik-status][POST] Проверка UPDATED MxikCode")
    void getMxikStatusUpdatedTest() {
        var response = step("Отправить запрос", () ->
                dictionaryService.getStatusByMxikCode(List.of(UPDATE_MXIK_CODE)));
        step("Проверка UPDATED статуса", () -> {
            assertThat(response[0].getStatus()).isEqualTo("UPDATED");
            assertThat(response[0].getNewMxikCode()).isEqualTo(NEW_UPDATE_MXIK_CODE);
        });
    }

    @Test
    
    @DisplayName("[v3/dictionary/mxik-status][POST] Проверка DELETED_OR_NOT_FOUND MxikCode")
    void getMxikStatusDeletedOrNotFoundTest() {
        var response = step("Отправить запрос", () -> dictionaryService.getStatusByMxikCode(List.of(DELETE_MXIK_CODE)));
        step("Проверка DELETED_OR_NOT_FOUND статуса", () ->
                assertThat(response[0].getStatus()).isEqualTo("DELETED_OR_NOT_FOUND")
        );
    }

    @Test
    
    @DisplayName("[v3/dictionary/payment-type][GET] Получение списка типов платежей")
    void getPaymentType() {
        var paymentTypes = step("Отправить запрос", () -> dictionaryService.getPaymentType());
        step("Проверка ответа", () -> {
            assertThat(paymentTypes).isNotNull();

            Set<String> expectedNames = dictionaryService.getExpectedPaymentTypeNames();
            Set<String> actualNames = Arrays.stream(paymentTypes)
                    .map(MatchingResponse::getName)
                    .collect(Collectors.toSet());
            assertThat(actualNames).containsExactlyInAnyOrderElementsOf(expectedNames);
        });
    }

    @Test
    
    @DisplayName("[v3/dictionary/package-code][GET] Получение список кодов пакетов")
    void getPackageCode() {
        var params = new HashMap<>(Map.of("mxikCode", ACTIVE_MXIK_CODE));
        var packageCode = step("Отправить запрос", () -> dictionaryService.getPackageCode(params));
        step("Проверка ответа", () -> {
            assertThat(packageCode).isNotNull();
            assertThat(packageCode[0].getMxikCode()).isEqualTo("02710001007000000");
            assertThat(packageCode[0].getPackageType()).isEqualTo("1");
            assertThat(packageCode[0].getNameUz()).isEqualTo("килограмм");
            assertThat(packageCode[0].getCode()).isEqualTo(1288872);
            assertThat(packageCode[0].getMxikCode()).isEqualTo(ACTIVE_MXIK_CODE);
            assertThat(packageCode[0].getPackageType()).isEqualTo("1");
        });
    }

    @Test
    
    @DisplayName("[v3/dictionary/matching][GET] Получение список значении для сравнения")
    void getMatching() {
        var matching = step("Отправить запрос", () -> dictionaryService.getMatchingList());
        step("Проверка ответа", () -> {
            assertThat(matching).isNotNull();
            assertThat(matching[0].getName()).isEqualTo(MATCHING);
            assertThat(matching[0].getDescription()).isEqualTo("Равно");
            assertThat(matching[1].getName()).isEqualTo(NOT_MATCHING);
            assertThat(matching[1].getDescription()).isEqualTo("Не равно");
        });
    }

    @Test
    
    @DisplayName("[v3/dictionary/check/hazelcast][POST] Получение информаций MXIK кодов с помошью API TASNIF")
    void checkHazelcast() {
        HazelcastRequest request = HazelcastRequest.builder().mxikCodes(List.of(SPIC)).build();

        step("Отправить запрос на v3/dictionary/check/hazelcast для получения информаций MXIK кодов", () -> {
            HazelcastResponse response = dictionaryService.checkHazelcast(request);

            step("Проверить ответ", () -> {
                soft.assertThat(response.getSuccess()).isEqualTo(true);
                soft.assertThat(response.getCode()).isEqualTo(1);
                soft.assertThat(response.getErrors()).isNull();
                soft.assertThat(response.getData()).isNotNull();
                soft.assertThat(response.getData().getFirst().getMxikCode()).isEqualTo(SPIC);
                soft.assertThat(response.getData().getFirst().getInfo().getMxikCode()).isEqualTo(SPIC);
                soft.assertThat(response.getData().getFirst().getInfo().getNameUzCyrl()).isEqualTo(NAME_UZ_CYRL);
                soft.assertThat(response.getData().getFirst().getInfo().getNameUzLatin()).isEqualTo(NAME_UZ_LATIN);
                soft.assertThat(response.getData().getFirst().getInfo().getNameRu()).isEqualTo(NAME_RU);
                soft.assertThat(response.getData().getFirst().getInfo().getUsePackage()).isEqualTo(USE_PACKAGE);
                soft.assertThat(response.getData().getFirst().getInfo().getUseLicense()).isEqualTo(USE_LICENSE);
                soft.assertThat(response.getData().getFirst().getInfo().getPackageNames().getFirst()).isNotNull();
                soft.assertThat(response.getData().getFirst().getInfo().getPackageNames().getFirst().getCode()).isEqualTo(HAZELCASR_INFO_CODE);
                soft.assertThat(response.getData().getFirst().getInfo().getPackageNames().getFirst().getMxikCode()).isEqualTo(SPIC);

            });
        });
    }
}
