package by.sazanchuk.finalTask.dao;

import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Doctor dao.
 */
public class DoctorDao extends BaseDao implements Dao<Doctor> {
    private static final String INSERT_ALL_INFO = "INSERT INTO doctor ( `name`) VALUES (?)";
    private static final String SELECT_NAME = "SELECT `name` FROM doctor WHERE `id` = ?";
    private static final String SELECT_ID = "SELECT `id`, `name` FROM doctor WHERE LOCATE(?, `name`)";
    private static final String UPDATE_DOCTOR = "UPDATE doctor SET `name` = ? WHERE `id` = ?";
    private static final String DELETE_BY_IDENTITY = "DELETE FROM doctor WHERE `id` = ?";
    private static final String SELECT_ALL_INFO_ORDER_BY_NAME = "SELECT `id`, `name` FROM doctor ORDER BY `name`";
    private static final String INSERT_ALL_INFO_INTO_DOCTOR_SERVICE = "INSERT INTO doctor_service (`doctor_id`, `service_id`) VALUES (?, ?)";
    private static final String SEARCH_REFERENCE = "SELECT `doctor_id`, `service_id` FROM doctor_service WHERE `service_id` = ? AND `doctor_id` = ?";
    private static final String DELETE_DS = "DELETE FROM doctor_service WHERE `doctor_id` = ?";

    private final Logger log = LogManager.getLogger(DoctorDao.class);

     @Override
    public Integer create(Doctor entity) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(INSERT_ALL_INFO, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
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

    /**
     * Create ds.
     *
     * @param entity  the entity
     * @param service the service
     * @throws DaoException the dao exception
     */
    public void createDS(Integer entity, Integer service) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(INSERT_ALL_INFO_INTO_DOCTOR_SERVICE, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity);
            statement.setInt(2, service);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
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
     * Delete ds.
     *
     * @param doctor the doctor
     * @throws DaoException the dao exception
     */
    public void deleteDS(Integer doctor) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_DS);
            statement.setInt(1, doctor);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch(SQLException e) {}
        }
    }

    @Override
    public Doctor read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_NAME);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Doctor doctor = null;
            if (resultSet.next()) {
                doctor = new Doctor();
                doctor.setIdentity(id);
                doctor.setName(resultSet.getString("name"));
            }
            return doctor;
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
     * Read by name doctor.
     *
     * @param name the name
     * @return the doctor
     * @throws DaoException the dao exception
     */
    public List<Doctor> readByName(String name) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ID);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            List<Doctor> doctors = new ArrayList<>();
            Doctor doctor = null;
            while (resultSet.next()) {
                doctor = new Doctor();
                doctor.setName(resultSet.getString("name"));
                doctor.setIdentity(resultSet.getInt("id"));
                doctors.add(doctor);
            }
            return doctors;
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
    public void update(Doctor entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_DOCTOR);
            statement.setInt(2, entity.getIdentity());
            statement.setString(1, entity.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch(SQLException e) {}
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
    public List<Doctor> read() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL_INFO_ORDER_BY_NAME);
            resultSet = statement.executeQuery();
            List<Doctor> doc = new ArrayList<>();
            Doctor doctor = null;
            while(resultSet.next()) {
                doctor = new Doctor();
                doctor.setIdentity(resultSet.getInt("id"));
                doctor.setName(resultSet.getString("name"));
                doc.add(doctor);
            }
            return doc;
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

