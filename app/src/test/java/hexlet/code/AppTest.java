package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static hexlet.code.Utils.getFixturePath;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class AppTest {
    private CommandLine cmd;

    @BeforeEach
    public void setUp() {
        App app = new App();
        cmd = new CommandLine(app);
    }

    @Test
    public void testSuccessExitCode() {
        String path1 = getFixturePath("file1.json");
        String path2 = getFixturePath("file2.json");
        String[] args = {path1, path2};
        int exitCode = cmd.execute(args);
        assertEquals(0, exitCode);
    }

    @Test
    public void testFailExitCode() {
        String path1 = getFixturePath("fileWithOutExtension");
        String path2 = getFixturePath("file2.json");
        String[] args = {path1, path2};
        int exitCode = cmd.execute(args);
        assertEquals(1, exitCode);
    }
}
