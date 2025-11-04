package config;

import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream is = TestConfig.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is != null) properties.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Cannot load config.properties", e);
        }
    }

    public static String baseUIUrl() {
        return properties.getProperty("baseUIUrl", "https://the-internet.herokuapp.com");
    }

    public static String baseAPIUrl() {
        return properties.getProperty("baseAPIUrl", "https://dummyjson.com");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(System.getProperty("headless", "true"));
    }
}
