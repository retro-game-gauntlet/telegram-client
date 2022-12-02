package com.epam.telegramclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TelegramClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramClientApplication.class, args);
    }
}
