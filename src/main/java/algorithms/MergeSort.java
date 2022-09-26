package algorithms;

public class MergeSort {
    static int[] mergeSort(int[] array) {
        int length = array.length;
        if (length <= 1) return array;

        int mid = length / 2;

        // partition
        int[] left = copyArray(array, 0, mid);
        int[] right = copyArray(array, mid, length);

        int[] leftSorted = mergeSort(left);
        int[] rightSorted = mergeSort(right);
        int[] mergeSorted = new int[length];

        // merge
        int i = 0, j = 0, k = 0;
        while (i < leftSorted.length && j < rightSorted.length) {
            if (leftSorted[i] < rightSorted[j]) {
                mergeSorted[k] = leftSorted[i];
                i++;
            } else {
                mergeSorted[k] = rightSorted[j];
                j++;
            }
            k++;
        }

        while (i < leftSorted.length) {
            mergeSorted[k] = leftSorted[i];
            i++;
            k++;
        }
        while (j < rightSorted.length) {
            mergeSorted[k] = rightSorted[j];
            j++;
            k++;
        }

        // return
        return mergeSorted;
    }

    @SuppressWarnings("all")
    private static int[] copyArray(int[] array, int start, int end) {
        int[] newArray = new int[end - start];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[start + i];
        }
        return newArray;
    }
}
