package com.epam.telegramclient.business.requestresolver;

import com.epam.telegramclient.business.domain.Request;
import com.epam.telegramclient.business.exception.RequestResolverNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.epam.telegramclient.factories.DefaultRequest.defaultRequest;
import static com.epam.telegramclient.factories.UpdateFactory.defaultUpdate;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestResolversImplTest {

    private RequestResolvers resolvers;

    @Mock
    private RequestResolver resolver;

    @BeforeEach
    void setUp() {
        resolvers = new RequestResolversImpl(singletonList(resolver));
    }

    @Test
    void shouldReturnRequestWhenRequestResolverWasFound() {
        when(resolver.isApplicableFor(defaultUpdate())).thenReturn(true);
        when(resolver.resolve(defaultUpdate())).thenReturn(defaultRequest());

        Request request = resolvers.resolve(defaultUpdate());

        assertThat(request).isEqualTo(defaultRequest());
    }

    @Test
    void shouldThrowExceptionWhenRequestResolverWasNotFound() {
        Update update = defaultUpdate();
        when(resolver.isApplicableFor(update)).thenReturn(false);

        assertThatThrownBy(() -> resolvers.resolve(update))
                .isInstanceOf(RequestResolverNotFoundException.class)
                .hasMessageContaining(update.toString());
    }
}