package com.epam.telegramclient.business.exception;

import org.telegram.telegrambots.meta.api.objects.Update;

import static java.lang.String.format;

public class RequestResolverNotFoundException extends RuntimeException {

    public RequestResolverNotFoundException(Update update) {
        super(format("Request resolver not found for update: %s", update));
    }
}