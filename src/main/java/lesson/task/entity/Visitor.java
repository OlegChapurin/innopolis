package lesson.task.entity;

import java.util.ArrayList;

/**
 * @author Oleg_Chapurin
 */
public interface Visitor {
    /**
     * @param status - true modified, false not modified
     */
    void setStatus(boolean status);
    void setName(String name);
    void setLogin(String login);
    void setPassword(String password);
    void setEmail(String email);
    void setTelephone(ArrayList<DateObject> telephone);
    void setTelephone(String telephone);
    int getId();
    boolean getStatus();
    String getName();
    String getLogin();
    String getPassword();
    String getEmail();
    ArrayList<DateObject> getTelephone();
    void deleteTelephone(int id);
    void markDeleteTelephone(int index);
}
