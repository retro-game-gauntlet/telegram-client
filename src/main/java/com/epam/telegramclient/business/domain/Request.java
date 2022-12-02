package com.epam.telegramclient.business.domain;

public record Request(Long chatId, String command) {
}