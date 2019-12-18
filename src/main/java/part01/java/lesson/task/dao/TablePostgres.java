package lesson.task.dao;

import lesson.task.connection.ConnectionJdbc;
import lesson.task.connection.ConnectionManagerPostgres;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Oleg_Chapurin
 */
public class TablePostgres implements TableDdl {

    private static final Logger logger = LogManager.getLogger(TablePostgres.class);
    private ConnectionJdbc connectionJdbc =
            ConnectionManagerPostgres.getInstance();
    private Connection connection;
    private SqlDdlPostgres factorySQL = new SqlDdlPostgres();

    /**
     * @param url  Database URL
     * @param user Database login
     * @param password  Database password
     */
    public TablePostgres(String url, String user, String password) {
        this.connection = connectionJdbc.getConnection(url, user, password);
    }

    /**
     *
     * @param sql sql
     * @return int quantity rows
     */
    private int executeSQL(String sql) {
        try (
                PreparedStatement ps = connection.prepareStatement(sql);) {
            int execUp = ps.executeUpdate();
            ps.close();
            logger.info("Method executeSQL: Execute sql = ".concat(sql));
            return execUp;
        } catch (SQLException e){
                logger.error(e);
        }
        return 0;
    }

    /**
     * check availability table
     * @param nameTable name table
     * @return boolean
     */
    private boolean isTable(String nameTable){
        boolean table = false;
        try {
            String sql = factorySQL.isTable();
            PreparedStatement ps = connection.prepareStatement(sql);
            factorySQL.setValues(ps, nameTable);
            ResultSet rs = ps.executeQuery();
            if (rs.getRow() > 0) {
                table = true;
            }
            ps.close();
            logger.info("Method Execute sql = ".concat(sql));
        } catch (SQLException e) {
            logger.error(e);
        }
        return table;
    }

    /**
     * Close connection JDBC
     */
    @Override
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Method closeConnection: Close connection JDBC");
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    /**
     * @param nameTable name table
     * @return boolean
     */
    @Override
    public boolean creatTable(String nameTable) {
        String sql = factorySQL.getCREATE_TABLE(nameTable);
        if (sql != null) {
            executeSQL(sql);
            return true;
        }
        return false;
    }

    /**
     * @param nameTable name table
     * @return boolean
     */
    @Override
    public boolean deleteTable(String nameTable) {
        if(isTable(nameTable)) {
            String sql = factorySQL.getDELETE_TABLE(nameTable);
            if (sql != null) {
                executeSQL(sql);
                return true;
            }
        }
        return false;
    }
}
