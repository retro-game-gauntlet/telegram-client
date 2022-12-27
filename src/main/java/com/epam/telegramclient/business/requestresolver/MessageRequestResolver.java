package com.epam.telegramclient.business.requestresolver;

import com.epam.methodlog.annotation.InputMethodLog;
import com.epam.methodlog.annotation.OutputMethodLog;
import com.epam.telegramclient.business.domain.Request;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MessageRequestResolver implements RequestResolver {

    @Override
    @InputMethodLog
    @OutputMethodLog
    public boolean isApplicableFor(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    @Override
    @InputMethodLog
    @OutputMethodLog
    public Request resolve(Update update) {
        return new Request(update.getMessage().getChatId(), update.getMessage().getText());
    }
}