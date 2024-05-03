package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String output(List<Map<String, Object>> difference) {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> element : difference) {

            var keySet = element.keySet();
            for (String key : keySet) {
                String value1 = getStringForPlain(element.get("value1"));
                String value2 = getStringForPlain(element.get("value2"));
                String value = getStringForPlain(element.get("value"));

                String status = String.valueOf(element.get(key));
                switch (status) {
                    case "ADDED" -> result.append(String.format("Property '%s' was added with value: %s\n",
                            element.get("key"), value));
                    case "REMOVED" -> result.append(String.format("Property '%s' was removed\n",
                            element.get("key")));
//                case EQUAL -> result.append("");
                    case "CHANGED" ->
                            result.append(String.format("Property '%s' was updated. From %s to %s\n",
                                    element.get("key"), value1, value2));
                    default -> { }
                }
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
