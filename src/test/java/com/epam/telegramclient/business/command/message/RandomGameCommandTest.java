package com.epam.telegramclient.business.command.message;

import com.epam.telegramclient.business.domain.Request;
import com.epam.telegramclient.tags.Junit;
import com.epam.telegramclient.web.client.GameServiceCLient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.epam.telegramclient.factories.GameResponseFactory.defaultGameResponse;
import static com.epam.telegramclient.factories.SendMessageFactory.defaultMarioSendMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Junit
@ExtendWith(MockitoExtension.class)
class RandomGameCommandTest {

    @InjectMocks
    private RandomGameCommand command;

    @Mock
    private GameServiceCLient gameServiceCLient;
    @Mock
    private TelegramLongPollingBot bot;

    @Test
    void shouldBeApplicableForRandomGamesRequest() {
        Request request = new Request(123L, "Random game for NES");

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
    void shouldProcessRandomCommand() throws TelegramApiException {
        when(gameServiceCLient.getRandomGame("NES")).thenReturn(defaultGameResponse());
        Request request = new Request(123L, "Random game for NES");

        command.process(bot, request);

        verify(bot).execute(defaultMarioSendMessage());
    }
}