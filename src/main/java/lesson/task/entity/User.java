package lesson.task.entity;

import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Oleg_Chapurin
 */
@Named
public class User implements Visitor, Users, Serializable {
    /**
     * id - new, from database
     */
    private int id;
    /**
     * status - true modified, false not modified
     */
    private boolean status = false;
    private String name;
    private String login;
    private String password;
    private String email;
    private ArrayList<DateObject> telephone = new ArrayList<>();

    private void setTelephone(String telephone,int statusTelephone) {
        if (telephone != null) {
            DateObject dateObject = new Telephone();
            dateObject.addEditObject(telephone);
            dateObject.addEditStatusObject(statusTelephone);
            this.telephone.add(dateObject);
        }
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setTelephone(ArrayList<DateObject> telephone) {
        if (telephone != null) {
            this.telephone = telephone;
        }
    }

    @Override
    public void setTelephone(String telephone) {
        if (telephone != null) {
            setTelephone(telephone,3);
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean getStatus() {
        return this.status;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLogin() {
        return this.login;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public ArrayList<DateObject> getTelephone() {
        return this.telephone;
    }

    @Override
    public void deleteTelephone(int index) {
        this.telephone.remove(index);
    }

    @Override
    public void markDeleteTelephone(int index) {
        this.telephone.get(index).addEditStatusObject(2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(telephone, user.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email, telephone);
    }
}
