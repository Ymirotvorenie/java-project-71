package hexlet.code.model;

import hexlet.code.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiffElement<T> {
    private String key;
    private T left;
    private T right;
    private FieldStatus status;
}
