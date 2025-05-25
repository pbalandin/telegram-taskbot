package io.github.pbalandin.telegram.taskbot.service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskService {

    private final Map<String, String> tasks = new HashMap<>();

    public String create(String title, String description) {
        tasks.put(title, description);
        return title;
    }

    public String update(String title, String description) {
        tasks.put(title, description);
        return title;
    }

    public String delete(String title) {
        tasks.remove(title);
        return title;
    }

    public Map<String, String> getTasks() {
        return tasks;
    }
}
