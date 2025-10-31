package com.example.api.evo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {
    SESSION_BLOCKED(-32098, "Сессия заблокирована, необходимо повторно авторизоваться",
            "", ""),
    SESSION_EXPIRED(-32097, "Сессия устарела или заблокирована, необходимо повторно авторизоваться в системе",
            "", ""),
    SOMETHING_WENT_WRONG(-32500, "Что-то пошло не так. Попробуйте повторить запрос позже",
            "", ""), // also permission denied, login error, connection error,
    INVALID_PARAMS(-32600, "", "", ""),
    METHOD_NOT_FOUND(-32601, "Метод не найден", "", ""),
    INCORRECT_PARAMS(-32602, "Неверные параметры", "", ""),
    CARD_NOT_FOUND(-33013, "Карта не найдена/Неверный срок", "Card not found/expired", "");

    final int code;
    final String messageRu;
    final String messageEn;
    final String messageUz;
}
