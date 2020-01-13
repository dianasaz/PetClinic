package by.sazanchuk.finalTask.dao;

/**
 * The interface Dao.
 *
 * @param <T> the type parameter
 */
public interface Dao <T> {
    /**
     * Create integer.
     *
     * @param entity the entity
     * @return the integer
     * @throws DaoException the dao exception
     */
    Integer create(T entity) throws DaoException;

    /**
     * Read t.
     *
     * @param identity the identity
     * @return the t
     * @throws DaoException the dao exception
     */
    T read(Integer identity) throws DaoException;

    /**
     * Update.
     *
     * @param entity the entity
     * @throws DaoException the dao exception
     */
    void update(T entity) throws DaoException;

    /**
     * Delete.
     *
     * @param id the id
     * @throws DaoException the dao exception
     */
    void delete(Integer id) throws DaoException;
}
