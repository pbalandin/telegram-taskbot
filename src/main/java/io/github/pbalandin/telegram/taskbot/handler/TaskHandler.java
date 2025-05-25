package io.github.pbalandin.telegram.taskbot.handler;

import io.github.pbalandin.telegram.bot.api.BotResponse;
import io.github.pbalandin.telegram.bot.api.annotation.Command;
import io.github.pbalandin.telegram.bot.api.annotation.CommandParam;
import io.github.pbalandin.telegram.bot.api.annotation.Handler;
import io.github.pbalandin.telegram.taskbot.service.TaskService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

@Handler
public class TaskHandler {

    private final TaskService taskService;

    public TaskHandler(TaskService taskService) {
        this.taskService = taskService;
    }

    @Command("/create")
    public BotResponse<SendMessage> create(Message message) {
        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(message.getChatId())
                .text("Creating... Please enter task details in format: title description")
                .build();

        return new BotResponse<>(sendMessage);
    }

    @Command(value = "(.+);(.+)", after = "create")
    public BotResponse<SendMessage> createEnter(Message message, @CommandParam(1) String title, @CommandParam(2) String description) {
        taskService.create(title, description);

        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(message.getChatId())
                .text("Task created successfully!\nTitle: " + title + "\nDescription: " + description)
                .build();

        return new BotResponse<>(sendMessage);
    }

    @Command("/update")
    public BotResponse<SendMessage> update(Message message) {
        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(message.getChatId())
                .text("Updating... Please enter task details in format: title description")
                .build();

        return new BotResponse<>(sendMessage);
    }

    @Command(value = "(.+);(.+)", after = "update")
    public BotResponse<SendMessage> updateEnter(Message message, @CommandParam(1) String title, @CommandParam(2) String description) {
        taskService.update(title, description);

        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(message.getChatId())
                .text("Task updated successfully!\nTitle: " + title + "\nDescription: " + description)
                .build();

        return new BotResponse<>(sendMessage);
    }

    @Command("/delete (.+)")
    public BotResponse<SendMessage> delete(Message message, @CommandParam(1) String title) {
        taskService.delete(title);

        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(message.getChatId())
                .text("Task deleted successfully!\nTitle: " + title)
                .build();

        return new BotResponse<>(sendMessage);
    }

    @Command("/show")
    public BotResponse<SendMessage> show(Message message) {
        Map<String, String> tasks = taskService.getTasks();

        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(message.getChatId())
                .text("Your tasks:\n" + tasks.entrySet().stream()
                        .map(entry -> entry.getKey() + ": " + entry.getValue())
                        .reduce((a, b) -> a + "\n" + b)
                        .orElse("No tasks found."))
                .build();

        return new BotResponse<>(sendMessage);
    }
}
