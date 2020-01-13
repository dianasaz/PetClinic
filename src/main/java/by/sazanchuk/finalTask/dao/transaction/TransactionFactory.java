package by.sazanchuk.finalTask.dao.transaction;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPool;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The type Transaction factory.
 */
public class TransactionFactory {
    private static Logger logger = LogManager.getLogger(TransactionFactory.class);
    private static TransactionFactory factory;
    private Connection connection;

    /**
     * Gets factory.
     *
     * @return the factory
     * @throws ConnectionPoolException the connection pool exception
     * @throws DaoException            the dao exception
     */
    public static TransactionFactory getFactory() throws ConnectionPoolException, DaoException {
        if (factory == null){
            factory = new TransactionFactory();
        }
        return factory;
    }

    private TransactionFactory() throws DaoException, ConnectionPoolException {
        connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch(SQLException e) {
            logger.error("It is impossible to turn off autocommiting for database connection", e);
            throw new DaoException(e);
        }
    }

    /**
     * Create transaction transaction.
     *
     * @return the transaction
     * @throws DaoException the dao exception
     */
    public Transaction createTransaction() throws DaoException {
        return new TransactionImpl(connection);
    }

    /**
     * Close.
     */
    public void close() {
        try {
            connection.close();
        } catch(SQLException e) {}
    }
}