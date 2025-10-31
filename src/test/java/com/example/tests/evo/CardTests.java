package com.example.tests.evo;

import com.example.api.evo.models.card.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.api.evo.constants.UserData;
import com.example.api.evo.models.BaseErrorResponse;
import com.example.api.evo.models.ResponseWithOkResult;
import uz.click.evo_api.models.card.*;
import com.example.api.evo.services.DataBaseService;
import com.example.api.evo.services.card.CardBtnManageService;
import com.example.api.evo.services.card.EvoCardService;
import com.example.tests.BaseTest;

import java.util.*;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Disabled("Для проверки нужна карта")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CardTests extends BaseTest {
    @Autowired
    EvoCardService evoCardService;

    @Autowired
    CardBtnManageService cardBtnManageService;
    @Autowired
    DataBaseService dataBaseService;

    UserData.User user;

    Map<String, String> emptyHeader = Collections.emptyMap();
    String randomCardName = RandomStringUtils.randomAlphabetic(5);

    @BeforeAll
    void setUp() {
        user = userData.getUser("NIK");
        String imei = user.getPhoneNumber() + "000";
        deprecatedHeaders = operations.login(user).getEvoHeaders();
        dataBaseService.removeDeviceRestrictions(imei);
    }

    @Test
    
    @DisplayName("[get.balance] Запрос на получение баланса")
    @Disabled("Пока что отключу так как приходить ошибка и нам нужен мок на внешку что бы получить баланс")
    void getBalance() {
        step("Запрос на получение баланса [get.balance]", () -> {
            var params = GetBalanceRequest.Params.builder()
                    .accountId(Arrays.asList(NIK_ACCOUNT_ID_1, NIK_ACCOUNT_ID_3))
                    .build();
            GetBalanceResponse response = evoCardService.getBalance(params, GetBalanceResponse.class, deprecatedHeaders);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Должно быть два баланса в ответе")
                        .hasSize(2)
                        .allSatisfy(r -> {
                            soft.assertThat(r.getAccountId())
                                    .as("Аккаунт ид должен содержать ид")
                                    .isIn(NIK_ACCOUNT_ID_1, NIK_ACCOUNT_ID_3);
                            soft.assertThat(r.getBalance())
                                    .as("Баланс не должен быть негативным")
                                    .isNotNull()
                                    .isNotNegative();
                        });
            });
        });
    }

    @Test
    
    @DisplayName("[get.balance] Запрос на получение баланса без сессии")
    void getBalanceWithOutHeaders() {
        step("Запрос на получение баланса [get.balance]", () -> {
            var params = GetBalanceRequest.Params.builder()
                    .accountId(Arrays.asList(NIK_ACCOUNT_ID_1, NIK_ACCOUNT_ID_3))
                    .build();
            BaseErrorResponse response = evoCardService.getBalance(params, BaseErrorResponse.class, emptyHeader);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SESSION_BLOCKED);
            });
        });
    }

    @Test
    
    @DisplayName("[get.balance] Запрос на получение баланса другого пользователя")
    @Disabled("Нужен мок")
    void getBalanceAnotherUser() {
        step("Запрос на получение баланса [get.balance]", () -> {
            var params = GetBalanceRequest.Params.builder()
                    .accountId(List.of(XAS_ACCOUNT_ID_1))
                    .build();
            GetBalanceResponse response = evoCardService.getBalance(params, GetBalanceResponse.class, deprecatedHeaders);

            step("Проверка ответа", () ->
                    soft.assertThat(response.getResult()).isEmpty()
            );
        });
    }

    @Test
    
    @DisplayName("[get.cards] Получение карты")
    void getCards() {
        step("Запрос на получение карт", () -> {
            GetCardsResponse response = evoCardService.getCards(GetCardsResponse.class, deprecatedHeaders);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Должен быть хотя бы один результат")
                        .isNotEmpty()
                        .allSatisfy(card -> {
                            soft.assertThat(card.getId()).as("id").isGreaterThan(0);
                            soft.assertThat(card.getBankCode()).as("bank_code").isNotEmpty();
                            soft.assertThat(card.getBankName()).as("bank_name").isNotEmpty();
                            soft.assertThat(card.getBankShortName()).as("bank_short_name").isNotEmpty();
                            soft.assertThat(card.getCardName()).as("card_name").isNotEmpty();

                            if ("WALLET".equals(card.getCardType())) {
                                soft.assertThat(card.getCardNumber())
                                        .as("card_number для WALLET должен быть только из цифр, без пробелов")
                                        .matches("^\\d{16}$"); // можно уточнить, если всегда 16
                            } else {
                                soft.assertThat(card.getCardNumber())
                                        .as("card_number должен быть в формате #### ##** **** #### для всех кроме WALLET")
                                        .matches("^\\d{4} \\d{2}\\*\\* \\*\\*\\*\\* \\d{4}$");
                            }

                            switch (card.getCardType()) {
                                case "HUMO", "SMARTV" -> soft.assertThat(card.getCardExpireDate())
                                        .as("card_expire_date должен быть 4 цифры (MMYY) для HUMO и SMARTV")
                                        .matches("^\\d{4}$");
                                case "WALLET" -> soft.assertThat(card.getCardExpireDate())
                                        .as("card_expire_date должен быть null для кошелька")
                                        .isNull();
                                case "ATTO" -> soft.assertThat(card.getCardExpireDate())
                                        .as("card_expire_date должен быть null для ATTO")
                                        .isNotNull();
                                default -> soft.assertThat(card.getCardExpireDate())
                                        .as("card_expire_date должен быть не пустым для других типов")
                                        .isNotEmpty();
                            }

                            soft.assertThat(card.getCardStatus()).as("card_status").isNotNull();
                            soft.assertThat(card.getCardStatusText()).as("card_status_text").isNotNull();

                            if (!"ATTO".equals(card.getCardType()) && !"WALLET".equals(card.getCardType())) {
                                soft.assertThat(card.getCardToken())
                                        .as("card_token должен быть для всех карт, кроме ATTO и WALLET")
                                        .isNotNull();
                            }

                            soft.assertThat(card.getCardType()).as("card_type").isNotNull();
                            soft.assertThat(card.getMonitoringStatus()).as("monitoring_status").isNotNull();
                            soft.assertThat(card.getIsDefault()).as("is_default").isIn(true, false);
                            soft.assertThat(card.getCardNumberHash()).as("card_number_hash").isNotEmpty();

                            if ("ATTO".equals(card.getCardType())) {
                                soft.assertThat(card.getCardholder())
                                        .as("cardholder должен быть null для ATTO")
                                        .isNull();
                                //soft.assertThat(card.getBankColor()).as("bankColor должен быть null для ATTO").isNull();
                            } else {
                                soft.assertThat(card.getCardholder()).as("cardholder").isNotEmpty();
                                //soft.assertThat(card.getBankColor()).as("bankColor").isNotEmpty();
                            }

                            soft.assertThat(card.getCurrencyCode()).as("currency_code").isNotEmpty();
                            soft.assertThat(card.getClick()).as("click").isIn(true, false);
                            soft.assertThat(card.getDetails()).as("details").isIn(true, false);
                            soft.assertThat(card.getSecurecodeAvailable()).as("securecode_available").isIn(true, false);
                            soft.assertThat(card.getSecurecodeStatus()).as("securecode_status").isIn(true, false);

                            step("Проверка картинок", () -> {
                                soft.assertThatUrl(card.getImages().getLogoMiniWhite()).isReachable();
                                soft.assertThatUrl(card.getImages().getLogo()).isReachable();
                                soft.assertThatUrl(card.getImages().getMiniLogo()).isReachable();
                                soft.assertThatUrl(card.getImages().getCardtypeMini()).isReachable();

                                if (!"WALLET".equals(card.getCardType())) {
                                    soft.assertThatUrl(card.getImages().getCardtype()).isReachable();
                                } else {
                                    soft.assertThat(card.getImages().getCardtype())
                                            .as("cardtype должен быть null для WALLET")
                                            .isNull();
                                }
                            });

                            step("Проверка разрешений", () -> {
                                switch (card.getCardType()) {
                                    case "SMARTV" -> soft.assertThat(card.getPermission().getTransfer())
                                            .as("SMARTV должен поддерживать переводы на все типы")
                                            .containsExactlyInAnyOrder("SMARTV", "WALLET", "HUMO", "ATTO");
                                    case "HUMO" -> soft.assertThat(card.getPermission().getTransfer())
                                            .as("HUMO должен поддерживать переводы, кроме ATTO")
                                            .containsExactlyInAnyOrder("SMARTV", "WALLET", "HUMO");
                                    case "WALLET" -> soft.assertThat(card.getPermission().getTransfer())
                                            .as("WALLET может переводить только SMARTV и WALLET")
                                            .containsExactlyInAnyOrder("SMARTV", "WALLET");
                                    case "ATTO", "AGRVISASUM", "AGRVISAUSD" ->
                                            soft.assertThat(card.getPermission().getTransfer())
                                                    .as(card.getCardType() + " не должен поддерживать переводы")
                                                    .isEmpty();
                                    default -> soft.assertThat(card.getPermission().getTransfer())
                                            .as("Для неизвестного типа карты transfer должен быть пустым")
                                            .isEmpty();
                                }
                                soft.assertThat(card.getPermission().getPayment())
                                        .as("permission.payment должен быть 0 или 1")
                                        .isIn(0, 1);
                                soft.assertThat(card.getPermission().getTransfer())
                                        .as("permission.transfer должен быть не null (может быть пустой)")
                                        .isNotNull();
                                soft.assertThat(card.getPermission().getClickpass())
                                        .as("permission.clickpass должен быть 0 или 1")
                                        .isIn(0, 1);
                                soft.assertThat(card.getPermission().getBlockable())
                                        .as("permission.blockable должен быть true или false")
                                        .isIn(true, false);
                                soft.assertThat(card.getPermission().getActivation())
                                        .as("permission.activation должен быть true или false")
                                        .isIn(true, false);
                                soft.assertThat(card.getPermission().getCopyNumber())
                                        .as("permission.copy_number должен быть true или false")
                                        .isIn(true, false);
                                soft.assertThat(card.getPermission().getHistory())
                                        .as("permission.history должен быть true или false")
                                        .isIn(true, false);
                                soft.assertThat(card.getPermission().getSetAsDefault())
                                        .as("permission.set_as_default должен быть true или false")
                                        .isIn(true, false);
                                soft.assertThat(card.getPermission().getBalance())
                                        .as("permission.balance должен быть true или false")
                                        .isIn(true, false);
                                soft.assertThat(card.getPermission().getTokenization())
                                        .as("permission.tokenization должен быть true или false")
                                        .isIn(true, false);
                                soft.assertThat(card.getPermission().getRemovable())
                                        .as("permission.removable должен быть 0 или 1")
                                        .isIn(0, 1);
                            });

                            step("Проверка лимитов", () -> {
                                soft.assertThat(card.getTransferLimits().getSendMinLimit())
                                        .as("Минимальная сумма перевода (send_min_limit) должна быть больше 0")
                                        .isGreaterThan(0);
                                soft.assertThat(card.getTransferLimits().getSendMaxLimit())
                                        .as("Максимальная сумма перевода (send_max_limit) должна быть положительной")
                                        .isGreaterThan(0);
                                soft.assertThat(card.getTransferLimits().getReceiveMinLimit())
                                        .as("Минимальная сумма приёма перевода (receive_min_limit) должна быть больше 0")
                                        .isGreaterThan(0);
                                soft.assertThat(card.getTransferLimits().getReceiveMaxLimit())
                                        .as("Максимальная сумма приёма перевода (receive_max_limit) может быть 0, если не ограничено")
                                        .isGreaterThanOrEqualTo(0);
                                soft.assertThat(card.getTransferLimits().getPercent())
                                        .as("Процент комиссии (percent) должен быть от 0 до 100")
                                        .isBetween(0, 100);
                            });

                            step("Проверка настроек", () -> {
                                soft.assertThat(card.getOptions().getIsMasked())
                                        .as("options.is_masked должен быть true или false")
                                        .isIn(true, false);
                                if (card.getCardType().startsWith("AGR")) { // для VISA-карт
                                    soft.assertThat(card.getOptions().getButtonSet())
                                            .as("button_set должен быть VISA для AGRVISASUM и AGRVISAUSD")
                                            .isEqualTo("VISA");
                                    soft.assertThat(card.getOptions().getDisplayType())
                                            .as("display_type должен быть VISA для AGRVISASUM и AGRVISAUSD")
                                            .isEqualTo("VISA");
                                } else {
                                    soft.assertThat(card.getOptions().getButtonSet())
                                            .as("button_set должен совпадать с типом карты")
                                            .isEqualTo(card.getCardType());
                                    soft.assertThat(card.getOptions().getDisplayType())
                                            .as("display_type должен совпадать с типом карты")
                                            .isEqualTo(card.getCardType());
                                }
                            });

                            step("Проверка мониторинга", () -> {
                                soft.assertThat(card.getMonitoring().getAvailable())
                                        .as("monitoring.available должен быть true или false")
                                        .isIn(true, false);

                                soft.assertThat(card.getMonitoring().getToggle())
                                        .as("monitoring.toggle должен быть true или false")
                                        .isIn(true, false);

                                soft.assertThat(card.getMonitoring().getStatus())
                                        .as("monitoring.status должен быть true или false")
                                        .isIn(true, false);

                                if (card.getCardType().equals("SMARTV")) {
                                    soft.assertThat(card.getMonitoring().getPrice())
                                            .as("monitoring.price должен быть > 0, если мониторинг доступен")
                                            .isGreaterThan(0);

                                    soft.assertThat(card.getMonitoring().getCurrency())
                                            .as("monitoring.currency должен быть UZS, если monitoring включён")
                                            .isEqualTo("UZS");
                                } else {
                                    soft.assertThat(card.getMonitoring().getPrice())
                                            .as("monitoring.price должен быть null или 0, если monitoring выключен")
                                            .isIn(0, null);

                                    soft.assertThat(card.getMonitoring().getCurrency())
                                            .as("monitoring.currency должен быть null, если monitoring выключен")
                                            .isNull();
                                }
                            });
                        });
            });
        });
    }

    @Test
    
    @DisplayName("[get.cards] Получение карты без сессии")
    void getCardsWithOutSession() {
        step("Запрос на получение карт", () -> {
            BaseErrorResponse response = evoCardService.getCards(BaseErrorResponse.class, emptyHeader);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SESSION_EXPIRED);
            });
        });
    }


    @Test
    
    @DisplayName("[card.btns.manage] Проверка кнопок для карт")
    void cardBtnManage() {
        step("[card.btns.manage] Проверка кнопок для карт", () -> {
            CardBtnManageResponse response = cardBtnManageService.getCardsManage();

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult().getUzcardHumo())
                        .as("UzcardHumo должен быть true или false")
                        .isIn(true, false);
                soft.assertThat(response.getResult().getOrderCard())
                        .as("OrderCard должен быть true или false")
                        .isIn(true, false);
                soft.assertThat(response.getResult().getAtto())
                        .as("Atto должен быть true или false")
                        .isIn(true, false);
                soft.assertThat(response.getResult().getVisaCapital())
                        .as("VisaCapital должен быть true или false")
                        .isIn(true, false);
                soft.assertThat(response.getResult().getVisaKapital())
                        .as("VisaKapital должен быть true или false")
                        .isIn(true, false);
            });
        });
    }


    @Test
    
    @DisplayName("[card.change.name] Сменить название карты")
    void changeCardName() {
        step("[card.change.name] Сменить название карты", () -> {
            var params = CardChangeNameRequest.Params.builder()
                    .accountId(NIK_ACCOUNT_ID_1)
                    .cardName(randomCardName)
                    .build();
            ResponseWithOkResult response = evoCardService.changeCardName(params, ResponseWithOkResult.class,
                    deprecatedHeaders);

            step("Проверка ответа", () -> {
                assertThat(response.getResult()).isEqualTo("ok");
            });
        });
        step("Получаем список карт и ищем по названию карту", () -> {
            Optional<GetCardsResponse.Result> card = evoCardService.getCards(GetCardsResponse.class, deprecatedHeaders)
                    .getResult()
                    .stream()
                    .filter(item -> item.getCardName().equals(randomCardName))
                    .findFirst();
            card.map(GetCardsResponse.Result::getCardName).orElseThrow();
        });
    }

    @Test
    
    @DisplayName("[card.change.name] Сменить название карты другого пользователя")
    void changeAnotherUserCardName() {
        step("[card.change.name] Сменить название карты", () -> {
            var params = CardChangeNameRequest.Params.builder()
                    .accountId(XAS_ACCOUNT_ID_1)
                    .cardName(randomCardName)
                    .build();
            BaseErrorResponse response = evoCardService.changeCardName(params, BaseErrorResponse.class,
                    deprecatedHeaders);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(CARD_NOT_FOUND);
            });
        });
    }

    @Test
    
    @DisplayName("[card.change.name] Сменить название карты без сессии")
    void changeCardNameWithoutSession() {
        step("[card.change.name] Сменить название карты", () -> {
            var params = CardChangeNameRequest.Params.builder()
                    .accountId(XAS_ACCOUNT_ID_1)
                    .cardName(randomCardName)
                    .build();
            BaseErrorResponse response = evoCardService.changeCardName(params, BaseErrorResponse.class, emptyHeader);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SESSION_BLOCKED);
            });
        });
    }

    @Test
    
    @DisplayName("[card.suggestions] Предложение по картам")
    @Disabled("Отключу так как приходить пустой ответ на тестовом")
    void cardSuggestions() {
        step("[card.suggestions] Предложение по картам", () -> {
            CardsSuggestionsResponse response = cardBtnManageService.getCardSuggestions(CardsSuggestionsResponse.class,
                    deprecatedHeaders);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult())
                        .as("Должен быть хотя бы один результат")
                        .isNotEmpty()
                        .allSatisfy(card -> {
                            soft.assertThat(card.getCardNumber())
                                    .as("card_number должен содержать только цифры и быть длиной 16")
                                    .matches("^\\d{16}$");
                            soft.assertThat(card.getCardNumberSpace())
                                    .as("card_number_space должен быть в формате #### ##** **** ####")
                                    .matches("^\\d{4} \\d{2}\\*\\* \\*\\*\\*\\* \\d{4}$");
                            soft.assertThat(card.getExpiry())
                                    .as("expiry должен быть в формате MMYY (4 цифры)")
                                    .matches("^\\d{4}$");
                            soft.assertThat(card.getBankName())
                                    .as("bank_name не должен быть пустым")
                                    .isNotBlank();
                            soft.assertThat(card.getCardType())
                                    .as("card_type должен быть HUMO")
                                    .isNotEmpty();
                            soft.assertThatUrl(card.getLogo())
                                    .as("logo должен быть валидным URL")
                                    .isReachable();
                            soft.assertThatUrl(card.getLogoUrl())
                                    .as("logo_url должен быть валидным URL")
                                    .isReachable();
                            soft.assertThatUrl(card.getLogoCardtype())
                                    .as("logo_cardtype должен быть валидным URL")
                                    .isReachable();
                        });

            });
        });
    }

    @Test
    
    @DisplayName("[card.suggestions] Предложение по картам без сессии")
    void cardSuggestionsWithoutSession() {
        step("[card.suggestions] Предложение по картам", () -> {
            BaseErrorResponse response = cardBtnManageService.getCardSuggestions(BaseErrorResponse.class, emptyHeader);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SESSION_BLOCKED);
            });
        });
    }

    @Test
    
    @DisplayName("[humopay.details] Детали humoPay")
    @Disabled("отключу вроде как нужен мок с внешки")
    void humoPayDetailsTest() {
        step("Получения детали humoPay", () -> {
            var params = GetBalanceRequest.Params.builder()
                    .accountId(Collections.singletonList(NIK_ACCOUNT_ID_3))
                    .build();
            HumoPayDetailsResponse response = evoCardService.getHumoPayDetails(params,
                    HumoPayDetailsResponse.class,
                    deprecatedHeaders);

            step("Проверка ответа", () -> {
                assertThat(response.getResult().getPan()).isEqualTo("986035VSLQIR1003");
                assertThat(response.getResult().getClientId()).isEqualTo("3500347100");
            });
        });
    }

    @Test
    
    @DisplayName("[humopay.details] Детали humoPay другого пользователя")
    @Disabled("отключу вроде как нужен мок с внешки")
    void humoPayDetailsOfAnotherUser() {
        step("Получения детали humoPay", () -> {
            var params = GetBalanceRequest.Params.builder()
                    .accountId(Collections.singletonList(XAS_ACCOUNT_ID_1))
                    .build();
            BaseErrorResponse response = evoCardService.getHumoPayDetails(params, BaseErrorResponse.class,
                    deprecatedHeaders);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(CARD_NOT_FOUND);
            });
        });
    }

    @Test
    
    @DisplayName("[humopay.details] Детали humoPay без сессии")
    void humoPayDetailsWithoutSession() {
        step("Получения детали humoPay", () -> {
            var params = GetBalanceRequest.Params.builder()
                    .accountId(Collections.singletonList(XAS_ACCOUNT_ID_1))
                    .build();
            BaseErrorResponse response = evoCardService.getHumoPayDetails(params, BaseErrorResponse.class, emptyHeader);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SESSION_EXPIRED);
            });
        });
    }

    @Test
    
    @DisplayName("[card.get.number] Получить номер карты по аккаунт ид")
    void getCardNumber() {
        step("Получения детали humoPay", () -> {
            var params = GetCardNumberRequest.Params.builder()
                    .accountId(NIK_ACCOUNT_ID_1)
                    .build();
            GetCardNumberResponse response = evoCardService.getCardNumber(params, GetCardNumberResponse.class,
                    deprecatedHeaders);

            step("Проверка ответа", () -> {
                soft.assertThat(response.getResult().getCardNumber())
                        .isEqualTo(String.valueOf(NIK_ACCOUNT_ID_1_NUMBER));
            });
        });
    }

    @Test
    
    @DisplayName("[card.get.number] Получить номер карты по аккаунт ид другого пользователя")
    void getCardNumberOfAnotherUser() {
        step("Получения детали humoPay", () -> {
            var params = GetCardNumberRequest.Params.builder()
                    .accountId(XAS_ACCOUNT_ID_1)
                    .build();
            BaseErrorResponse response = evoCardService.getCardNumber(params, BaseErrorResponse.class,
                    deprecatedHeaders);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(CARD_NOT_FOUND);
            });
        });
    }

    @Test
    
    @DisplayName("[card.get.number] Получить номер карты по аккаунт ид без сессии")
    void getCardNumberWithoutSession() {
        step("Получения детали humoPay", () -> {
            var params = GetCardNumberRequest.Params.builder()
                    .accountId(XAS_ACCOUNT_ID_1)
                    .build();
            BaseErrorResponse response = evoCardService.getCardNumber(params, BaseErrorResponse.class, emptyHeader);

            step("Проверка ответа", () -> {
                soft.assertThatError(response).hasError(SESSION_BLOCKED);
            });
        });
    }
}
