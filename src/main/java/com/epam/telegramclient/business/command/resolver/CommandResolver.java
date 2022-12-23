package com.epam.telegramclient.business.command.resolver;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.domain.Request;

public interface CommandResolver {

    Command resolve(Request request);
}