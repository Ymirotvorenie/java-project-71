package hexlet.code.formatters;

import hexlet.code.model.DiffElement;

import java.util.List;

public class Stylish {
    public static String output(List<DiffElement> difference) {
        StringBuilder result = new StringBuilder("{\n");

        for (DiffElement element : difference) {
            switch (element.getStatus()) {
                case ADDED -> result.append("  + ")
                        .append(element.getKey())
                        .append(": ")
                        .append(element.getSecondValue())
                        .append("\n");
                case REMOVED -> result.append("  - ")
                        .append(element.getKey())
                        .append(": ")
                        .append(element.getFirstValue())
                        .append("\n");
                case EQUAL -> result.append("    ")
                        .append(element.getKey())
                        .append(": ")
                        .append(element.getFirstValue())
                        .append("\n");
                case CHANGED -> {
                    result.append("  - ")
                            .append(element.getKey())
                            .append(": ")
                            .append(element.getFirstValue())
                            .append("\n");
                    result.append("  + ")
                            .append(element.getKey())
                            .append(": ")
                            .append(element.getSecondValue())
                            .append("\n");
                }
                default -> { }
            }
        }
        result.append("}");
        return result.toString();
    }
}
