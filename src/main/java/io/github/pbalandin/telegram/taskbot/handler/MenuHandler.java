package io.github.pbalandin.telegram.taskbot.handler;

import io.github.pbalandin.telegram.bot.api.BotResponse;
import io.github.pbalandin.telegram.bot.api.annotation.Command;
import io.github.pbalandin.telegram.bot.api.annotation.Handler;
import io.github.pbalandin.telegram.taskbot.utils.FileUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

@Handler
public class MenuHandler {

    private final String IMAGE_START_PNG = "images/start.jpg";
    private final String IMAGE_NAME = "start";
    private final String DESCRIPTION = """
            This is a demo Telegram bot designed to demonstrate the capabilities of a bot library.
            Use the menu to explore its features and functionalities. For more information, visit the official documentation.
            """;

    @Command("/start")
    public BotResponse<SendPhoto> start(Message message) {
        SendPhoto sendPhoto = SendPhoto
                .builder()
                .chatId(message.getChatId())
                .caption(DESCRIPTION)
                .photo(new InputFile(FileUtils.getInputStream(IMAGE_START_PNG), IMAGE_NAME))
                .build();

        return new BotResponse<>(sendPhoto);
    }

}
