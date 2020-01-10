package lesson.task.dao;

import javax.enterprise.context.ApplicationScoped;

/**
 * Stores prepared templates SQL for object visitor
 * @author Oleg_Chapurin
 */
@ApplicationScoped
public class FactorySql implements FactorySqlUser {
    private String USER = "SELECT u.id,u.name, u.login,u.password, (m.email) AS mail " +
            " FROM public.user AS u\n" +
            "  LEFT JOIN public.mail AS m ON   u.id = m.user_id\n" +
            " WHERE u.id = ? ;";
    private String ALL_USERS = "SELECT u.id,u.name, u.login,u.password," +
            " (m.email) AS mail, (t.telephone) AS telephone  FROM public.user AS u\n" +
            " LEFT JOIN public.mail AS m ON   u.id = m.user_id\n" +
            " LEFT JOIN public.telephone AS t ON u.id = t.user_id;";
    private String USER_ID = "SELECT u.id FROM " +
            "public.user AS u WHERE login = ? AND password = ?;";
    private String REGISTER_USER = "INSERT INTO public.user (name, login, password) VALUES (?,?,?);";
    private String REGISTER_MAIL = "INSERT INTO public.mail (email, user_id) VALUES (?,?);";
    private String REGISTER_TELEPHONE = "INSERT INTO public.telephone (telephone, user_id) VALUES (?,?);";
    private String ALL_TELEPHONE = "SELECT t.telephone, (u.name) AS name FROM public.telephone AS t\n" +
            "                               LEFT JOIN public.user AS u ON   t.user_id = u.id;";
    private String ALL_TELEPHONE_BY_ID_USER = "SELECT t.id, t.telephone FROM public.telephone AS t\n" +
            "                               WHERE t.user_id = ?;";
    private String DELETE_TELEPHONE = "DELETE FROM public.telephone WHERE telephone = ?;";
    private String DELETE_TELEPHONE_ID = "DELETE FROM public.telephone WHERE id = ?;";
    private String DELETE_TELEPHONE_USER = "DELETE FROM public.telephone WHERE user_id = ?;";
    private String UPDATE_USER = "UPDATE public.user SET name = ?, login = ?, password = ? WHERE id = ?;";
    private String UPDATE_MAIL = "UPDATE public.mail SET email = ? WHERE user_id = ?;";
    private String UPDATE_TELEPHONE = "UPDATE public.telephone SET telephone = ? WHERE id = ?;";
    private static FactorySql factorySql;

    private FactorySql(){

    }

    public synchronized static FactorySql getInstance(){
        if(factorySql == null){
            factorySql = new FactorySql();
        }
        return factorySql;
    }

    @Override
    public String getSqlUser() {
        return USER;
    }

    @Override
    public String getSqlUserId() {
        return USER_ID;
    }

    @Override
    public String getAllUsers() {
        return ALL_USERS;
    }

    @Override
    public String getSqlRegisterUser() {
        return REGISTER_USER;
    }

    @Override
    public String getSqlAddMail() {
        return REGISTER_MAIL;
    }

    @Override
    public String getSqlAddTelephone() {
        return REGISTER_TELEPHONE;
    }

    @Override
    public String getSqlUpdateUser() {
        return UPDATE_USER;
    }

    @Override
    public String getSqlUpdateMail() {
        return UPDATE_MAIL;
    }

    @Override
    public String getSqlUpdateTelephone() {
        return UPDATE_TELEPHONE;
    }

    @Override
    public String getSqlAllTelephone() {
        return ALL_TELEPHONE;
    }

    @Override
    public String getSqlAllTelephoneByIdUser() {
        return ALL_TELEPHONE_BY_ID_USER;
    }

    @Override
    public String getSqlRemoveTelephone() {
        return DELETE_TELEPHONE;
    }

    @Override
    public String getSqlRemoveTelephoneId() {
        return DELETE_TELEPHONE_ID;
    }

    @Override
    public String getSqlRemoveTelephoneUser() {
        return DELETE_TELEPHONE_USER;
    }
}
