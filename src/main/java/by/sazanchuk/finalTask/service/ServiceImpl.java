package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.dao.transaction.Transaction;
import by.sazanchuk.finalTask.dao.transaction.TransactionFactory;

/**
 * The type Service.
 */
abstract public class ServiceImpl implements Service {
    /**
     * The Transaction.
     */
    protected Transaction transaction = null;

    /**
     * Instantiates a new Service.
     *
     * @throws ServiceException the service exception
     */
    public ServiceImpl() throws ServiceException {
        try {
            transaction = TransactionFactory.getFactory().createTransaction();
        } catch (DaoException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Sets transaction.
     *
     * @param transaction the transaction
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
