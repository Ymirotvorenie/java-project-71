package hexlet.code;

import hexlet.code.model.DiffElement;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class DiffGenerator {
    public static <T> ArrayList<DiffElement<T>> generateDiffs(Map<String, T> file1, Map<String, T> file2) {
        var result = new ArrayList<DiffElement<T>>();

        var fields = new TreeSet<String>();
        fields.addAll(file1.keySet());
        fields.addAll(file2.keySet());

        for (String field : fields) {
            var firstData = file1.get(field);
            var secondData = file2.get(field);

            FieldStatus status = getStatus(field, file1, file2);
            result.add(new DiffElement<T>(field, firstData, secondData, status));
        }
        return result;
    }

    public static <T> FieldStatus getStatus(String field, Map<String, T> file1, Map<String, T> file2) {
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
