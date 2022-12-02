package com.epam.telegramclient.business.requestresolver;

import com.epam.telegramclient.business.domain.Request;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CallbackQueryRequestResolver implements RequestResolver {

    @Override
    public boolean isApplicableFor(Update update) {
        return update.hasCallbackQuery();
    }

    @Override
    public Request resolve(Update update) {
        return new Request(update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getData());
    }
}