//package config;
//
//public class EnvironmentConfig {
//
//}

package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentConfig {

    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream file =
                    new FileInputStream("src/test/resources/config.properties");

            properties.load(file);
            file.close();

        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    public static String getUrl() {

        String environment =
                System.getProperty("environment", "qa");

        String url =
                properties.getProperty(environment + ".url");

        if (url == null || url.isEmpty()) {
            throw new RuntimeException(
                    "URL not configured for environment: " + environment
            );
        }

        return url;
    }
}