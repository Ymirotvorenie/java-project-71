package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.model.DiffElement;

import java.util.List;

public class Json {
    public static String output(List<DiffElement> difference) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(difference);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
