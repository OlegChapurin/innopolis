package part01.lesson02.task03;

import java.util.ArrayList;

/**
 * Sorts array with measurement
 *
 * @author Oleg_Chapurin
 */
class ArraySortingBenchmarking {

    private boolean displays = true;

    /** Allows disables array output */
    void displaysListArray(boolean trueFalse) {
        displays = trueFalse;
    }

    /** Sorts array */
    void sort(ArrayList<Person> arrayPerson, ArrayList<Sorting> arraySorting) {
        String message = "\n------- Array size  " + arrayPerson.size() + "  elements ----------- \n";
        int size = arraySorting.size();
        for (int i = 0; i < size; i++) {
            message = message + "--- sort time N: " + (i + 1) + " ---- \n";
            long start = System.nanoTime();
            arraySorting.get(i).sort(arrayPerson);
            long end = System.nanoTime();
            message = message + (end - start) + "\n";
        }
        System.out.println("--------- array -----------");
        if (displays) {
            arrayPerson.forEach(System.out::println);
        }
        System.out.println(message);
    }
}
