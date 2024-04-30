package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


public class DifferTest {
    @Test
    public void testWithoutFormat() throws IOException {
        String expected = Files.readString(Paths.get("./src/test/resources/result_stylish.txt"));

        assertEquals(generate("./src/test/resources/file1.json",
                "./src/test/resources/file2.json"), expected);
    }
    @Test
    public void testJsonDiffs() throws IOException {
        String expected = Files.readString(Paths.get("./src/test/resources/result_stylish.txt"));

        assertEquals(generate("./src/test/resources/file1.json",
                "./src/test/resources/file2.json", "stylish"), expected);
    }

    @Test
    public void testYamlDiffs() throws IOException {
        String expected = Files.readString(Paths.get("./src/test/resources/result_stylish.txt"));

        assertEquals(generate("./src/test/resources/file1.yml",
                "./src/test/resources/file2.yml", "stylish"), expected);
    }

    @Test
    public void testEqualFiles() throws IOException {
        String expected = Files.readString(Paths.get("./src/test/resources/result_equal_files.txt"));
        assertEquals(generate("./src/test/resources/file2.json",
                "./src/test/resources/file2.json", "stylish"), expected);
    }

    @Test
    public void testPlainFormatter() throws IOException {
        String expected = Files.readString(Paths.get("./src/test/resources/result_plain.txt"));

        assertEquals(generate("./src/test/resources/file1.yml",
                "./src/test/resources/file2.yml", "plain"), expected);
    }

//    @Test
//    public void testJsonFormatter() throws IOException {
//        String expected = Files.readString(Paths.get("./src/test/resources/result_json.txt"));
//
//        assertEquals(generate("./src/test/resources/file1.yml",
//                "./src/test/resources/file2.yml", "json"), expected);
//    }

    @Test
    public void testWrongFileExtension() {

        var thrown = catchThrowable(
                () -> generate("./src/test/resources/fileWithoutExrension",
                        "./src/test/resources/file2.yml", "stylish")
        );
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWrongFormat() {
        var thrown = catchThrowable(
                () -> generate("./src/test/resources/file1.yml",
                        "./src/test/resources/file2.yml", "wrong")
        );
        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }
}
