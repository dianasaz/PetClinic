package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.PetDao;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Pet service.
 */
public class PetServiceImpl extends ServiceImpl implements PetService {


    /**
     * Instantiates a new Pet service.
     *
     * @throws ServiceException the service exception
     */
    public PetServiceImpl() throws ServiceException {
    }

    @Override
    public List<Pet> findAll() throws ServiceException {
        PetDao petDao = null;
        try {
            petDao = transaction.createDao(PetDao.class);
            return petDao.read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public Pet findByIdentity(Integer identity) throws ServiceException {
        PetDao petDao = null;
        try {
            if (identity == null) throw new ServiceException();
            else {
                petDao = transaction.createDao(PetDao.class);
                return petDao.read(identity);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public int save(Pet pet) throws ServiceException {
        try {
            if (pet == null) throw new ServiceException();
            else {
                PetDao petDao = transaction.createDao(PetDao.class);
                if (pet.getIdentity() == null) {
                    pet.setIdentity(petDao.create(pet));
                } else {
                    petDao.update(pet);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return pet.getIdentity();
    }

    @Override
    public void delete(Integer identity) throws ServiceException {
        try {
            PetDao petDao = transaction.createDao(PetDao.class);
            if (identity == null) {
                throw new ServiceException();
            } else petDao.delete(identity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Pet> getPetsOfOneUser(Integer userId) throws ServiceException {
        try {
            if (userId == null) throw new ServiceException();
            else {
                PetDao petDao = transaction.createDao(PetDao.class);
                List<Pet> pets = petDao.readPetsWithOneUser(userId);
                List<Pet> petList = new ArrayList<>();
                for (Pet pet : pets) {
                    Pet p = new Pet();
                    p = petDao.read(pet.getIdentity());
                    petList.add(p);
                }
                return petList;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Pet findByNameAndUserId(String name, Integer user_id) throws ServiceException {
        try {
            if (name == null || user_id == null || name.isEmpty()) throw new ServiceException();
            else {
                PetDao petDao = transaction.createDao(PetDao.class);
                Pet pet = null;
                pet = petDao.read(name, user_id);
                return pet;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
