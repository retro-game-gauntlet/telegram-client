package com.epam.telegramclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfig {

    @Bean
    public int platformsButtonsPerRow() {
        return 5;
    }
}
