package hexlet.code.model;

import hexlet.code.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiffElement {
    private String key;
    private Object firstValue;
    private Object secondValue;
    private FieldStatus status;
}
