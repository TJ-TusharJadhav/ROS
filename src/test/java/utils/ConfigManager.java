package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();
    private static String environment;

    static {
        try {
            FileInputStream input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);

            // First check system property (from Maven/CI), else default from config file
            environment = System.getProperty("env", properties.getProperty("environment"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file.", e);
        }
    }

    public static String getEnvironment() {
        return environment;
    }

    public static String getBaseUrl() {
        return properties.getProperty(environment + ".baseUrl");
    }

    public static String getLoginUrl() {
        return getBaseUrl() + "/login";
    }

    public static String getProtectedUrl() {
        return getBaseUrl() + "/users";
    }
}
