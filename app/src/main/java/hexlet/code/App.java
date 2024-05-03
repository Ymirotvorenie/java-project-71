package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    private String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws IOException {
        try {
            String differs = Differ.generate(filepath1, filepath2, format);
            System.out.println(differs);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return 1;
        }
        return 0;
    }
}

//в целом часто мы создаем дата классы для данных,
//но тут не много не тот случай у нас должна быть динамичная
//        структура данных, firstValue и secondValue по сути
//нам нужны только в случае изменения остальное все обходится
//просто value. (Да и в цеолом можно короче просто value1 и value2).
//МОжно заменить дата класс на Map<String, Object> тогда при
//формировании json получится нечто вроде такого
//
//{ "key": "chars1", "type": "UNCHANGED", "value": [ "a", "b", "c" ] },
//        { "key": "chars2", "type": "CHANGED", "value1": [ "d", "e", "f" ],
//        "value2": false }, { "key": "checked", "type": "CHANGED",
//        "value1": false, "value2": true },
//        { "key": "default", "type": "CHANGED",
//        "value1": null, "value2": [ "value1", "value2" ] },

