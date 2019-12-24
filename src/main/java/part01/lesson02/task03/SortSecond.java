package part01.lesson02.task03;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Method sort of Collections
 *
 * @author Oleg_Chapurin
 */
public class SortSecond implements Sorting {

    @Override
    public void sort(ArrayList<Person> personArrayList) {
        Collections.sort(personArrayList);
    }
}
