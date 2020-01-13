package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.CouponDao;
import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Coupon;
import by.sazanchuk.finalTask.entity.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.Date;
import java.util.List;

/**
 * The type Coupon service.
 */
public class CouponServiceImpl extends ServiceImpl implements CouponService {
    /**
     * Instantiates a new Coupon service.
     *
     * @throws ServiceException the service exception
     */
    public CouponServiceImpl() throws ServiceException {
    }

    @Override
    public List<Coupon> findAll() throws ServiceException {
        try {
            CouponDao couponDao = transaction.createDao(CouponDao.class);
            return couponDao.read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Coupon findByIdentity(Integer identity) throws ServiceException {
        try {
            if (identity == null) throw new ServiceException();
            else {
                CouponDao couponDao = transaction.createDao(CouponDao.class);
                return couponDao.read(identity);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int save(Coupon coupon) throws ServiceException {
        try {
            if (coupon == null) throw new ServiceException();
            if (coupon.getTime() == null || coupon.getDoctor_id() == null || coupon.getPet_id() == null || coupon.getService_id() == null || coupon.getUser_id() == null) throw new ServiceException();
            else {
                CouponDao couponDao = transaction.createDao(CouponDao.class);
                if (coupon.getIdentity() == null) {
                    coupon.setIdentity(couponDao.create(coupon));
                } else {
                    couponDao.update(coupon);
                }
                return coupon.getIdentity();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer identity) throws ServiceException {
        try {
            CouponDao couponDao = transaction.createDao(CouponDao.class);
            if (identity == null) throw new ServiceException();
            else couponDao.delete(identity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isTaken(Integer doctor_id, Date date) throws ServiceException {
        try {
            if (doctor_id == null || date == null) throw new ServiceException();
            else {
                CouponDao couponDao = transaction.createDao(CouponDao.class);
                Coupon c = couponDao.isTaken(doctor_id, date);
                return c != null;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Coupon> getCouponsOfOneUser(int user_id) throws ServiceException {
        try {
            if (user_id == 0) throw new ServiceException();
            else {
                CouponDao couponDao = transaction.createDao(CouponDao.class);
                List<Coupon> coupons = couponDao.getCouponsOfOneUser(user_id);
                if (coupons.size() == 0) return null;
                else return coupons;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
