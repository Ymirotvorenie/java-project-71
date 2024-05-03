package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {

    public static String output(List<Map<String, Object>> difference, String format) throws IOException {
        return switch (format) {
            case "stylish" -> Stylish.output(difference);
            case "plain" -> Plain.output(difference);
            case "json" -> Json.output(difference);

            default -> throw new IOException("Unsupported format type: '"
                    + format + "'. Expected format: 'stylish', 'plain', 'json'");
        };
    }
}

