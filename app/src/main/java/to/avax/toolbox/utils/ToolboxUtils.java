package to.avax.toolbox.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ToolboxUtils {
    public static String readResourceAsString(String resourcePath) {
        try (var schemaIS = ToolboxUtils.class.getResourceAsStream(resourcePath)) {
            if (schemaIS == null) {
                throw new RuntimeException("Cannot open resource " + resourcePath);
            }
            return new String(schemaIS.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return null;
        }
    }
}
