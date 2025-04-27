package hexlet.code;

import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        TreeSet<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        return allKeys.stream()
                .flatMap(key -> {
                    Object value1 = data1.get(key);
                    Object value2 = data2.get(key);

                    if (!data1.containsKey(key)) {
                        return Stream.of("  + " + key + ": " + value2);
                    }else if (!data2.containsKey(key)) {
                        return Stream.of("  - " + key + ": " + value1);
                    } else if (!value1.equals(value2)) {
                        return Stream.of(
                                "  - " + key + ": " + value1,
                                "  + " + key + ": " + value2
                        );
                    } else {
                        return Stream.of("    " + key + ": " + value1);
                    }
                })
                .collect(Collectors.joining("\n", "{\n", "\n}"));

    }
}
