package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.model.DiffElement;

import java.io.IOException;
import java.util.ArrayList;

public class Formatter {

    public static <T> String output(ArrayList<DiffElement<T>> difference, String format) throws IOException {
        return switch (format) {
            case "stylish" -> Stylish.output(difference);
            case "plain" -> Plain.output(difference);
            case "json" -> Json.output(difference);

            default -> throw new IOException("Unsupported format type: '"
                    + format + "'. Expected format: 'stylish', 'plain', 'json'");
        };
    }
}

