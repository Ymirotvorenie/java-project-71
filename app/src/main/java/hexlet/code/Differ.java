package hexlet.code;

import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hexlet.code.Parser.parse;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {
        var result = new HashMap<String, ArrayList<String>>();

        var file1 = parse(filepath1);
        var file2 = parse(filepath2);

        List<String> fieldsNames = CollectionUtils.union(file1.keySet(), file2.keySet()).stream().sorted().toList();

        fieldsNames.forEach(f -> {
            var json1FieldValue = file1.get(f);
            var json2FieldValue = file2.get(f);

            if (!file1.containsKey(f)) {
                result.put(f, new ArrayList<>(List.of("+", json2FieldValue.toString())));
            } else if (!file2.containsKey(f)) {
                result.put(f, new ArrayList<>(List.of("-", json1FieldValue.toString())));
            } else if (json2FieldValue == null && json1FieldValue != null) {
                result.put(f, new ArrayList<>(List.of("-", json1FieldValue.toString(),
                        "+", "null")));
            } else
                if (json1FieldValue != null) {
                    if (json1FieldValue.equals(json2FieldValue)) {
                        result.put(f, new ArrayList<>(List.of(" ", json2FieldValue.toString())));
                    } else {
                        result.put(f,
                                new ArrayList<>(List.of(
                                        "-",
                                        json1FieldValue.toString(),
                                        "+",
                                        json2FieldValue.toString())));
                    }
                } else if (json2FieldValue != null) {
                    result.put(f, new ArrayList<>(List.of("-", "null", "+", json2FieldValue.toString())));
                } else {
                    result.put(f, new ArrayList<>(List.of(" ", "null")));
                }

        });
        return diffString(result, fieldsNames);
    }

    public static String diffString(Map<String, ArrayList<String>> diffMap, List<String> fieldsNames) {
        StringBuilder result = new StringBuilder("{\n");

        fieldsNames.forEach(f -> {
            if (diffMap.containsKey(f)) {
                for (int i = 0; i < diffMap.get(f).size(); i += 2) {
                    result.append("  ")
                            .append(diffMap.get(f).get(i))
                            .append(" ")
                            .append(f)
                            .append(": ")
                            .append(diffMap.get(f).get(i + 1))
                            .append("\n");
                }
            }


        });
        result.append("}");
        return result.toString();
    }
}
