package com.epam.telegramclient.ui;

import com.epam.telegramclient.business.util.ArraySpliterator;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class Markup {

    private InlineKeyboardButton[] buttons = {};
    private int itemsPerRow = 1;

    public static Markup builder() {
        return new Markup();
    }

    public Markup withButtons(InlineKeyboardButton... buttons) {
        this.buttons = buttons;
        return this;
    }

    public Markup itemsPerRow(int itemsPerRow) {
        this.itemsPerRow = itemsPerRow;
        return this;
    }

    public InlineKeyboardMarkup build() {
        ArraySpliterator<InlineKeyboardButton> arraySpliterator = new ArraySpliterator<>(itemsPerRow);
        List<List<InlineKeyboardButton>> buttonsGrid = arraySpliterator.split(buttons);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(buttonsGrid);
        return markup;
    }
}
