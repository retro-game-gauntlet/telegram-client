package com.epam.telegramclient.business.event.events;

import com.epam.telegramclient.business.command.Command;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RandomGameSent extends ApplicationEvent {

    private final String gameName;

    public RandomGameSent(Command command, String gameName) {
        super(command);
        this.gameName = gameName;
    }
}