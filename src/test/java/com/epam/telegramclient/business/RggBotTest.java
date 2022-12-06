package com.epam.telegramclient.business;

import com.epam.telegramclient.business.event.events.UpdateEvent;
import com.epam.telegramclient.tags.Junit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static com.epam.telegramclient.factories.UpdateFactory.defaultUpdate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@Junit
@ExtendWith(MockitoExtension.class)
class RggBotTest {

    private RggBot bot;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @BeforeEach
    void setUp() {
        bot = new RggBot("rgg_bot", "fweshdz", eventPublisher);
    }

    @Test
    void shouldPublishUpdateEvent() {
        bot.onUpdateReceived(defaultUpdate());

        verify(eventPublisher).publishEvent(any(UpdateEvent.class));
    }

    @Test
    void shouldReturnBotName() {
        String botUsername = bot.getBotUsername();

        assertThat(botUsername).isEqualTo("rgg_bot");
    }

    @Test
    void shouldReturnBotToken() {
        String botToken = bot.getBotToken();

        assertThat(botToken).isEqualTo("fweshdz");
    }
}