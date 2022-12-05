package com.epam.telegramclient.factories;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import static com.epam.telegramclient.factories.MessageFactory.defaultMessage;

public class CallbackQueryFactory {

    public static CallbackQuery defaultCallbackQuery() {
        CallbackQuery callbackQuery = new CallbackQuery();
        callbackQuery.setMessage(defaultMessage());
        callbackQuery.setData("hi");
        return callbackQuery;
    }
}