package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Doctor;

import java.util.List;

/**
 * The interface Service service.
 */
public interface ServiceService extends Service {
    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<by.sazanchuk.finalTask.entity.Service> findAll() throws ServiceException;

    /**
     * Find by identity by . sazanchuk . final task . entity . service.
     *
     * @param identity the identity
     * @return the by . sazanchuk . final task . entity . service
     * @throws ServiceException the service exception
     */
    by.sazanchuk.finalTask.entity.Service findByIdentity(Integer identity) throws ServiceException;

    /**
     * Save int.
     *
     * @param service the service
     * @return the int
     * @throws ServiceException the service exception
     */
    int save(by.sazanchuk.finalTask.entity.Service service) throws ServiceException;

    /**
     * Delete.
     *
     * @param identity the identity
     * @throws ServiceException the service exception
     */
    void delete(Integer identity) throws ServiceException;

    /**
     * Search service boolean.
     *
     * @param name the name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean searchService(String name) throws ServiceException;

    /**
     * Search with one doctor list.
     *
     * @param doctor the doctor
     * @return the list
     * @throws ServiceException the service exception
     */
    List<by.sazanchuk.finalTask.entity.Service> searchWithOneDoctor(Doctor doctor) throws ServiceException;

    List<by.sazanchuk.finalTask.entity.Service> searchServiceByPrice(Integer price) throws ServiceException;
    List<by.sazanchuk.finalTask.entity.Service> searchServiceByPartOfName(String name) throws ServiceException;

    /**
     * Search service by name by . sazanchuk . final task . entity . service.
     *
     * @param name the name
     * @return the by . sazanchuk . final task . entity . service
     * @throws ServiceException the service exception
     */
    by.sazanchuk.finalTask.entity.Service searchServiceByName(String name) throws ServiceException;

}
