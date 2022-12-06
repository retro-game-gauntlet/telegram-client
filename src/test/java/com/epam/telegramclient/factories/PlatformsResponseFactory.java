package com.epam.telegramclient.factories;

import com.epam.telegramclient.web.dto.Data;
import com.epam.telegramclient.web.dto.Platform;
import com.epam.telegramclient.web.dto.Response;

import java.util.List;

import static com.epam.telegramclient.factories.PlatformFactory.defaultPlatform;
import static java.util.Collections.singletonList;

public class PlatformsResponseFactory {

    public static Response<List<Platform>> defaultPlatformsResponse() {
        Platform platform = defaultPlatform();
        Data<List<Platform>> data = new Data<>(singletonList(platform));
        return new Response<>(data);
    }
}