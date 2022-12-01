package com.epam.telegramclient.business.command.message;

import com.epam.telegramclient.business.command.Command;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static java.lang.String.format;

@Component
public class NotFoundMessageCommand implements Command<Message> {

    @Override
    public void process(TelegramLongPollingBot bot, Message message) throws TelegramApiException {
        String text = message.getText();
        Long chatId = message.getChatId();
        SendMessage response = new SendMessage(chatId.toString(), format("Command %s not found", text));
        bot.execute(response);
    }
}