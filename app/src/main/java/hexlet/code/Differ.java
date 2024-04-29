package hexlet.code;

import hexlet.code.model.DiffElement;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static hexlet.code.Parser.parse;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {
        var result = new ArrayList<DiffElement>();

        var file1 = parse(filepath1);
        var file2 = parse(filepath2);

        List<String> fields = CollectionUtils.union(file1.keySet(), file2.keySet()).stream().sorted().toList();
        FieldStatus status;

        for (String field : fields) {
            var firstData = file1.get(field);
            var secondData = file2.get(field);

            if (!file1.containsKey(field) && firstData == null) {
                status = FieldStatus.ADDED;
            } else if (!file2.containsKey(field) && secondData == null) {
                status = FieldStatus.REMOVED;
            } else if (Objects.equals(firstData, secondData)) {
                status = FieldStatus.EQUAL;
            } else {
                status = FieldStatus.CHANGED;
            }
            result.add(new DiffElement(field, firstData, secondData, status));
        }

        return diffString(result);
    }

    public static <T> String diffString(List<DiffElement> diffrence) {
        StringBuilder result = new StringBuilder("{\n");

        for (DiffElement element : diffrence) {
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
