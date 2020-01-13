package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.ServiceDao;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Service;

import java.util.List;

/**
 * The type Service service.
 */
public class ServiceServiceImpl extends ServiceImpl implements ServiceService {


    /**
     * Instantiates a new Service service.
     *
     * @throws ServiceException the service exception
     */
    public ServiceServiceImpl() throws ServiceException {
    }

    @Override
    public List<by.sazanchuk.finalTask.entity.Service> findAll() throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            serviceDao = transaction.createDao(ServiceDao.class);
            return serviceDao.read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Service> searchWithOneDoctor(Doctor doctor) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            if (doctor == null) throw new ServiceException();
            else {
                serviceDao = transaction.createDao(ServiceDao.class);
                return serviceDao.searchWithOneDoctor(doctor);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public by.sazanchuk.finalTask.entity.Service findByIdentity(Integer identity) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            serviceDao = transaction.createDao(ServiceDao.class);
            Service s = null;
            if (identity != null) {
                s = serviceDao.read(identity);
            } else throw new ServiceException();
            return s;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public int save(by.sazanchuk.finalTask.entity.Service service) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            if (service == null) throw new ServiceException();
            else {
                serviceDao = transaction.createDao(ServiceDao.class);
                if (service.getIdentity() != null) {
                    service.setName(service.getName());
                    service.setPrice(service.getPrice());
                } else {
                    service.setIdentity(serviceDao.create(service));
                }
                serviceDao.update(service);
                return service.getIdentity();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void delete(Integer identity) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            serviceDao = transaction.createDao(ServiceDao.class);
            if (identity != null) serviceDao.delete(identity);
            else throw new ServiceException();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean searchService(String name) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            if (name == null) throw new ServiceException();
            else {
                serviceDao = transaction.createDao(ServiceDao.class);
                return serviceDao.searchServiceByName(name) != null;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public Service searchServiceByName(String name) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            if (name == null) throw new ServiceException();
            else {
                serviceDao = transaction.createDao(ServiceDao.class);
                return serviceDao.searchServiceByName(name);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    public List<Service> searchServiceByPrice(Integer price) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            if (price == null) throw new ServiceException();
            else {
                serviceDao = transaction.createDao(ServiceDao.class);
                return serviceDao.searchServiceByPrice(price);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Service> searchServiceByPartOfName(String name) throws ServiceException {
        ServiceDao serviceDao = null;
        try {
            if (name == null) throw new ServiceException();
            else {
                serviceDao = transaction.createDao(ServiceDao.class);
                return serviceDao.readByName(name);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
