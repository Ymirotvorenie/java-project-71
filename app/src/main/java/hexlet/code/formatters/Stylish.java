package hexlet.code.formatters;

import hexlet.code.model.DiffElement;

import java.util.List;

public class Stylish {
    public static <T> String output(List<DiffElement<T>> difference) {
        StringBuilder result = new StringBuilder("{\n");

        for (DiffElement<T> element : difference) {
            switch (element.getStatus()) {
                case ADDED -> result.append(String.format("  + %s: %s\n",
                        element.getKey(), element.getRight()));
                case REMOVED -> result.append(String.format("  - %s: %s\n",
                        element.getKey(), element.getLeft()));
                case EQUAL -> result.append(String.format("    %s: %s\n",
                        element.getKey(), element.getLeft()));
                case CHANGED -> result.append(String.format("  - %s: %s\n  + %s: %s\n",
                        element.getKey(), element.getLeft(),
                        element.getKey(), element.getRight()));
                default -> { }
            }
        }
        result.append("}");
        return result.toString();
    }
}
