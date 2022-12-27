package com.epam.telegramclient.ui;

import com.epam.methodlog.annotation.InputMethodLog;
import com.epam.methodlog.annotation.OutputMethodLog;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
public class InlineButtonBuilder {

    @InputMethodLog
    @OutputMethodLog
    public InlineKeyboardButton build(String text, String callBack) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callBack);
        return button;
    }
}
