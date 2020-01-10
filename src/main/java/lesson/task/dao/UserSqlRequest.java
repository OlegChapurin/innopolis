package lesson.task.dao;

import java.util.ArrayList;

/**
 * DAO for object type T
 * @author Oleg_Chapurin
 */
public interface UserSqlRequest<T,E> {
    /**
     * Returns the object id from the database by login and password
     * @param login login object
     * @param password password object
     * @return id object from the database by login and password
     */
    int getIdUser(String login, String password);

    /**
     * Returns the object type T from the database by id
     * @param id - id object
     * @return object
     */
    T getUser(int id);

    /**
     * Changes the value of object parameters
     * @param user mutable object
     * @return number of changed parameters
     */
    int editUser(T user);

    /**
     * Registers an object
     * @param user object
     * @return true yes register, false not register
     */
    boolean registerUser(T user);

    /**
     * Add value parameter telephone to object by login and password
     * @param login login object
     * @param password  password object
     * @param telephone value parameter telephone
     * @return true yes add, false not add
     */
    boolean addTelephone(String login,String password,String telephone);

    /**
     * Add value parameter mail to object by login and password
     * @param login login object
     * @param password  password object
     * @param mail value parameter mail
     * @return true yes add, false not add
     */
    boolean addMail(String login,String password,String mail);

    /**
     * @param idUser - id object
     * @return array all telephones
     */
    ArrayList<E> allTelephoneByIdUser(int idUser);
}
