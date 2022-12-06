package com.epam.telegramclient.business.command.message;

import com.epam.telegramclient.business.domain.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotFoundMessageCommandTest {

    @InjectMocks
    private NotFoundMessageCommand command;

    @Mock
    private TelegramLongPollingBot bot;

    @Test
    void shouldReturnCommandNotFoundMessage() throws TelegramApiException {
        Request request = new Request(123L, "/qwe");

        command.process(bot, request);

        SendMessage response = new SendMessage("123", "Command /qwe not found");
        verify(bot).execute(response);
    }
}