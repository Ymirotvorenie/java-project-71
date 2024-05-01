package hexlet.code.formatters;

import hexlet.code.model.DiffElement;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String output(List<DiffElement> difference) {
        StringBuilder result = new StringBuilder();

        for (DiffElement element : difference) {
            String firstValue = getStringForPlain(element.getFirstValue());
            String secondValue = getStringForPlain(element.getSecondValue());

            switch (element.getStatus()) {
                case ADDED -> result.append("Property '")
                        .append(element.getKey())
                        .append("' was added with value: ")
                        .append(secondValue)
                        .append("\n");
                case REMOVED -> result.append("Property '")
                        .append(element.getKey())
                        .append("' was removed")
                        .append("\n");
//                case EQUAL -> result.append("");
                case CHANGED -> {
                    result.append("Property '")
                            .append(element.getKey())
                            .append("' was updated. From ")
                            .append(firstValue)
                            .append(" to ")
                            .append(secondValue)
                            .append("\n");
                }
                default -> { }
            }
        }
        return result.toString().trim();
    }

    public static String getStringForPlain(Object o) {
        if (o instanceof Collection<?> || o instanceof Map) {
            return "[complex value]";
        } else if (o instanceof String) {
            return "'" + o + "'";
        } else {
            return o + "";
        }
    }
}
