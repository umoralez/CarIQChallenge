package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EndpointUtils {
    private static final String ENDPOINTS_FILE = "endpoint.properties";
    private static final Properties endpoints = loadEndpoints();

    private static Properties loadEndpoints() {
        Properties props = new Properties();
        try (InputStream input = EndpointUtils.class.getClassLoader().getResourceAsStream(ENDPOINTS_FILE)) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static String getEndpoint(String key) {
        return endpoints.getProperty(key);
    }
}
