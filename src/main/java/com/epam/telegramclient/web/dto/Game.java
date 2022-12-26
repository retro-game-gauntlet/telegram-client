package com.epam.telegramclient.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record Game(
        @JsonProperty("name")
        String name,
        @JsonProperty("releasedAt")
        LocalDate releasedAt) {
}