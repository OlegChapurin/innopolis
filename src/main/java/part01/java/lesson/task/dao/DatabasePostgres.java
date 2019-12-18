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
public class DatabasePostgres implements Database {

    private static final Logger logger = LogManager.getLogger(DatabasePostgres.class);
    private ConnectionJdbc connectionJdbc =
            ConnectionManagerPostgres.getInstance();
    private Connection connection;
    private SqlDdlPostgres SqlDdl = new SqlDdlPostgres();

    /**
     * @param url  Database URL
     * @param user Database login
     * @param password  Database password
     */
    public DatabasePostgres(String url,String user, String password){
        this.connection = connectionJdbc.getConnection(url, user, password);
    }

    /**
     * Close connection JDBC
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Method closeConnection: Close connection");
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    /**
     *
     * @param nameDatabase name database
     * @return Database status YES,NOT,ERROR
     */
    protected DatabaseStatus isDatabase(String nameDatabase) {
        DatabaseStatus status = DatabaseStatus.ERROR;
        String SqlDatabaseStatus = SqlDdl.getSqlDatabaseStatus();
        try (PreparedStatement ps = connection.prepareStatement(SqlDatabaseStatus);) {
            ps.setString(1, nameDatabase);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (String.valueOf(rs.getArray(1)).equals("t")) {
                status = DatabaseStatus.YES;
            } else {
                status = DatabaseStatus.NOT;
            }
            logger.info("Method isDatabase: Database ".concat(nameDatabase).concat("status ").concat(status.name()));
        } catch (SQLException e) {
            logger.error(e);
        }
        return status;
    }

    /**
     *
     * @param nameDatabase name database
     * @return boolean creat database true false
     */
    @Override
    public boolean creatDatabase(String nameDatabase) {
        if(DatabaseStatus.NOT == isDatabase(nameDatabase)) {
            String SqlDatabaseCreate = SqlDdl.getSqlDatabaseCreate();
            SqlDatabaseCreate = SqlDatabaseCreate.replace("?", nameDatabase);
            try (PreparedStatement ps = connection.prepareStatement(SqlDatabaseCreate);) {
                ps.executeUpdate();
                logger.info("Method creatDatabase: Database ".concat(nameDatabase).concat(" creat"));
                return true;
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return false;
    }
}
