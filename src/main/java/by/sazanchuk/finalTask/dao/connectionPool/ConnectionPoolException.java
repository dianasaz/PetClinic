package by.sazanchuk.finalTask.dao.connectionPool;

/**
 * the type of connection pool exception
 */
public class ConnectionPoolException  extends Exception {
    /**
     * Instantiates a new Connection pool exception
     */
    public ConnectionPoolException() {}

    /**
     * Instantiates a new Connection pool exception
     *
     * @param message message
     * @param cause cause
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Connection pool exception
     *
     * @param message message
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Connection pool exception
     *
     * @param cause cause
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}