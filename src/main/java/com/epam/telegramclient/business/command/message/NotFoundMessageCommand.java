package com.epam.telegramclient.business.command.message;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.domain.Request;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static java.lang.String.format;

@Component
public class NotFoundMessageCommand implements Command {

    @Override
    public void process(TelegramLongPollingBot bot, Request request) throws TelegramApiException {
        SendMessage response = new SendMessage(request.chatId().toString(), format("Command %s not found", request.command()));
        bot.execute(response);
    }
}