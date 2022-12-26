package com.epam.telegramclient.business.command.message;

import com.epam.telegramclient.business.command.Command;
import com.epam.telegramclient.business.domain.Request;
import com.epam.telegramclient.web.client.GameServiceCLient;
import com.epam.telegramclient.web.dto.Game;
import com.epam.telegramclient.web.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Pattern;

/**
 * Command which response for '/platforms/{platformCode}/games/random' request
 */
@Component
@RequiredArgsConstructor
public class RandomGameCommand implements Command {

    private final GameServiceCLient gameServiceCLient;
    private final Pattern commandPattern = Pattern.compile("/platforms/.*/games/random");

    @Override
    public boolean isApplicableFor(Request request) {
        return commandPattern.matcher(request.command()).matches();
    }

    @Override
    public void process(TelegramLongPollingBot bot, Request request) throws TelegramApiException {
        Long chatId = request.chatId();
        String command = request.command();
        String[] parts = command.split("/");
        String platformCode = parts[2];
        Response<Game> response = gameServiceCLient.getRandomGame(platformCode);
        Game game = response.data().attributes();
        SendMessage message = new SendMessage(chatId.toString(), game.name());
        bot.execute(message);
    }
}
