package com.epam.telegramclient.business.requestresolver;

import com.epam.telegramclient.business.domain.Request;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface RequestResolvers {

    Request resolve(Update update);
}