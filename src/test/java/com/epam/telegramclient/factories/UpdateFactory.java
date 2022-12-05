package com.epam.telegramclient.factories;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.function.Consumer;

import static com.epam.telegramclient.factories.CallbackQueryFactory.*;
import static com.epam.telegramclient.factories.MessageFactory.defaultMessage;

public class UpdateFactory {

    public static Update defaultUpdate() {
        Update update = new Update();
        update.setMessage(defaultMessage());
        update.setCallbackQuery(defaultCallbackQuery());
        return update;
    }

    public static Update defaultUpdate(Consumer<Update> consumer) {
        Update update = defaultUpdate();
        consumer.accept(update);
        return update;
    }
}
