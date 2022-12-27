package com.epam.telegramclient.business.command.message;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.domain.Request;
import com.epam.telegramclient.ui.InlineButtonBuilder;
import com.epam.telegramclient.ui.Markup;
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

import java.util.List;

/**
 * Command which response for 'Platforms' request
 */
@Component
@RequiredArgsConstructor
public class PlatformsMessageCommand implements Command {

    private final GameServiceCLient gameServiceCLient;
    private final InlineButtonBuilder inlineButtonBuilder;
    private final int platformsButtonsPerRow;

    @Override
    public boolean isApplicableFor(Request request) {
        return "Platforms".equalsIgnoreCase(request.command());
    }

    @Override
    @SneakyThrows
    public void process(TelegramLongPollingBot bot, Request request) {
        SendMessage response = new SendMessage(request.chatId().toString(), "Platforms");
        InlineKeyboardMarkup markup = Markup.builder()
                .withButtons(creatButtonRows())
                .itemsPerRow(platformsButtonsPerRow)
                .build();
        response.setReplyMarkup(markup);
        bot.execute(response);
    }

    private List<Platform> platforms() {
        Response<List<Platform>> platforms = gameServiceCLient.getPlatforms();
        return platforms.data().attributes();
    }

    private InlineKeyboardButton[] creatButtonRows() {
        return platforms().stream()
                .map(this::createButton).toArray(InlineKeyboardButton[]::new);
    }

    private InlineKeyboardButton createButton(Platform platform) {
        return inlineButtonBuilder.build(platform.code(), "Random game for " + platform.code());
    }
}