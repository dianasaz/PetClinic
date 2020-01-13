package by.sazanchuk.finalTask.dao.connectionPool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Custom realization of ConnectionPool
 */
public class ConnectionPool {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    /** The Constant PROPERTY_PATH. */
    private static final String PROPERTY_PATH = "myDatabase.properties";

    /** The Constant INITIAL_CAPACITY. */
    private static final int INITIAL_CAPACITY = 20;

    /** The free connections. */
    private ArrayBlockingQueue<Connection> freeConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);

    /** The release connections. */
    private ArrayBlockingQueue<Connection> releaseConnections = new ArrayBlockingQueue<>(INITIAL_CAPACITY);

    /** The lock. */
    private static ReentrantLock lock = new ReentrantLock();

    /** The connection pool. */
    private volatile static ConnectionPool connectionPool;

    /**
     * Gets the single instance of ConnectionPool.
     *
     * @return single instance of ConnectionPool
     */
    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            try {
                lock.lock();
                if (connectionPool == null) {
                    connectionPool = new ConnectionPool();
                }
            } catch (ConnectionPoolException e) {
                LOGGER.error("Can not get Instance", e);
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        return connectionPool;
    }

    /**
     * Instantiates a new connection pool.
     *
     * @throws ConnectionPoolException the Connection Pool exception
     */
    private ConnectionPool() throws ConnectionPoolException {
        try {
            lock.lock();
            if (connectionPool != null) {
                throw new UnsupportedOperationException();
            } else {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Can't register driver");
            throw new ConnectionPoolException(e + "Cant' register driver");
        } finally {
            lock.unlock();
        }
        init();
    }

    /**
     * Inits the.
     */
    private void init() throws ConnectionPoolException {

        Properties properties = new Properties();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(PROPERTY_PATH);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Error while reading properties", e);
            throw new ConnectionPoolException(e);
        }
        String connectionURL = properties.getProperty("url");
        String initialCapacityString = properties.getProperty("poolSize");
        Integer initialCapacity = Integer.valueOf(initialCapacityString);

        for (int i = 0; i < initialCapacity; i++) {
            try {
                Connection connection = new PoolConnection(DriverManager.getConnection(connectionURL, properties)) {
                };
                freeConnections.add(connection);
            } catch (SQLException e) {
                LOGGER.error("Pool can not initialize", e);
                throw new RuntimeException("Pool can not initialize", e);
            }
        }

    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public Connection getConnection() throws ConnectionPoolException {
        try {
            Connection connection = freeConnections.take();
            releaseConnections.offer(connection);

            return connection;
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Can not get database", e);
        }

    }

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public void releaseConnection(Connection connection) {
        releaseConnections.remove(connection);
        freeConnections.offer(connection);

    }

    /**
     * Destroy.
     */
    public void destroy() {
        freeConnections.addAll(releaseConnections);
        releaseConnections.clear();

        for (int i = 0; i < freeConnections.size(); i++) {
            try {
                PoolConnection connection = (PoolConnection) freeConnections.take();
                connection.realClose();
            } catch (InterruptedException e) {
                LOGGER.error("Connection close exception", e);
            }
        }


        Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            java.sql.Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                LOGGER.log(Level.INFO, String.format("Deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, String.format("Error deregistering driver %s", driver), e);
            }
        }

    }

}
