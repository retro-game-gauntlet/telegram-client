package com.epam.telegramclient.business;

import com.epam.methodlog.annotation.InputMethodLog;
import com.epam.telegramclient.business.event.events.UpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
@Slf4j
public class RggBot extends TelegramLongPollingBot {

    private final String botName;
    private final String botToken;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    @InputMethodLog
    public void onUpdateReceived(Update update) {
        eventPublisher.publishEvent(new UpdateEvent(this, update));
    }
}
