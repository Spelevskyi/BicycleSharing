package by.epam.project.pool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.project.util.Constants;

public enum ConnectionPool {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private PoolInitializer initializer;
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenConnections;
    private static int poolSize;

    private ConnectionPool() {
        try {
            initializer = new PoolInitializer();
            Class.forName(initializer.DRIVER);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        init();
    }

    // Function of getting connection from connection pool
    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
            logger.info("Taken connection from pool.");
        } catch (InterruptedException ex) {
            logger.error(ex);
        }
        return connection;
    }

    // Function of connection pool initialization
    private void init() {
        Properties properties = new Properties();
        properties.put(Constants.DB_USER, initializer.USER_NAME);
        properties.put(Constants.DB_PASSWORD, initializer.PASSWORD);
        properties.put(Constants.DB_ENCODING, initializer.ENCODING);
        properties.put(Constants.DB_USE_UNICODE, initializer.USE_UNICODE);

        poolSize = Integer.parseInt(initializer.POOL_SIZE);

        freeConnections = new LinkedBlockingDeque<>(poolSize);
        givenConnections = new ArrayDeque<>();

        for (int i = 0; i < poolSize; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/bicycle_rental?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                        properties);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            freeConnections.add(proxyConnection);
        }
    }

    // Function of releasing one of taken connections and returning it into pool
    public void releaseConnection(ProxyConnection connection) {
        givenConnections.remove(connection);
        freeConnections.offer(connection);
        logger.info("Returning connection to free pool connections.");
    }

    // Function of destroying connection pool
    public void destroyPool() {
        for (int i = 0; i < poolSize; i++) {
            ProxyConnection connection = null;
            try {
                connection = freeConnections.take();
            } catch (InterruptedException ex) {
                logger.error(ex);
            } finally {
                connection.closeConnection();
            }
        }
        logger.info("Connections closed.");
        deregisterDrivers();
    }

    // Function of deregistration JDBC drivers
    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                java.sql.Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            } catch (SQLException ex) {
                logger.error(ex);
            }
        }
        logger.info("Deregistration JDBC drivers.");
    }

}
