package hexlet.code;

import java.io.IOException;

import static hexlet.code.DiffGenerator.generateDiffs;
import static hexlet.code.Parser.parse;
import static hexlet.code.Formatter.output;
import static hexlet.code.Utils.getFileContent;
import static hexlet.code.Utils.getFileExtension;
import static hexlet.code.Utils.getPath;

public class Differ {
    public static String generate(String file1, String file2) throws IOException {
        return generate(file1, file2, "stylish");
    }

    public static String generate(String file1, String file2, String format) throws IOException {
        String filepath1 = getPath(file1);
        String filepath2 = getPath(file2);

        String fileContent1 = getFileContent(filepath1);
        String fileContent2 = getFileContent(filepath2);

        var parsFile1 = parse(fileContent1, getFileExtension(filepath1));
        var parsFile2 = parse(fileContent2, getFileExtension(filepath2));

        var result = generateDiffs(parsFile1, parsFile2);

        return output(result, format);
    }
}

