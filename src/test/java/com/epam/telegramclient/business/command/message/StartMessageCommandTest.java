package com.epam.telegramclient.business.command.message;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.domain.Request;
import com.epam.telegramclient.tags.Junit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@Junit
@ExtendWith(MockitoExtension.class)
class StartMessageCommandTest {

    private Command command;

    @Mock
    private TelegramLongPollingBot bot;

    @BeforeEach
    void setUp() {
        command = new StartMessageCommand("Hello");
    }

    @Test
    void shouldBeApplicableForStartCommand() {
        Request request = new Request(123L, "/start");

        boolean isApplicableFor = command.isApplicableFor(request);

        assertThat(isApplicableFor).isTrue();
    }


    @Test
    void shouldNotBeApplicableForUnknownCommand() {
        Request request = new Request(123L, "/qwe");

        boolean isApplicableFor = command.isApplicableFor(request);

        assertThat(isApplicableFor).isFalse();
    }

    @Test
    void shouldProcessWelcomeCommand() throws TelegramApiException {
        Request request = new Request(123L, "/start");

        command.process(bot, request);

        SendMessage response = new SendMessage("123", "Hello");
        verify(bot).execute(response);
    }
}