package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Coupon;

import java.util.Date;
import java.util.List;

/**
 * The interface Coupon service.
 */
public interface CouponService extends Service {

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Coupon> findAll() throws ServiceException;

    /**
     * Find by identity coupon.
     *
     * @param identity the identity
     * @return the coupon
     * @throws ServiceException the service exception
     */
    Coupon findByIdentity(Integer identity) throws ServiceException;

    /**
     * Save int.
     *
     * @param coupon the coupon
     * @return the int
     * @throws ServiceException the service exception
     */
    int save(Coupon coupon) throws ServiceException;

    /**
     * Delete.
     *
     * @param identity the identity
     * @throws ServiceException the service exception
     */
    void delete(Integer identity) throws ServiceException;

    /**
     * Is taken boolean.
     *
     * @param doctor_id the doctor id
     * @param date      the date
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isTaken(Integer doctor_id, Date date) throws ServiceException;

    /**
     * Gets coupons of one user.
     *
     * @param user_id the user id
     * @return the coupons of one user
     * @throws ServiceException the service exception
     */
    List<Coupon> getCouponsOfOneUser(int user_id) throws ServiceException;
}
