package by.sazanchuk.finalTask.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.dao.transaction.Transaction;
import by.sazanchuk.finalTask.dao.transaction.TransactionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Service factory.
 */
public class ServiceFactory{
   // private static Logger logger = LogManager.getLogger(ServiceFactory.class);

    private TransactionFactory factory;

    private static final Map<Class<? extends Service>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>();

    static {
        SERVICES.put(UserService.class, UserServiceImpl.class);
        SERVICES.put(PetService.class, PetServiceImpl.class);
        SERVICES.put(ServiceService.class, ServiceServiceImpl.class);
        SERVICES.put(DoctorService.class, DoctorServiceImpl.class);
        SERVICES.put(CouponService.class, CouponServiceImpl.class);
    }

    /**
     * Instantiates a new Service factory.
     *
     * @throws ServiceException the service exception
     */
    public ServiceFactory() throws ServiceException {
        try {
            factory = TransactionFactory.getFactory();
        } catch (ConnectionPoolException | DaoException e) {
            throw new ServiceException(e);
        }
    }


    /**
     * Gets service.
     *
     * @param <Type> the type parameter
     * @param key    the key
     * @return the service
     * @throws ServiceException the service exception
     */
    public <Type extends Service> Type getService(Class<Type> key) throws ServiceException {
        Class<? extends ServiceImpl> value = SERVICES.get(key);
        if(value != null) {
            try {
                ClassLoader classLoader = value.getClassLoader();
                Class<?>[] interfaces = {key};
                factory.createTransaction();
                ServiceImpl service = value.newInstance();
                InvocationHandler handler = new ServiceInvocationHandlerImpl(service);
                return (Type)Proxy.newProxyInstance(classLoader, interfaces, handler);
            } catch(DaoException e) {
                throw new ServiceException(e);
            } catch(InstantiationException | IllegalAccessException e) {
                //logger.error("It is impossible to instance by.sazanchuk.finalTask.service class", e);
                throw new ServiceException(e);
            }
        }
        return null;
    }

    /**
     * Close.
     */
    public void close() {
        factory.close();
    }
}