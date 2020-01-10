package lesson.task.entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Oleg_Chapurin
 */
public interface FactoryVisitor<T,E> {
    /**
     * @param nameClass required object
     * @return  an object without prefilling
     */
    T getVisitor(String nameClass);

    /**
     * @param nameClass  required object
     * @param hashMap HashMap key= "name parameter object" value= "value parameter object"
     * @param value Array values parameter
     * @return a pre-populated object
     */
    T getVisitor(String nameClass, HashMap<String,String> hashMap, ArrayList<E> value);
}
