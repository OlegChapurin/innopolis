package lesson.task.connection;

import lesson.task.logger.Log;
import org.apache.commons.dbcp.BasicDataSource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Creates connection pool
 * @author Oleg_Chapurin
 */
@ApplicationScoped
public class PoolConnections implements ConnectionDb {
    @Inject
    private Log log;
    private static PoolConnections poolConnections;
    private static BasicDataSource bds;

    private PoolConnections() {
    }

    public synchronized static ConnectionDb getInstance(){
        if(poolConnections == null){
            poolConnections = new PoolConnections();
        }
        return poolConnections;
    }

    /**
     * Creates connection pool
     * @param driver - driver database
     * @param url - url database
     * @param user - user database
     * @param password - password database
     * @param numberConnections number of connections in pool
     */
    @Override
    public void instancePoolConnection(String driver,String url,String user,
                                       String password,String numberConnections){
        try {
            Class.forName(driver);
            bds = new BasicDataSource();
            bds.setUrl(url);
            bds.setUsername(user);
            bds.setPassword(password);
            bds.setMaxIdle(Integer.valueOf(numberConnections));
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }
    }

    /**
     *Set pool connections
     * @param bds pool connections
     */
    @Override
    public void setConnection(BasicDataSource bds) {
        this.bds = bds;
    }

    /**
     * @return connection database of pool connections
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
            return this.bds.getConnection();
    }

    /**
     * Close pool connections
     */
    @Override
    public void Close() {
        try {
            this.bds.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
