package by.sazanchuk.finalTask.dao.transaction;

import by.sazanchuk.finalTask.dao.Dao;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.PetDao;
import by.sazanchuk.finalTask.dao.ServiceDao;
import by.sazanchuk.finalTask.dao.UserDao;

/**
 * The interface Transaction.
 */
public interface Transaction {
    /**
     * Create dao type.
     *
     * @param <Type> the type parameter
     * @param key    the key
     * @return the type
     * @throws DaoException the dao exception
     */
    <Type extends Dao<?>> Type createDao(Class<Type> key) throws DaoException;

    /**
     * Makes connection commit.
     *
     * @throws DaoException the dao exception
     */
    void commit() throws DaoException;

    /**
     * Makes connection rollback.
     *
     * @throws DaoException the dao exception
     */
    void rollback() throws DaoException;
}
