package com.epam.telegramclient.factories;

import com.epam.telegramclient.business.domain.Request;

public class DefaultRequest {

    public static Request defaultRequest() {
        return new Request(123L, "/platforms");
    }

    public static Request notFoundRequest() {
        return new Request(321L, "/qwe");
    }

    public static Request helloRequest() {
        return new Request(45L, "hello");
    }
}