package algorithms.sorting;

import java.util.Arrays;

public class BubbleSort {
    static void bubbleSort(Integer[] array) {
        int i;
        int j;

        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            i = 0;
            j = 1;
            for (; j < array.length; i++, j++) {
                if (array[i] > array[j]) {
                    isSorted = false;
                    swap(array, i, j);
                }
            }
        }
    }

    static <E extends Comparable<E>> E[] bubbleSort(E[] array) {
        E[] arrayClone = array.clone();
        for (int position = array.length - 1; position >= 0; position--) {

            // a flag that would indicate whether any action has been taken
            // to swap the elements in the current run of the array
            boolean isSorted = true;
            System.out.println(Arrays.toString(arrayClone));

            for (int scan = 0; scan <= position - 1; scan++) {
                if (arrayClone[scan].compareTo(arrayClone[scan + 1]) > 0) {
                    swap(arrayClone, scan, scan + 1);

                    // if a swap action happens, the flag will be changed accordingly
                    isSorted = false;
                }
            }

            // early return the sorted array if the flag indicates
            // no action has been taken to swap elements in the array
            if (isSorted) return arrayClone;
        }
        return arrayClone;
    }

    private static <E> void swap(E[] array, int index1, int index2) {
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
