package com.epam.telegramclient;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramBotConfig {

    @Value("${telegram.bot.token}")
    private String telegramBotToken;

    @Bean
    public TelegramBot bot(OnUpdateListener listener) {
        TelegramBot bot = new TelegramBot(telegramBotToken);
        bot.setUpdatesListener(listener);
        return bot;
    }
}