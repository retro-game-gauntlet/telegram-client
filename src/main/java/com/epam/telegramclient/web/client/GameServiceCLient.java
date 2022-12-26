package com.epam.telegramclient.web.client;

import com.epam.telegramclient.web.dto.Game;
import com.epam.telegramclient.web.dto.Platform;
import com.epam.telegramclient.web.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "game-service", url = "http://localhost:8081/")
public interface GameServiceCLient {

    @GetMapping("v1/platforms")
    Response<List<Platform>> getPlatforms();

    @GetMapping("v1/platforms/{code}/games/random")
    Response<Game> getRandomGame(@PathVariable("code") String platformCode);
}