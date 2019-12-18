package lesson.task.pojo;

import java.math.BigDecimal;

/**
 * @author Oleg_Chapurin
 */
public interface Users {
    public String getName();
    public String getBirthday();
    public long getLogin_id();
    public String getCity();
    public String getEmail();
    public String getDescription();
    public Role getRole();
    public void setLogin_id(long login_id);
    public void setBirthday(String birthday);
    public void setCity(String city);
    public void setEmail(String email);
    public void setDescription(String description);
    public void setRole(Role role);
}
