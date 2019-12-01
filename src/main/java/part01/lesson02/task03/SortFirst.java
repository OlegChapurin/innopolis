package part01.lesson02.task03;

import java.util.ArrayList;

/**
 * Recursive sort
 *
 * @author Oleg_Chapurin
 */
public class SortFirst implements Sorting {

    /** transposition */
    private void swap(ArrayList<Person> personArrayList, int index1, int index2){
        if(index1 != index2) {
            Person temp;
            temp = personArrayList.get(index1);
            personArrayList.set(index1, personArrayList.get(index2));
            personArrayList.set(index2, temp);
        }
    }

    /** Break the range */
    private int partition(ArrayList<Person> personArrayList, int left, int right, int median){
        int leftPr = left -1;
        int rightPr = right;
        while(true) {
            while ((personArrayList.get(++leftPr).compareTo(personArrayList.get(median)) * -1) > 0) {
            }
            while ((rightPr > 0) && (personArrayList.get(median).compareTo(personArrayList.get(--rightPr)) * -1) > 0) {
            }
            if(leftPr >= rightPr){
                break;
            }else {
                swap(personArrayList, leftPr,rightPr);
            }
        }
        swap(personArrayList, leftPr,right);
        return leftPr;
    }

    /** Recursive sort */
    private void recSort(ArrayList<Person> personArrayList, int left, int right){
        if (right - left <= 0){
            return;
        }
        int median = right;
        int part = partition(personArrayList,left,right,median);
        recSort(personArrayList, left,part -1);
        recSort(personArrayList, part +1,right);
    }

    @Override
    public void sort(ArrayList<Person> personArrayList) {
        int size = personArrayList.size();
        if(size > 0) {
            recSort(personArrayList, 0, size - 1);
        }
    }
}
