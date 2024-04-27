package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file")
    private String filepath2;
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
    @Override
    public Integer call() throws IOException {
        try {
            System.out.println(Differ.generate(filepath1, filepath2));
            System.out.println();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return 0;
    }
}

