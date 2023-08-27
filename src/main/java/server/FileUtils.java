package server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static void createDirectories(String location) {
        try {
            Files.createDirectories(Path.of(location));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
