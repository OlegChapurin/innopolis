package part01.lesson02.task03;

import java.util.ArrayList;

/**
 * Redefine function contains
 *
 * @author Oleg_Chapurin
 */
public class MyArrayList<T> extends ArrayList<T> {

    public boolean contains(Object o){
        T temp = (T)o;
        boolean search = super.contains(temp);
        if(search) {
            try {
                throw new DuplicateObjectException();
            } catch (DuplicateObjectException e) {
                e.printStackTrace("Duplicate " + temp);
            }
        }
        return search;
    }
}
