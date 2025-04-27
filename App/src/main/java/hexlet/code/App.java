package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private String filePath1;

    @Parameters(index = "1", description = "path to second file")
    private String filePath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public Integer call() throws Exception {
        // Чтение и парсинг файлов
        Map<String, Object> data1 = parseFile(filePath1);
        Map<String, Object> data2 = parseFile(filePath2);

        // Сравнение данных
        System.out.println("Comparing files:");
        System.out.println("File 1: " + filePath1 + ", data: " + data1);
        System.out.println("File 2: " + filePath2 + ", data: " + data2);
        System.out.println("Format: " + format);

        // Здесь будет логика сравнения данных

        return 0;
    }

    private Map<String, Object> parseFile(String filePath) throws Exception {
        String content = readFile(filePath);
        return parseJson(content);
    }

    private String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        return new String(Files.readAllBytes(path));
    }

    private Map<String, Object> parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}