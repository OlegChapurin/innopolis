package lesson.task.entity;

/**
 * Data object
 * @author Oleg_Chapurin
 */
public interface DateObject {
    void addEditIdObject(int idTelephone);
    void addEditObject(String telephone);
    void addEditStatusObject(int statusTelephone);
    int getIdObject();
    String getObject();
    int getStatusObject();
}
