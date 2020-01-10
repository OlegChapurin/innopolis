package lesson.task.entity;

import lesson.task.logger.Log;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Oleg_Chapurin
 */
public class FactoryUser<T,E> implements FactoryVisitor<Visitor,DateObject> {
    private static FactoryVisitor<Visitor,DateObject> factoryVisitor;
    @Inject
    private Log log;

    public FactoryUser(){

    }

    public synchronized static FactoryVisitor getInstance(){
            if (factoryVisitor == null) {
                factoryVisitor = new FactoryUser();
            }
        return factoryVisitor;
    }

    /**
     * Sets the value of object parameters
     * @param user object type Visitor
     * @param hashMap HashMap key= "name parameter object" value= "value parameter object"
     * @param telephones Array values parameter
     */
    private void fillObject(Visitor user,HashMap<String,String> hashMap,ArrayList<DateObject> telephones){
        Class<? extends Visitor> cl = user.getClass();
        Field[] fields = cl.getDeclaredFields();
        int size = fields.length;
        for (int i = 0; i < size; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            String classType = field.getType().getName();
            if("java.lang.String".equals(classType)){
                String value = hashMap.get(field.getName());
                if (value != null) {
                    try {
                        field.set(user, value);
                    } catch (IllegalAccessException e) {
                        log.error(e.getMessage());
                    }
                }
            }
            if("int".equals(classType)){
                String value = hashMap.get(field.getName());
                if (value != null) {
                    try {
                        field.setInt(user, Integer.valueOf(value));
                    } catch (IllegalAccessException e) {
                        log.error(e.getMessage());
                    }
                }
            }
            if("java.util.ArrayList".equals(classType)){
                if (telephones.size()> 0) {
                    try {
                        field.set(user, telephones);
                    } catch (IllegalAccessException e) {
                        log.error(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * @param nameClass required object
     * @return  an object without prefilling
     */
    @Override
    public Visitor getVisitor(String nameClass) {
        switch (nameClass){
            case("Users"):
                return new User();
            default:
                return null;
        }
    }

    /**
     * @param nameClass  required object
     * @param hashMap HashMap key= "name parameter object" value= "value parameter object"
     * @param telephones Array values parameter telephones
     * @return a pre-populated object
     */
    @Override
    public Visitor getVisitor(String nameClass, HashMap<String,String> hashMap,ArrayList<DateObject> telephones) {
        Visitor user = getVisitor(nameClass);
        fillObject(user,hashMap,telephones);
        return user;
    }
}
