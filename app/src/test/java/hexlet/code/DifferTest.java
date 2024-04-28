package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static hexlet.code.Differ.generate;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


public class DifferTest {

    @Test
    public void test1() {
        assertEquals(String.valueOf(15), String.valueOf(15));
    }

    @Test
    public void testJsonDiffs() throws IOException {
        String expected = """
                {
                  - proxy: 123.234.53.22
                    host: hexlet.io
                  - follow: false
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        assertEquals(generate("./src/test/resources/file1.json", "./src/test/resources/file2.json"), expected);
    }

    @Test
    public void testYamlDiffs() throws IOException {
        String expected = """
                {
                  - proxy: 123.234.53.22
                    host: hexlet.io
                  - follow: false
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
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
                    host: hexlet.io
                    timeout: 20
                    verbose: true
                }""";
        assertEquals(generate("./src/test/resources/file2.json", "./src/test/resources/file2.json"), expected);
    }
}
