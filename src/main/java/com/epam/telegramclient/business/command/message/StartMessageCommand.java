package com.epam.telegramclient.business.command.message;

import com.epam.methodlog.annotation.InputMethodLog;
import com.epam.methodlog.annotation.OutputMethodLog;
import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.domain.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StartMessageCommand implements Command {

    private final String welcomeMessage;

    @Override
    @InputMethodLog
    @OutputMethodLog
    public boolean isApplicableFor(Request request) {
        return "/start".equalsIgnoreCase(request.command());
    }

    @Override
    @InputMethodLog
    public void process(TelegramLongPollingBot bot, Request request) throws TelegramApiException {
        ReplyKeyboardMarkup markup = buildMarkup();
        SendMessage response = new SendMessage(request.chatId().toString(), welcomeMessage);
        response.setReplyMarkup(markup);
        bot.execute(response);
    }

    private ReplyKeyboardMarkup buildMarkup() {
        KeyboardButton button = new KeyboardButton("Platforms");
        KeyboardRow row = new KeyboardRow(List.of(button));
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup(List.of(row));
        markup.setResizeKeyboard(true);
        return markup;
    }
}