package com.epam.telegramclient.business.command.resolver;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.domain.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageCommandResolver implements CommandResolver {

    private final List<Command> messageCommands;
    private final Command unknownMessageCommand;

    @Override
    public Command resolve(Request request) {
        return messageCommands.stream()
                .filter(c -> c.isApplicableFor(request))
                .findFirst()
                .orElse(unknownMessageCommand);
    }
}