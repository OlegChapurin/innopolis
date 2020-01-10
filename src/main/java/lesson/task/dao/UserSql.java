package lesson.task.dao;

import lesson.task.connection.ConnectionDb;
import lesson.task.entity.FactoryVisitor;
import lesson.task.entity.Visitor;
import lesson.task.entity.DateObject;
import lesson.task.entity.Telephone;
import lesson.task.logger.Log;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * DAO for object Visitor
 * @author Oleg_Chapurin
 */
public class UserSql<T,E> implements UserSqlRequest<Visitor,DateObject> {
    @Inject
    private ConnectionDb connectionDb;
    @Inject
    private FactorySqlUser factorySqlUser;
    @Inject
    private FactoryVisitor<Visitor,DateObject> factoryVisitor;
    @Inject
    private Log log;

    /**
     * @param rs ResultSet
     * @return HashMap with parameter values from the request
     * @throws SQLException
     */
    private HashMap<String,String> getParametersUser(ResultSet rs) throws SQLException {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("id",rs.getString("id"));
        hashMap.put("name",rs.getString("name"));
        hashMap.put("login",rs.getString("login"));
        hashMap.put("password",rs.getString("password"));
        hashMap.put("email",rs.getString("mail"));
        return hashMap;
    }

    /**
     * Creates an object with the given parameter values
     * @param hashMap HashMap key= "name parameter object" value= "value parameter object"
     * @return Object
     */
    private Visitor creatUser(HashMap<String,String> hashMap) {
        ArrayList<DateObject> telephones = allTelephoneByIdUser(Integer.valueOf(hashMap.get("id")));
        return factoryVisitor.getVisitor("Users",hashMap,telephones);
    }

