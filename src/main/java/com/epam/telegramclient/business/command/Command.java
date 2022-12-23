package com.epam.telegramclient.business.command;

import com.epam.telegramclient.business.domain.Request;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Command {

    boolean isApplicableFor(Request request);

    void process(TelegramLongPollingBot bot, Request request) throws TelegramApiException;
}