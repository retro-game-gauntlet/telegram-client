package com.epam.telegramclient.business.requestresolver;

import com.epam.telegramclient.business.domain.Request;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface RequestResolver {

    boolean isApplicableFor(Update update);

    Request resolve(Update update);
}