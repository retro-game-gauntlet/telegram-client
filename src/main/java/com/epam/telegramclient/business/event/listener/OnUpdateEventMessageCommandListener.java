package com.epam.telegramclient.business.event.listener;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.command.resolver.CommandResolver;
import com.epam.telegramclient.business.domain.Request;
import com.epam.telegramclient.business.event.events.UpdateEvent;
import com.epam.telegramclient.business.requestresolver.RequestResolvers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
@RequiredArgsConstructor
public class OnUpdateEventMessageCommandListener implements ApplicationListener<UpdateEvent> {

    private final RequestResolvers requestResolvers;
    private final CommandResolver commandResolver;

    @Override
    public void onApplicationEvent(@NonNull UpdateEvent event) {
        Update update = event.getUpdate();
        Request request = requestResolvers.resolve(update);
        if (request.command().startsWith("/")) {
            process(event.getBot(), request);
        }
    }

    private void process(TelegramLongPollingBot bot, Request request) {
        Command messageCommand = commandResolver.resolve(request);
        try {
            messageCommand.process(bot, request);
        } catch (TelegramApiException e) {
            log.error("Can't process message for request: {}", request, e);
        }
    }
}