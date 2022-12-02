package com.epam.telegramclient.business.command.message;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.domain.Request;
import com.epam.telegramclient.web.client.GameServiceCLient;
import com.epam.telegramclient.web.dto.Platform;
import com.epam.telegramclient.web.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component("/platforms")
@RequiredArgsConstructor
public class AllPlatformsMessageCommand implements Command {

    private final GameServiceCLient gameServiceCLient;

    @Override
    @SneakyThrows
    public void process(TelegramLongPollingBot bot, Request request) {
        SendMessage response = new SendMessage(request.chatId().toString(), "Platforms");
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(creatButtonRows());
        response.setReplyMarkup(markup);
        bot.execute(response);
    }

    private List<Platform> platforms() {
        Response<List<Platform>> platforms = gameServiceCLient.getPlatforms();
        return platforms.data().attributes();
    }

    private List<List<InlineKeyboardButton>> creatButtonRows() {
        return platforms().stream()
                .map(this::createButton).toList();
    }

    private List<InlineKeyboardButton> createButton(Platform platform) {
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(platform.code());
        button.setCallbackData("/" + platform.code() + "/games/random");
        buttonsRow.add(button);
        return buttonsRow;
    }
}