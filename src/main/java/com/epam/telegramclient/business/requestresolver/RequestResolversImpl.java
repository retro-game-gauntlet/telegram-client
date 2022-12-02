package com.epam.telegramclient.business.requestresolver;

import com.epam.telegramclient.business.domain.Request;
import com.epam.telegramclient.business.exception.RequestResolverNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RequestResolversImpl implements RequestResolvers {

    private final List<RequestResolver> requestResolvers;

    @Override
    public Request resolve(Update update) {
        return requestResolvers.stream()
                .filter(resolver -> resolver.isApplicableFor(update))
                .findFirst()
                .map(resolver -> resolver.resolve(update))
                .orElseThrow(()-> new RequestResolverNotFoundException(update)) ;
    }
}