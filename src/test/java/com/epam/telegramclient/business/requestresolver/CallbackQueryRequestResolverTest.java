package com.epam.telegramclient.business.requestresolver;

import com.epam.telegramclient.business.domain.Request;
import com.epam.telegramclient.tags.Junit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.epam.telegramclient.factories.UpdateFactory.defaultUpdate;
import static org.assertj.core.api.Assertions.assertThat;

@Junit
class CallbackQueryRequestResolverTest {

    private RequestResolver resolver;

    @BeforeEach
    void setUp() {
        resolver = new CallbackQueryRequestResolver();
    }

    @Test
    void shouldReturnTrueWhenUpdateHasCallback() {
        Update update = defaultUpdate();

        boolean isApplicable = resolver.isApplicableFor(update);

        assertThat(isApplicable).isTrue();
    }

    @Test
    void shouldReturnFalseWhenUpdateDoesNotHasCallback() {
        Update update = defaultUpdate(u -> u.setCallbackQuery(null));

        boolean isApplicable = resolver.isApplicableFor(update);

        assertThat(isApplicable).isFalse();
    }

    @Test
    void shouldReturnRequest() {
        Update update = defaultUpdate();

        Request actual = resolver.resolve(update);

        Request expected = new Request(123L, "hi");
        assertThat(actual).isEqualTo(expected);
    }
}