package com.epam.telegramclient;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OnUpdateListener implements UpdatesListener {

    @Override
    public int process(List<Update> list) {
        log.info("Incoming updates: {}", list);
        return CONFIRMED_UPDATES_ALL;
    }
}