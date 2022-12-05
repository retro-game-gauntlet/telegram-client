package com.epam.telegramclient.factories;

import org.telegram.telegrambots.meta.api.objects.Chat;

public class ChatFactory {

    public static Chat defaultChat() {
        Chat chat = new Chat();
        chat.setId(123L);
        return chat;
    }
}