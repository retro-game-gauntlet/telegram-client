package com.epam.telegramclient.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Response<T>(@JsonProperty("data") Data<T> data) {
}