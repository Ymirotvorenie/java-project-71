package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


public final class DifferTest {
    private static String expectedStylishFormatter;
    private static String expectedPlainFormatter;
    private static String expectedJsonFormatter;
    @BeforeAll
    public static void setExpectedFiles() throws IOException {
        expectedStylishFormatter = Files.readString(Paths.get("./src/test/resources/result_stylish.txt"));
        expectedPlainFormatter = Files.readString(Paths.get("./src/test/resources/result_plain.txt"));
        expectedJsonFormatter = Files.readString(Paths.get("./src/test/resources/result_json.txt"));
    }

    @Test
    public void testDifferGenerateJson() throws IOException {
        assertEquals(generate("./src/test/resources/file1.json",
                "./src/test/resources/file2.json", "stylish"), expectedStylishFormatter);

        assertEquals(generate("./src/test/resources/file1.json",
                "./src/test/resources/file2.json", "plain"), expectedPlainFormatter);

        assertEquals(generate("./src/test/resources/file1.json",
                "./src/test/resources/file2.json", "json"), expectedJsonFormatter);

        assertEquals(generate("./src/test/resources/file1.json",
                "./src/test/resources/file2.json"), expectedStylishFormatter);
    }

    @Test
    public void testDifferGenerateYaml() throws IOException {
        assertEquals(generate("./src/test/resources/file1.yml",
                "./src/test/resources/file2.yml", "stylish"), expectedStylishFormatter);

        assertEquals(generate("./src/test/resources/file1.yml",
                "./src/test/resources/file2.yml", "plain"), expectedPlainFormatter);

        assertEquals(generate("./src/test/resources/file1.yml",
                "./src/test/resources/file2.yml", "json"), expectedJsonFormatter);

        assertEquals(generate("./src/test/resources/file1.yml",
                "./src/test/resources/file2.yml"), expectedStylishFormatter);
    }

    @Test
    public void testEqualFiles() throws IOException {
        String expected = Files.readString(Paths.get("./src/test/resources/result_equal_files.txt"));
        assertEquals(generate("./src/test/resources/file2.json",
                "./src/test/resources/file2.json", "stylish"), expected);
    }

    @Test
    public void testWrongFileExtension() {
        var thrown = catchThrowable(
                () -> generate("./src/test/resources/fileWithoutExtension",
                        "./src/test/resources/file2.yml", "stylish")
        );
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWrongFormatter() {
        var thrown = catchThrowable(
                () -> generate("./src/test/resources/file1.yml",
                        "./src/test/resources/file2.yml", "wrong")
        );
        assertThat(thrown).isInstanceOf(IOException.class);
    }
}
