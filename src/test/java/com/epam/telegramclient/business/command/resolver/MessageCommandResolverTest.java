package com.epam.telegramclient.business.command.resolver;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.tags.Junit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.epam.telegramclient.factories.DefaultRequest.defaultRequest;
import static com.epam.telegramclient.factories.DefaultRequest.unknownRequest;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Junit
@ExtendWith(MockitoExtension.class)
class MessageCommandResolverTest {

    private CommandResolver resolver;

    @Mock
    private Command platformsCommand;
    @Mock
    private Command unknownCommand;

    @BeforeEach
    void setUp() {
        resolver = new MessageCommandResolver(singletonList(platformsCommand), unknownCommand);
    }

    @Test
    void shouldReturnPlatformsCommandWhenRequestIsPlatforms() {
        when(platformsCommand.isApplicableFor(defaultRequest())).thenReturn(true);

        Command command = resolver.resolve(defaultRequest());

        assertThat(command).isEqualTo(platformsCommand);
    }

    @Test
    void shouldReturnUnknownCommandWhenCommandNowFound() {
        Command command = resolver.resolve(unknownRequest());

        assertThat(command).isEqualTo(unknownCommand);
    }
}