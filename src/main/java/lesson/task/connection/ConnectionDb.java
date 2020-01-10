package lesson.task.connection;

import org.apache.commons.dbcp.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Oleg_Chapurin
 */
public interface ConnectionDb {
    /**
     * Creates a connection pool
     * @param driver - driver database
     * @param url - url database
     * @param user - user database
     * @param password - password database
     * @param numberConnections number of connections in pool
     */
    void instancePoolConnection(String driver,String url,String user, String password,String numberConnections);

    /**
     *Set pool connections
     * @param bds pool connections
     */
    void setConnection(BasicDataSource bds);

    /**
     * @return connection database of pool connections
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;

    /**
     * Close pool connections
     */
    void Close();
}
