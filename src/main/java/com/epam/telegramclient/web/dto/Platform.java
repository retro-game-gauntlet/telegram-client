package com.epam.telegramclient.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;

public record Platform(
        @JsonProperty("code")
        String code,
        @JsonProperty("name")
        String name,
        @JsonProperty("gamesCount")
        int gamesCount,
        @JsonProperty("releasedAt")
        @JsonDeserialize(using = LocalDateDeserializer.class)
        LocalDate releasedAt) {
}