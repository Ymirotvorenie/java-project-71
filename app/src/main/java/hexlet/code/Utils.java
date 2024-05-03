package hexlet.code;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Utils {
    public enum FileType {
        JSON,
        YAML,
        UNKNOWN
    }

    public static FileType getFileExtension(String filename) {
        return switch (Files.getFileExtension(filename)) {
            case "json" -> FileType.JSON;
            case "yaml", "yml" -> FileType.YAML;
            default -> FileType.UNKNOWN;
        };
    }

    public static String getPath(String file) {
        return Paths.get(file).toAbsolutePath().toString();
    }

    public static String getFileContent(String path) throws IOException {
        return Files.asCharSource(new File(path), Charsets.UTF_8).read();
    }

    public static String getFixturePath(String fileName) {
        return "./src/test/resources/" + fileName;
    }
}
