package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.model.DiffElement;

import java.util.ArrayList;

public class Formatter {
//    Format format;
//    public Formatter(String format) {
//        switch (format) {
//            case "stylish" -> this.format = Format.STYLISH;
//            case "plain" -> this.format = Format.PLAIN;
//            case "json" -> this.format = Format.JSON;
//        }
//    }
    public static String output(ArrayList<DiffElement> difference, String format) throws JsonProcessingException {
        return switch (format) {
            case "stylish" -> Stylish.output(difference);
            case "plain" -> Plain.output(difference);
            case "json" -> Json.output(difference);

            default -> throw new IllegalStateException("Unexpected value: " + format);
        };
    }
}
//enum Format {
//    STYLISH,
//    PLAIN,
//    JSON
//}
