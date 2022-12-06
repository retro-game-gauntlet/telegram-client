package com.epam.telegramclient.factories;

import com.epam.telegramclient.ui.Markup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static com.epam.telegramclient.factories.InlineButtonFactory.*;

public class InlineMarkupFactory {

    public static InlineKeyboardMarkup defaultMarkup() {
        return Markup.builder()
                .itemsPerRow(1)
                .withButtons(defaultButton())
                .build();
    }
}