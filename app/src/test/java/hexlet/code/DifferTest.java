package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void test1() {
        assertEquals(String.valueOf(15), String.valueOf(15));
    }

    @Test
    public void testJsonDiffs() throws IOException {
        String expected = "{\n" +
                "  - proxy: 123.234.53.22\n" +
                "    host: hexlet.io\n" +
                "  - follow: false\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";

        assertEquals(generate("./src/test/resources/file1.json", "./src/test/resources/file2.json"), expected);
    }

}
