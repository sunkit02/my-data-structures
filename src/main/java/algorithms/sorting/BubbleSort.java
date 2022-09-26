package algorithms.sorting;

public class BubbleSort {
    static void bubbleSort(int[] array) {
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
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
