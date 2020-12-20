package tr.havelsan.ueransim.app.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/*
 * This class shall be used for file writing operations inside of UERANSIM project.
 * Purpose of this is to perform unprivileged file operations even if the agent runs with 'sudo'.
 */
public class FileServer {

    public static void initialize() {
        // TODO
    }

    public static void appendFile(String path, String content) {
        appendFile(path, content.getBytes(StandardCharsets.UTF_8));
    }

    // TODO
    public static void appendFile(String path, byte[] data) {
        final Path p = Paths.get(path);
        try {
            Files.write(p, data, Files.exists(p) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
