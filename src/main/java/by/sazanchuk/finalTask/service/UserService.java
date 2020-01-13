package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.User;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService extends Service{
    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAll() throws ServiceException;

    /**
     * Find by identity user.
     *
     * @param identity the identity
     * @return the user
     * @throws ServiceException the service exception
     */
    User findByIdentity(Integer identity) throws ServiceException;

    /**
     * Find by login and password user.
     *
     * @param login    the login
     * @param password the password
     * @return the user
     * @throws ServiceException the service exception
     */
    User findByLoginAndPassword(String login, String password) throws ServiceException;

    /**
     * Save int.
     *
     * @param user the user
     * @return the int
     * @throws ServiceException the service exception
     */
    int save(User user) throws ServiceException;

    /**
     * Delete.
     *
     * @param identity the identity
     * @throws ServiceException the service exception
     */
    void delete(Integer identity) throws ServiceException;

    /**
     * Is exist boolean.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isExist(String login) throws ServiceException;

    /**
     * Search email boolean.
     *
     * @param email the email
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean searchEmail(String email) throws ServiceException;
}
