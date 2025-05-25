package io.github.pbalandin.telegram.taskbot.handler;

import io.github.pbalandin.telegram.bot.api.BotResponse;
import io.github.pbalandin.telegram.bot.api.annotation.Command;
import io.github.pbalandin.telegram.bot.api.annotation.Handler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Handler
public class DefaultHandler {

    @Command(value = ".+", order = -1)
    public BotResponse<SendMessage> notFound(Message message) {
        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(message.getChatId())
                .text("Unrecognized command. Please enter a valid command.")
                .build();

        return new BotResponse<>(sendMessage);
    }
}
