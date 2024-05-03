package hexlet.code.formatters;

import hexlet.code.model.DiffElement;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {
    public static <T> String output(List<DiffElement<T>> difference) {
        StringBuilder result = new StringBuilder();

        for (DiffElement<T> element : difference) {
            String leftValue = getStringForPlain(element.getLeft());
            String rightValue = getStringForPlain(element.getRight());

            switch (element.getStatus()) {
                case ADDED -> result.append(String.format("Property '%s' was added with value: %s\n",
                        element.getKey(), rightValue));
                case REMOVED -> result.append(String.format("Property '%s' was removed\n",
                        element.getKey()));
//                case EQUAL -> result.append("");
                case CHANGED ->
                        result.append(String.format("Property '%s' was updated. From %s to %s\n",
                                element.getKey(), leftValue, rightValue));
                default -> { }
            }
        }
        return result.toString().trim();
    }

    public static <T> String getStringForPlain(T o) {
        if (o instanceof Collection<?> || o instanceof Map) {
            return "[complex value]";
        } else if (o instanceof String) {
            return "'" + o + "'";
        } else {
            return String.valueOf(o);
        }
    }
}
