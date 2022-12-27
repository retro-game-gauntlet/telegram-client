package com.epam.telegramclient.business.event.listener;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.command.resolver.CommandResolver;
import com.epam.telegramclient.business.event.events.UpdateEvent;
import com.epam.telegramclient.business.requestresolver.RequestResolvers;
import com.epam.telegramclient.tags.Junit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.epam.telegramclient.factories.DefaultRequest.defaultRequest;
import static com.epam.telegramclient.factories.UpdateFactory.defaultUpdate;
import static org.mockito.Mockito.*;

@Junit
@ExtendWith(MockitoExtension.class)
class OnUpdateEventMessageCommandListenerTest {

    @InjectMocks
    private OnUpdateEventMessageCommandListener listener;

    @Mock
    private CommandResolver commandResolver;
    @Mock
    private RequestResolvers requestResolvers;
    @Mock
    private Command command;
    @Mock
    private TelegramLongPollingBot bot;

    @Test
    void shouldExecuteCommandWhenRequestIsCommand() throws TelegramApiException {
        when(commandResolver.resolve(any())).thenReturn(command);
        UpdateEvent event = new UpdateEvent(bot, defaultUpdate());
        when(requestResolvers.resolve(defaultUpdate())).thenReturn(defaultRequest());

        listener.onApplicationEvent(event);

        verify(command).process(bot, defaultRequest());
    }
}