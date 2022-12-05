package com.epam.telegramclient.factories;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.function.Consumer;

import static com.epam.telegramclient.factories.ChatFactory.defaultChat;

public class MessageFactory {

    public static Message defaultMessage() {
        Message message = new Message();
        message.setText("hello");
        message.setChat(defaultChat());
        return message;
    }

    public static Message defaultMessage(Consumer<Message> consumer) {
        Message message = defaultMessage();
        consumer.accept(message);
        return message;
    }
}