package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String output(List<Map<String, Object>> difference) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> element : difference) {
            var keySet = element.keySet();
            for (String key : keySet) {
                String status = String.valueOf(element.get(key));
                switch (status) {
                    case "ADDED" -> result.append(String.format("  + %s: %s\n",
                            element.get("key"), element.get("value")));
                    case "REMOVED" -> result.append(String.format("  - %s: %s\n",
                            element.get("key"), element.get("value")));
                    case "EQUAL" -> result.append(String.format("    %s: %s\n",
                            element.get("key"), element.get("value")));
                    case "CHANGED" ->
                            result.append(String.format("  - %s: %s\n  + %s: %s\n",
                                    element.get("key"), element.get("value1"),
                                    element.get("key"), element.get("value2")));
                    default -> { }
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
