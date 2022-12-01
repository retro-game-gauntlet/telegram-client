package com.epam.telegramclient.business.event.listener;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.event.events.UpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class OnUpdateEventMessageCommandListener implements ApplicationListener<UpdateEvent> {

    private final Map<String, Command<Message>> messageCommands;
    private final Command<Message> notFoundMessageCommand;

    @Override
    public void onApplicationEvent(@NonNull UpdateEvent event) {
        Update update = event.getUpdate();
        if (isMessageCommand(update)) {
            process(event.getBot(), update);
        }
    }

    private boolean isMessageCommand(Update update) {
        return update.hasMessage()
                && update.getMessage().hasText()
                && update.getMessage().getText().startsWith("/");
    }

    private void process(TelegramLongPollingBot bot, Update update) {
        String message = update.getMessage().getText();
        Command<Message> messageCommand = messageCommands.getOrDefault(message, notFoundMessageCommand);
        try {
            messageCommand.process(bot, update.getMessage());
        } catch (TelegramApiException e) {
            log.error("Can't process message command: '{}'", update, e);
        }
    }
}