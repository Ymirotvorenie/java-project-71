package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class DiffGenerator {

    public static ArrayList<Map<String, Object>> generateDiffs(Map<String, Object> file1, Map<String, Object> file2) {
        var result = new ArrayList<Map<String, Object>>();

        var fields = new TreeSet<String>();
        fields.addAll(file1.keySet());
        fields.addAll(file2.keySet());
        for (String field : fields) {
            Map<String, Object> diffElement = new LinkedHashMap<>();
            var value1 = file1.get(field);
            var value2 = file2.get(field);

            diffElement.put("key", field);
            if (!file1.containsKey(field)) {
                diffElement.put("status", "ADDED");
                diffElement.put("value", value2);
            } else if (!file2.containsKey(field)) {
                diffElement.put("status", "REMOVED");
                diffElement.put("value", value1);
            } else if (Objects.equals(value1, value2)) {
                diffElement.put("status", "EQUAL");
                diffElement.put("value", value1);
            } else {
                diffElement.put("status", "CHANGED");
                diffElement.put("value1", value1);
                diffElement.put("value2", value2);
            }
            result.add(diffElement);
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
