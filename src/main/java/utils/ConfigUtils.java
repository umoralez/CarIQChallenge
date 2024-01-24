package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigUtils {
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = loadProperties();

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = ConfigUtils.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static int getApiTimeout() {
        return Integer.parseInt(properties.getProperty("api.timeout"));
    }

    public static String readJsonFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
