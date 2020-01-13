package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Doctor;

import javax.print.Doc;
import java.util.List;

/**
 * The interface Doctor service.
 */
public interface DoctorService extends Service{
    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Doctor> findAll() throws ServiceException;

    /**
     * Find by identity doctor.
     *
     * @param identity the identity
     * @return the doctor
     * @throws ServiceException the service exception
     */
    Doctor findByIdentity(Integer identity) throws ServiceException;

    /**
     * Find by name doctor.
     *
     * @param name the name
     * @return the doctor
     * @throws ServiceException the service exception
     */
    List<Doctor> findByName(String name) throws ServiceException;

    /**
     * Save int.
     *
     * @param doctor the doctor
     * @return the int
     * @throws ServiceException the service exception
     */
    int save(Doctor doctor) throws ServiceException;

    /**
     * Delete references.
     *
     * @param doctor the doctor
     * @throws ServiceException the service exception
     */
    void deleteReferences(Doctor doctor) throws ServiceException;

    /**
     * Save.
     *
     * @param doctor  the doctor
     * @param service the service
     * @throws ServiceException the service exception
     */
    void save(Doctor doctor, by.sazanchuk.finalTask.entity.Service service) throws ServiceException;

    /**
     * Delete.
     *
     * @param identity the identity
     * @throws ServiceException the service exception
     */
    void delete(Integer identity) throws ServiceException;
}
