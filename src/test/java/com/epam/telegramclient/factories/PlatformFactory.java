package com.epam.telegramclient.factories;

import com.epam.telegramclient.web.dto.Platform;

import java.time.LocalDate;

public class PlatformFactory {

    public static Platform defaultPlatform() {
        return new Platform(
                "NES",
                "Nintendo Entertainment System",
                2,
                LocalDate.of(1983, 6, 15)
        );
    }
}