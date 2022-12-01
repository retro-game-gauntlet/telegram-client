package com.epam.telegramclient.business.event.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public class UpdateEvent extends ApplicationEvent {

    private final transient TelegramLongPollingBot bot;
    private final Update update;

    public UpdateEvent(TelegramLongPollingBot bot, Update update) {
        super(bot);
        this.bot = bot;
        this.update = update;
    }
}