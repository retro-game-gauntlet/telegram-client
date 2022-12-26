package com.epam.telegramclient.factories;

import com.epam.telegramclient.web.dto.Data;
import com.epam.telegramclient.web.dto.Game;
import com.epam.telegramclient.web.dto.Response;

import java.time.LocalDate;

public class GameResponseFactory {

    public static Response<Game> defaultGameResponse() {
        return new Response<>(new Data<>(new Game("Super Mario Bros.", LocalDate.of(1985, 9, 13))));
    }
}