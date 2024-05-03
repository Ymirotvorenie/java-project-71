package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static hexlet.code.Differ.generate;
import static hexlet.code.Utils.getFileContent;
import static hexlet.code.Utils.getFixturePath;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


public final class DifferTest {

    private static String expectedStylishFormatter;
    private static String expectedPlainFormatter;
    private static String expectedJsonFormatter;

    @BeforeAll
    public static void setExpectedFiles() throws IOException {

        expectedStylishFormatter = getFileContent(getFixturePath("result_stylish.txt"));
        expectedPlainFormatter = getFileContent(getFixturePath("result_plain.txt"));
        expectedJsonFormatter = getFileContent(getFixturePath("result_json.json"));
    }

    @Test
    public void testDifferGenerateJson() throws IOException {
        assertEquals(generate(getFixturePath("file1.json"),
                getFixturePath("file2.json"), "stylish"), expectedStylishFormatter);

        assertEquals(generate(getFixturePath("file1.json"),
                getFixturePath("file2.json"), "plain"), expectedPlainFormatter);

        assertEquals(generate(getFixturePath("file1.json"),
                getFixturePath("file2.json"), "json"), expectedJsonFormatter);

        assertEquals(generate(getFixturePath("file1.json"),
                getFixturePath("file2.json")), expectedStylishFormatter);
    }

    @Test
    public void testDifferGenerateYaml() throws IOException {
        assertEquals(generate(getFixturePath("file1.yml"),
                getFixturePath("file2.yml"), "stylish"), expectedStylishFormatter);

        assertEquals(generate(getFixturePath("file1.yml"),
                getFixturePath("file2.yml"), "plain"), expectedPlainFormatter);

        assertEquals(generate(getFixturePath("file1.yml"),
                getFixturePath("file2.yml"), "json"), expectedJsonFormatter);

        assertEquals(generate(getFixturePath("file1.yml"),
                getFixturePath("file2.yml")), expectedStylishFormatter);
    }

    @Test
    public void testEqualFiles() throws IOException {
        String expected = getFileContent(getFixturePath("result_equal_files.txt"));
        assertEquals(generate(getFixturePath("file2.json"),
                getFixturePath("file2.json"), "stylish"), expected);
    }

    @Test
    public void testWrongFileExtension() {
        var thrown = catchThrowable(
                () -> generate(getFixturePath("fileWithoutExtension"),
                        getFixturePath("file2.yml"), "stylish")
        );
        assertThat(thrown).isInstanceOf(IOException.class);
    }

    @Test
    public void testWrongFormatter() {
        var thrown = catchThrowable(
                () -> generate(getFixturePath("file1.yml"),
                        getFixturePath("file2.yml"), "wrong")
        );
        assertThat(thrown).isInstanceOf(IOException.class);
    }
}
