package com.epam.telegramclient.business.event.listener;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.event.events.UpdateEvent;
import com.epam.telegramclient.business.requestresolver.RequestResolvers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationListener;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

import static com.epam.telegramclient.factories.DefaultRequest.*;
import static com.epam.telegramclient.factories.UpdateFactory.defaultUpdate;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OnUpdateEventMessageCommandListenerTest {

    private ApplicationListener<UpdateEvent> listener;

    @Mock
    private Command platfromsCommand;
    @Mock
    private Command notFoundMessageCommand;
    @Mock
    private RequestResolvers requestResolvers;
    @Mock
    private TelegramLongPollingBot bot;

    @BeforeEach
    void setUp() {
        Map<String, Command> commands = Map.of("/platforms", platfromsCommand);
        listener = new OnUpdateEventMessageCommandListener(commands, notFoundMessageCommand, requestResolvers);
    }

    @Test
    void shouldExecutePlatformsCommandWhenCommandIsPlatforms() throws TelegramApiException {
        UpdateEvent event = new UpdateEvent(bot, defaultUpdate());
        when(requestResolvers.resolve(defaultUpdate())).thenReturn(defaultRequest());

        listener.onApplicationEvent(event);

        verify(platfromsCommand).process(bot, defaultRequest());
    }

    @Test
    void shouldExecuteNotFoundCommandWhenCommandIsNotFound() throws TelegramApiException {
        UpdateEvent event = new UpdateEvent(bot, defaultUpdate());
        when(requestResolvers.resolve(defaultUpdate())).thenReturn(notFoundRequest());

        listener.onApplicationEvent(event);

        verify(notFoundMessageCommand).process(bot, notFoundRequest());
    }

    @Test
    void shouldNotExecuteCommandWhenMessageIsNotCommand() throws TelegramApiException {
        UpdateEvent event = new UpdateEvent(bot, defaultUpdate());
        when(requestResolvers.resolve(defaultUpdate())).thenReturn(helloRequest());

        listener.onApplicationEvent(event);

        verify(notFoundMessageCommand, never()).process(any(), any());
    }
}