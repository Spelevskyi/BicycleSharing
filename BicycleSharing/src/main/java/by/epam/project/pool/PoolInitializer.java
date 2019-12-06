package by.epam.project.pool;

import java.util.ResourceBundle;

public class PoolInitializer {
    // DB localhost url;
    final String URL;
    // DB user name value
    final String USER_NAME;
    // DB password value
    final String PASSWORD;
    // DB Driver for connection
    final String DRIVER;

    final String ENCODING;

    final String POOL_SIZE;

    final String USE_UNICODE;

    PoolInitializer() {
        ResourceBundle bundle = ResourceBundle.getBundle("properties.database");
        URL = bundle.getString("db.url");
        USER_NAME = bundle.getString("db.user");
        PASSWORD = bundle.getString("db.password");
        DRIVER = bundle.getString("db.driver");
        ENCODING = bundle.getString("db.encoding");
        POOL_SIZE = bundle.getString("db.poolsize");
        USE_UNICODE = bundle.getString("db.useUnicode");
    }
}
