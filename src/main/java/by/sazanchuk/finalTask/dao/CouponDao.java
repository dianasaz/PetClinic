package by.sazanchuk.finalTask.dao;


import by.sazanchuk.finalTask.entity.Coupon;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Coupon dao.
 */
public class CouponDao extends BaseDao implements Dao<Coupon> {
    private static final String INSERT_ALL_INFO = "INSERT INTO coupon (`user_id`, `doctor_id`, `time`, `pet_id`, `service_id`) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_NAME = "SELECT `user_id`, `doctor_id`, `time`, `pet_id`, `service_id` FROM coupon WHERE `id` = ?";
    private static final String UPDATE_DOCTOR = "UPDATE coupon SET `user_id` = ?, `doctor_id` = ?, `time` = ?, `pet_id` = ?, `service_id` = ? WHERE `id` = ?";
    private static final String DELETE_BY_IDENTITY = "DELETE FROM coupon WHERE `id` = ?";
    private static final String SELECT_ALL_INFO_ORDER_BY_NAME = "SELECT `id`, `user_id`,`doctor_id`, `time`, `pet_id`, `service_id` FROM coupon ORDER BY `user_id`";
    private static final String IS_EXIST = "SELECT `id` FROM coupon WHERE `time` = ? && `doctor_id` = ?";
    private static final String SELECT_COUPONS_BY_USER = "SELECT `id`, `doctor_id`, `time`, `pet_id`, `service_id` FROM coupon WHERE `user_id` = ?";

    private final Logger log = LogManager.getLogger(DoctorDao.class);
@Override
    public Integer create(Coupon entity) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(INSERT_ALL_INFO, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(2, entity.getDoctor_id());
            statement.setTimestamp(3, new Timestamp(entity.getTime().getTime()));
            statement.setInt(1, entity.getUser_id());
            statement.setInt(5, entity.getService_id());
            statement.setInt(4, entity.getPet_id());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                log.error("There is no autoincremented index after trying to add record into table `users`");
                throw new DaoException();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch(SQLException e) {}
            try {
                if (statement != null) statement.close();
            } catch(SQLException e) {}
        }
    }

    @Override
    public Coupon read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_NAME);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Coupon coupon = null;
            if (resultSet.next()) {
                coupon = new Coupon();
                coupon.setIdentity(id);
                coupon.setDoctor_id(resultSet.getInt("doctor_id"));
                coupon.setUser_id(resultSet.getInt("user_id"));
                coupon.setTime(resultSet.getTimestamp("time"));
                coupon.setPet_id(resultSet.getInt("pet_id"));
                coupon.setService_id(resultSet.getInt("service_id"));
            }
            return coupon;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch(SQLException e) {}
            try {
                if (statement != null) statement.close();
            } catch(SQLException e) {}
        }
    }

    /**
     * Gets coupons of one user.
     *
     * @param user_id the user id
     * @return the coupons of one user
     * @throws DaoException the dao exception
     */
    public List<Coupon> getCouponsOfOneUser(int user_id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_COUPONS_BY_USER);
            statement.setInt(1, user_id);
            resultSet = statement.executeQuery();
            List<Coupon> coupons = new ArrayList<>();
            Coupon coupon = null;
            while (resultSet.next()) {
                coupon = new Coupon();
                coupon.setIdentity(resultSet.getInt("id"));
                coupon.setTime(resultSet.getTimestamp("time"));
                coupon.setService_id(resultSet.getInt("service_id"));
                coupon.setDoctor_id(resultSet.getInt("doctor_id"));
                coupon.setPet_id(resultSet.getInt("pet_id"));
                coupons.add(coupon);
            }
            return coupons;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch(SQLException e) {}
            try {
                if (statement != null) statement.close();
            } catch(SQLException e) {}
        }
    }

    /**
     * Is taken coupon.
     *
     * @param doctor_id the doctor id
     * @param date      the date
     * @return the coupon
     * @throws DaoException the dao exception
     */
    public Coupon isTaken(Integer doctor_id, Date date) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(IS_EXIST);
            statement.setTimestamp(1, new Timestamp(date.getTime()));
            statement.setInt(2, doctor_id);
            resultSet = statement.executeQuery();
            Coupon coupon = null;
            if (resultSet.next()) {
                coupon = new Coupon();
                coupon.setIdentity(resultSet.getInt("id"));
            }
            return coupon;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch(SQLException e) {}
            try {
                if (statement != null) statement.close();
            } catch(SQLException e) {}
        }
    }

    @Override
    public void update(Coupon entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_DOCTOR);
            statement.setInt(3, entity.getIdentity());
            statement.setInt(1, entity.getUser_id());
            statement.setInt(2, entity.getDoctor_id());
            statement.setTimestamp(3, new Timestamp(entity.getTime().getTime()));
            statement.setInt(4, entity.getPet_id());
            statement.setInt(5, entity.getService_id());
            statement.setInt(6, entity.getIdentity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_BY_IDENTITY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch(SQLException e) {}
        }
    }

    /**
     * Read list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    public List<Coupon> read() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL_INFO_ORDER_BY_NAME);
            resultSet = statement.executeQuery();
            List<Coupon> cou = new ArrayList<>();
            Coupon coupon = null;
            while(resultSet.next()) {
                coupon = new Coupon();
                coupon.setIdentity(resultSet.getInt("id"));
                coupon.setDoctor_id(resultSet.getInt("doctor_id"));
                coupon.setUser_id(resultSet.getInt("user_id"));
                coupon.setTime(resultSet.getTimestamp("time"));
                coupon.setPet_id(resultSet.getInt("pet_id"));
                coupon.setService_id(resultSet.getInt("service_id"));
                cou.add(coupon);
            }
            return cou;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch(SQLException e) {}
            try {
                if (statement != null) statement.close();
            } catch(SQLException e) {}
        }
    }

}

