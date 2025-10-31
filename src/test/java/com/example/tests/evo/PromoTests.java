package com.example.tests.evo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.models.promo.AdsSliderGetResponse;
import com.example.api.evo.services.promo.PromoService;

import static io.qameta.allure.Allure.step;

public class PromoTests extends EvoBaseTest {
    @Autowired
    PromoService promoService;

    @Test
    
    @DisplayName("[ads.slider.get] Получить слайдеры")
    void adsSliderGetTest() {
        step("Получения слайдера", () -> {
            AdsSliderGetResponse response = promoService.adsSliderGet(headers);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult()).isNotEmpty().allSatisfy(n -> {
                    soft.assertThat(n.getId()).as("id").isNotEmpty();
                    soft.assertThat(n.getAnalyticsViewVariable()).as("analyticsViewVariable").isNotEmpty();
                    soft.assertThat(n.getAnalyticsVariable()).as("analyticsVariable").isNotEmpty();
                    soft.assertThatUrl(n.getImage()).as("image").isReachable();
                    soft.assertThatUrl(n.getUrl()).as("url").isReachable();
                });
            });
        });
    }

}
