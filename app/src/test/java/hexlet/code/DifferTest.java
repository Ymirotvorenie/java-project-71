package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static hexlet.code.Differ.generate;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


public class DifferTest {

    @Test
    public void testJsonDiffs() throws IOException {
        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

        assertEquals(generate("./src/test/resources/file1.json", "./src/test/resources/file2.json"), expected);
    }

    @Test
    public void testYamlDiffs() throws IOException {
        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

        assertEquals(generate("./src/test/resources/file1.yml", "./src/test/resources/file2.yml"), expected);
    }

    @Test
    public void testWrongFileExtension() throws IOException {

        var thrown = catchThrowable(
                () -> generate("./src/test/resources/fileWithoutExrension",
                        "./src/test/resources/file2.yml")
        );
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testEqualFiles() throws IOException {
        String expected = """
                {
                    chars1: [a, b, c]
                    chars2: false
                    checked: true
                    default: [value1, value2]
                    id: null
                    key2: value2
                    numbers1: [1, 2, 3, 4]
                    numbers2: [22, 33, 44, 55]
                    numbers4: [4, 5, 6]
                    obj1: {nestedKey=value, isNested=true}
                    setting1: Another value
                    setting2: 300
                    setting3: none
                }""";
        assertEquals(generate("./src/test/resources/file2.json", "./src/test/resources/file2.json"), expected);
    }
}
