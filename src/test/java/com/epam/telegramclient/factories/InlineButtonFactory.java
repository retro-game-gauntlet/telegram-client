package com.epam.telegramclient.factories;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class InlineButtonFactory {

    public static InlineKeyboardButton defaultButton() {
        return new InlineKeyboardButton();
    }
}