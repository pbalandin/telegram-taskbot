package io.github.pbalandin.telegram.taskbot.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Objects;

@Slf4j
public final class FileUtils {

    private FileUtils() {
    }

    public static InputStream getInputStream(String path) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        return Objects.requireNonNull(classLoader.getResourceAsStream(path));
    }
}
