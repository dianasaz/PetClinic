package by.sazanchuk.finalTask.dao.transaction;

import by.sazanchuk.finalTask.dao.BaseDao;
import by.sazanchuk.finalTask.dao.Dao;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.PetDao;
import by.sazanchuk.finalTask.dao.ServiceDao;
import by.sazanchuk.finalTask.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The type Transaction.
 */
public class TransactionImpl implements Transaction {
    private static Logger logger = LogManager.getLogger(TransactionImpl.class);

    private Connection connection;

    /**
     * Instantiates a new Transaction.
     *
     * @param connection the connection
     */
    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <Type extends Dao<?>> Type createDao(Class<Type> key) throws DaoException {
        Class<? extends BaseDao> value = (Class<? extends BaseDao>) key;
        if (value != null) {
            try {
                BaseDao dao = value.newInstance();
                dao.setConnection(connection);
                return (Type) dao;
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("It is impossible to create data access object", e);
                throw new DaoException(e);
            }
        }
        return null;
    }

    @Override
    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("It is impossible to commit transaction", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("It is impossible to rollback transaction", e);
            throw new DaoException(e);
        }
    }
}


