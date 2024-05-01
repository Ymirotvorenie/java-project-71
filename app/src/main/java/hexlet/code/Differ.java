package hexlet.code;

import hexlet.code.model.DiffElement;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

import static hexlet.code.Parser.parse;
import static hexlet.code.Formatter.output;

public class Differ {
    public static String generate(String file1, String file2) throws IOException {
        return generate(file1, file2, "stylish");
    }

    public static String generate(String file1, String file2, String format) throws IOException {
        Path filepath1 = Paths.get(file1).toAbsolutePath();
        Path filepath2 = Paths.get(file2).toAbsolutePath();
        var result = generateDiffs(parse(filepath1.toString()), parse(filepath2.toString()));
        return output(result, format);
    }

    public static ArrayList<DiffElement> generateDiffs(Map<String, Object> file1, Map<String, Object> file2) {
        var result = new ArrayList<DiffElement>();

        var fields = new TreeSet<String>();
        fields.addAll(file1.keySet());
        fields.addAll(file2.keySet());

        for (String field : fields) {
            var firstData = file1.get(field);
            var secondData = file2.get(field);

            FieldStatus status = getStatus(field, file1, file2);
            result.add(new DiffElement(field, firstData, secondData, status));
        }
        return result;
    }

    public static FieldStatus getStatus(String field, Map<String, Object> file1, Map<String, Object> file2) {
        if (!file1.containsKey(field)) {
            return FieldStatus.ADDED;
        } else if (!file2.containsKey(field)) {
            return FieldStatus.REMOVED;
        } else if (Objects.equals(file1.get(field), file2.get(field))) {
            return FieldStatus.EQUAL;
        } else {
            return FieldStatus.CHANGED;
        }
    }
}
