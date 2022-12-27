package com.epam.telegramclient.business.command.message;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.domain.Request;
import com.epam.telegramclient.tags.Junit;
import com.epam.telegramclient.ui.InlineButtonBuilder;
import com.epam.telegramclient.web.client.GameServiceCLient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.epam.telegramclient.factories.InlineButtonFactory.defaultButton;
import static com.epam.telegramclient.factories.PlatformsResponseFactory.defaultPlatformsResponse;
import static com.epam.telegramclient.factories.SendMessageFactory.defaultPlatformsSendMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Junit
@ExtendWith(MockitoExtension.class)
class PlatformsMessageCommandTest {

    private Command command;

    @Mock
    private GameServiceCLient gameServiceCLient;
    @Mock
    private InlineButtonBuilder inlineButtonBuilder;
    @Mock
    private TelegramLongPollingBot bot;

    @BeforeEach
    void setUp() {
        command = new PlatformsMessageCommand(gameServiceCLient, inlineButtonBuilder, 1);
    }

    @Test
    void shouldBeApplicableForPlatformsRequest() {
        Request request = new Request(123L, "Platforms");

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
    void shouldExecuteResponseWithNesButton() throws TelegramApiException {
        when(gameServiceCLient.getPlatforms()).thenReturn(defaultPlatformsResponse());
        when(inlineButtonBuilder.build("NES", "Random game for NES")).thenReturn(defaultButton());

        Request request = new Request(123L, "Platforms");
        command.process(bot, request);

        verify(bot).execute(defaultPlatformsSendMessage());
    }
}