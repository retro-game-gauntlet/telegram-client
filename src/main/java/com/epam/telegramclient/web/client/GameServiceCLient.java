package com.epam.telegramclient.web.client;

import com.epam.telegramclient.web.dto.Platform;
import com.epam.telegramclient.web.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "game-service", url = "http://localhost:8081/")
public interface GameServiceCLient {

    @GetMapping("v1/platforms")
    Response<List<Platform>> getPlatforms();
}