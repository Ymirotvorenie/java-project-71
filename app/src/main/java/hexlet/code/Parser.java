package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String filepath) throws IOException {
        FileType type = getFileExtension(filepath);

        return switch (type) {
            case JSON -> new ObjectMapper().readValue(new File(filepath), new TypeReference<Map<String, Object>>() { });
            case YAML -> new YAMLMapper().readValue(new File(filepath), new TypeReference<Map<String, Object>>() { });
            default -> throw new IllegalArgumentException("Unsupported format");
        };
    }

    private enum FileType {
        JSON,
        YAML,
        UNKNOWN
    }
    private static FileType getFileExtension(String filename) {
        return switch (Files.getFileExtension(filename)) {
            case "json" -> FileType.JSON;
            case "yaml", "yml" -> FileType.YAML;
            default -> FileType.UNKNOWN;
        };
    }
}