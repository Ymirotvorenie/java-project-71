package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Differ {
    public static final ArrayList<String> JSON_FIELDS = new ArrayList<>(List.of(
            "follow",
            "host",
            "proxy",
            "timeout",
            "verbose"));
    public static String generate(String file1, String file2) throws IOException {
        var result = new HashMap<String, ArrayList<String>>();

        ObjectMapper objectMapper = new ObjectMapper();
        var json1 = objectMapper.readValue(new File(file1), Map.class);
        var json2 = objectMapper.readValue(new File(file2), Map.class);

        JSON_FIELDS.forEach(f -> {
            var json1FieldValue = json1.get(f);
            var json2FieldValue = json2.get(f);

            if (json2FieldValue == null) {
                result.put(f, new ArrayList<>(List.of("-", json1FieldValue.toString())));
            } else {
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
                } else {
                    result.put(f, new ArrayList<>(List.of("+", json2FieldValue.toString())));
                }
            }
        });
        return diffString(result);
    }

    public static String diffString(Map<String, ArrayList<String>> diffMap) {
        StringBuilder result = new StringBuilder("{\n");
        JSON_FIELDS.forEach(f -> {
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
