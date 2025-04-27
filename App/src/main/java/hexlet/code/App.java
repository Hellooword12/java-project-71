package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
        // Чтение и парсинг файлов из ресурсов
        Map<String, Object> data1 = parseResourceFile(filePath1);
        Map<String, Object> data2 = parseResourceFile(filePath2);

        System.out.println("Comparing files:");
        System.out.println("File 1: " + filePath1 + ", data: " + data1);
        System.out.println("File 2: " + filePath2 + ", data: " + data2);
        System.out.println("Format: " + format);

        return 0;
    }

    private Map<String, Object> parseResourceFile(String resourcePath) throws Exception {
        String content = readResourceFile(resourcePath);
        return parseJson(content);
    }

    private String readResourceFile(String resourcePath) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found in resources: " + resourcePath);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
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