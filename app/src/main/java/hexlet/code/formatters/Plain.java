package hexlet.code.formatters;

import hexlet.code.model.DiffElement;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {
//    Property 'chars2' was updated. From [complex value] to false
//    Property 'checked' was updated. From false to true
//    Property 'default' was updated. From null to [complex value]
//    Property 'id' was updated. From 45 to null
//    Property 'key1' was removed
//    Property 'key2' was added with value: 'value2'
//    Property 'numbers2' was updated. From [complex value] to [complex value]
//    Property 'numbers3' was removed
//    Property 'numbers4' was added with value: [complex value]
//    Property 'obj1' was added with value: [complex value]
//    Property 'setting1' was updated. From 'Some value' to 'Another value'
//    Property 'setting2' was updated. From 200 to 300
//    Property 'setting3' was updated. From true to 'none'
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
//                case EQUAL -> result.append("    ")
//                        .append(element.getKey())
//                        .append(": ")
//                        .append(element.getFirstValue())
//                        .append("\n");
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
        return result.toString();
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
