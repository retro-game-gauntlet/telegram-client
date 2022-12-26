package com.epam.telegramclient.business.command.message;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.domain.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class StartMessageCommand implements Command {

    private final String welcomeMessage;

    @Override
    public boolean isApplicableFor(Request request) {
        return "/start".equalsIgnoreCase(request.command());
    }

    @Override
    public void process(TelegramLongPollingBot bot, Request request) throws TelegramApiException {
        SendMessage response = new SendMessage(request.chatId().toString(), welcomeMessage);
        bot.execute(response);
    }
}