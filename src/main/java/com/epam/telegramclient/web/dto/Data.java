package com.epam.telegramclient.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Data<T>(@JsonProperty("attributes") T attributes) {
}