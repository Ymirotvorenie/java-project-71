package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

import static hexlet.code.Utils.FileType;

public class Parser {

    public static Map<String, Object> parse(String fileContent, FileType type) throws IOException {
        return switch (type) {
            case JSON -> new ObjectMapper().readValue(fileContent, new TypeReference<Map<String, Object>>() { });
            case YAML -> new YAMLMapper().readValue(fileContent, new TypeReference<Map<String, Object>>() { });
            default -> throw new IOException("Unsupported format type");
        };
    }
}
