package com.epam.telegramclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {

    @Value("${telegram.bot.name}")
    private String botName;
    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.bot.welcome-message}")
    private String welcomeMessage;

    @Bean
    public TelegramBotsApi bot(LongPollingBot onUpdateListener) throws TelegramApiException {
        TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);
        bot.registerBot(onUpdateListener);
        return bot;
    }

    @Bean
    public String botName() {
        return botName;
    }

    @Bean
    public String botToken() {
        return botToken;
    }
    @Bean
    public String welcomeMessage() {
        return welcomeMessage;
    }
}