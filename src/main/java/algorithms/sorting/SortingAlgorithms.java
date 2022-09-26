package algorithms.sorting;

/**
 * A middle class for classes from other package to call the
 * package-private sorting method call in one place
 */
public class SortingAlgorithms {
    public static int[] bubbleSort(int[] array) {
        int[] clonedArray = array.clone();
        BubbleSort.bubbleSort(clonedArray);
        return clonedArray;
    }
    public static int[] mergeSort(int[] array) {
        return MergeSort.mergeSort(array);
    }
}
