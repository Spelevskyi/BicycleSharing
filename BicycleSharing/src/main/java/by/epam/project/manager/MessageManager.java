package by.epam.project.manager;

import java.util.ResourceBundle;

public class MessageManager {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("resources.message");

    public MessageManager() {
    }

    public static String getProperty(String key) {
        return bundle.getString(key);
    }
}
