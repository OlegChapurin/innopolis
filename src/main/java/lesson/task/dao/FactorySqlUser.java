package lesson.task.dao;

/**
 * Stores prepared templates SQL for object visitor
 * @author Oleg_Chapurin
 */
public interface FactorySqlUser {
    String getSqlUser();
    String getSqlUserId();
    String getAllUsers();
    String getSqlRegisterUser();
    String getSqlAddMail();
    String getSqlAddTelephone();
    String getSqlUpdateUser();
    String getSqlUpdateMail();
    String getSqlUpdateTelephone();
    String getSqlAllTelephone();
    String getSqlAllTelephoneByIdUser();
    String getSqlRemoveTelephone();
    String getSqlRemoveTelephoneId();
    String getSqlRemoveTelephoneUser();
}
