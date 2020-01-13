package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Pet;

import java.util.List;

/**
 * The interface Pet service.
 */
public interface PetService  extends Service{

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Pet> findAll() throws ServiceException;

    /**
     * Find by identity by . sazanchuk . final task . entity . pet.
     *
     * @param identity the identity
     * @return the by . sazanchuk . final task . entity . pet
     * @throws ServiceException the service exception
     */
    by.sazanchuk.finalTask.entity.Pet findByIdentity(Integer identity) throws ServiceException;

    /**
     * Save int.
     *
     * @param service the service
     * @return the int
     * @throws ServiceException the service exception
     */
    int save(by.sazanchuk.finalTask.entity.Pet service) throws ServiceException;

    /**
     * Delete.
     *
     * @param identity the identity
     * @throws ServiceException the service exception
     */
    void delete(Integer identity) throws ServiceException;

    /**
     * Gets pets of one user.
     *
     * @param userId the user id
     * @return the pets of one user
     * @throws ServiceException the service exception
     */
    List<Pet> getPetsOfOneUser(Integer userId) throws ServiceException;

    /**
     * Find by name and user id pet.
     *
     * @param name    the name
     * @param user_id the user id
     * @return the pet
     * @throws ServiceException the service exception
     */
    Pet findByNameAndUserId(String name, Integer user_id) throws ServiceException;
}
