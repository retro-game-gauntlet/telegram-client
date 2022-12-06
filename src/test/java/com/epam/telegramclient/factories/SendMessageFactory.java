package com.epam.telegramclient.factories;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static com.epam.telegramclient.factories.InlineMarkupFactory.defaultMarkup;

public class SendMessageFactory {

    public static SendMessage defaultPlatformsSendMessage() {
        SendMessage response = new SendMessage("123", "Platforms");
        response.setReplyMarkup(defaultMarkup());
        return response;
    }
}