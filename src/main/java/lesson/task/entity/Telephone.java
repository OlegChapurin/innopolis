package lesson.task.entity;


/**
 * Stores phone data
 * @author Oleg_Chapurin
 */
public class Telephone implements DateObject {
    /**
     * index 0 - stores id phone from database
     * index 1 - stores value
     * index 3 - new, modified, not modified value
     */
    private Object[] cells = new Object[3];

    @Override
    public void addEditIdObject(int idTelephone){
        cells[0] = idTelephone;
    }

    @Override
    public void addEditObject(String telephone){
        cells[1] = telephone;
    }

    @Override
    public void addEditStatusObject(int statusTelephone){
        cells[2] = statusTelephone;
    }

    @Override
    public int getIdObject(){
        return (int) cells[0];
    }

    @Override
    public String getObject(){
        return (String) cells[1];
    }

    @Override
    public int getStatusObject(){
        return (int) cells[2];
    }
}