    /**
     * Performs SQL request with two parameters
     * @param sql SQL
     * @param value1 parameter value
     * @param value2 parameter value
     * @return
     */
    private Integer InsertTwoValue(String sql, String value1, int value2){
        Integer amount = 0;
        try (Connection connection = connectionDb.getConnection();) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, value1);
            ps.setInt(2, value2);
            amount = ps.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return amount;
    }

    /**
     * Add value parameter telephone to object by login and password
     * @param login login
     * @param password password
     * @param telephones Array values parameter telephones
     * @return number of rows added
     */
    private int addTelephone(String login, String password, ArrayList<DateObject> telephones) {
        int amount = 0;
        if(telephones.size() > 0) {
            int id = getIdUser(login, password);
            String sql = factorySqlUser.getSqlAddTelephone();
            for (DateObject telephone:telephones) {
                amount += InsertTwoValue(sql, telephone.getObject(), id);
            }
        }
        return amount;
    }

    /**
     * Updates the value of a row field to id
     * @param value value
     * @param id id row
     * @param sql SQL request
     * @return number of rows update
     */
    private int updateTable(String value, int id, String sql){
        int amount = 0;
        try(Connection connection = connectionDb.getConnection();) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,value);
            ps.setInt(2,id);
            amount = ps.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return amount;
    }

    /**
     *
     * @param arrayList Array values parameter telephones
     * @param id id row
     * @param sqlUpdate SQL request Update
     * @param sqlDelete SQL request Delete
     * @param sqlInsert SQL request Insert
     * @return number of rows update
     */
    private int updateTable(ArrayList<DateObject> arrayList, int id, String sqlUpdate,
                            String sqlDelete, String sqlInsert){
        int amount = 0;
        try(Connection connection = connectionDb.getConnection();) {
            for (DateObject value:arrayList){
                if ((value.getStatusObject() > 0) && (value.getStatusObject() < 4)) {
                    PreparedStatement ps = null;
                    if (value.getStatusObject() == 1) {
                        ps = connection.prepareStatement(sqlUpdate);
                        ps.setString(1, value.getObject());
                        ps.setInt(2, value.getIdObject());
                    }
                    if (value.getStatusObject() == 2) {
                        ps = connection.prepareStatement(sqlDelete);
                        ps.setInt(1, value.getIdObject());
                    }
                    if (value.getStatusObject() == 3) {
                        ps = connection.prepareStatement(sqlInsert);
                        ps.setString(1, value.getObject());
                        ps.setInt(2, id);
                    }
                    amount += ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return amount;
    }

    /**
     * Performs SQL request with four parameters
     * @param sql SQL
     * @param value1 parameter value1
     * @param value2 parameter value2
     * @param value3 parameter value3
     * @param id id row
     * @return
     */
    private int updateTable(String value1, String value2, String value3, int id, String sql){
        int amount = 0;
        try(Connection connection = connectionDb.getConnection();) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,value1);
            ps.setString(2,value2);
            ps.setString(3,value3);
            ps.setInt(4,id);
            amount = ps.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return amount;
    }

    /**
     * Returns the object id from the database by login and password
     * @param login login object
     * @param password password object
     * @return id object from the database by login and password
     */
    @Override
    public int getIdUser(String login, String password){
        int id = 0;
        try (Connection connection = connectionDb.getConnection();) {
            PreparedStatement ps = connection.prepareStatement(factorySqlUser.getSqlUserId());
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = Integer.valueOf(rs.getString("id"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return id;
    }

    /**
     * Returns the object type T from the database by id
     * @param id - id object
     * @return object
     */
    @Override
    public Visitor getUser(int id) {
        Visitor user = null;
        HashMap<String,String> parameters = null;
        try(Connection connection = connectionDb.getConnection();) {
            PreparedStatement ps = connection.prepareStatement(factorySqlUser.getSqlUser());
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                parameters = getParametersUser(rs);
            }
            user = creatUser(parameters);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return user;
    }

    /**
     * Changes the value of object parameters
     * @param user mutable object
     * @return number of changed parameters
     */
    @Override
    public int editUser(Visitor user) {
        int amount = 0;
        if (user.getStatus()){
            amount += updateTable(user.getName(),user.getLogin(), user.getPassword(),
                    user.getId(),factorySqlUser.getSqlUpdateUser());
            amount += updateTable(user.getEmail(),user.getId(),factorySqlUser.getSqlUpdateMail());
            amount += updateTable(user.getTelephone(),user.getId(), factorySqlUser.getSqlUpdateTelephone(),
                    factorySqlUser.getSqlRemoveTelephoneId(), factorySqlUser.getSqlAddTelephone());
        }
        return amount;
    }

    /**
     * Registers an object
     * @param user object
     * @return true yes register, false not register
     */
    @Override
    public boolean registerUser(Visitor user) {
        boolean status = false;
        if(getIdUser(user.getLogin(),user.getPassword()) == 0) {
            try (Connection connection = connectionDb.getConnection();) {
                PreparedStatement ps = connection.prepareStatement(factorySqlUser.getSqlRegisterUser());
                ps.setString(1, user.getName());
                ps.setString(2, user.getLogin());
                ps.setString(3, user.getPassword());
                int amount = ps.executeUpdate();
                if (amount == 1) {
                    ArrayList<DateObject> telephones = user.getTelephone();
                    if (telephones.size() > 0) {
                        addTelephone(user.getLogin(), user.getPassword(), telephones);
                    }
                    String mail = user.getEmail();
                    if (mail.trim().length() > 0) {
                        addMail(user.getLogin(), user.getPassword(), mail);
                    }
                    status = true;
                }
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
        return status;
    }

    /**
     * Add value parameter telephone to object by login and password
     * @param login login object
     * @param password  password object
     * @param telephone value parameter telephone
     * @return true yes add, false not add
     */
    @Override
    public boolean addTelephone(String login, String password, String telephone) {
        int id = getIdUser(login, password);
        Integer amount = 0;
        boolean status = false;
        amount += InsertTwoValue(factorySqlUser.getSqlAddTelephone(), telephone, id);
        if (amount == 1) {
            status = true;
        }
        return status;
    }

    /**
     * Add value parameter mail to object by login and password
     * @param login login object
     * @param password  password object
     * @param mail value parameter mail
     * @return true yes add, false not add
     */
    @Override
    public boolean addMail(String login, String password, String mail) {
        int id = getIdUser(login, password);
        Integer amount = 0;
        boolean status = false;
        amount = InsertTwoValue(factorySqlUser.getSqlAddMail(), mail, id);
        if (amount == 1) {
            status = true;
        }
        return status;
    }

    /**
     *
     * @param idUser - id object
     * @return array all telephones
     */
    @Override
    public ArrayList<DateObject> allTelephoneByIdUser(int idUser) {
        ArrayList<DateObject> arrayList = new ArrayList<>();
        try(Connection connection = connectionDb.getConnection();) {
            PreparedStatement ps = connection.prepareStatement(factorySqlUser.getSqlAllTelephoneByIdUser());
            ps.setInt(1,idUser);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DateObject dateObject = new Telephone();
                dateObject.addEditIdObject(rs.getInt("id"));
                dateObject.addEditObject(rs.getString("telephone"));
                dateObject.addEditStatusObject(0);
                arrayList.add(dateObject);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return arrayList;
    }
}
