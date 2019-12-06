package by.epam.project.manager;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final ResourceBundle bundle = ResourceBundle
            .getBundle("C:\\Users\\Ilya\\eclipse-workspace\\BicycleSharing\\src\\main\\resources\\config.properties");

    public ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return bundle.getString(key);
    }
}
