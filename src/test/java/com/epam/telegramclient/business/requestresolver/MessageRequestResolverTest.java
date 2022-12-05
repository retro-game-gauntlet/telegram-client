package com.epam.telegramclient.business.requestresolver;

import com.epam.telegramclient.business.domain.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.epam.telegramclient.factories.MessageFactory.defaultMessage;
import static com.epam.telegramclient.factories.UpdateFactory.defaultUpdate;
import static org.assertj.core.api.Assertions.assertThat;

class MessageRequestResolverTest {

    private RequestResolver resolver;

    @BeforeEach
    void setUp() {
        resolver = new MessageRequestResolver();
    }

    @Test
    void shouldReturnTrueWhenUpdateHasMessageWithText() {
        Update update = defaultUpdate();

        boolean isApplicable = resolver.isApplicableFor(update);

        assertThat(isApplicable).isTrue();
    }

    @Test
    void shouldReturnFalseWhenUpdateHasMessageWithoutText() {
        Update update = defaultUpdate(u -> u.setMessage(defaultMessage(m -> m.setText(null))));

        boolean isApplicable = resolver.isApplicableFor(update);

        assertThat(isApplicable).isFalse();
    }

    @Test
    void shouldReturnFalseWhenUpdateDoesNotHasMessage() {
        Update update = defaultUpdate(u -> u.setMessage(null));

        boolean isApplicable = resolver.isApplicableFor(update);

        assertThat(isApplicable).isFalse();
    }

    @Test
    void shouldReturnRequest() {
        Request actual = resolver.resolve(defaultUpdate());

        Request expected = new Request(123L, "hello");
        assertThat(actual).isEqualTo(expected);
    }
}