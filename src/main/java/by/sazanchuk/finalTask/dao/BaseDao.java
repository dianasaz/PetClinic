package by.sazanchuk.finalTask.dao;

import java.sql.Connection;

/**
 * The type Base dao.
 */
abstract public class BaseDao {
    /**
     * The Connection.
     */
    protected Connection connection;

    /**
     * Sets connection.
     *
     * @param connection the connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
