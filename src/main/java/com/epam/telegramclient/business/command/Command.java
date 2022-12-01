package com.epam.telegramclient.business.command;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Command<T> {

    void process(TelegramLongPollingBot bot, T update) throws TelegramApiException;
}