package algorithms.searching;

public class BinarySearch {
    public static boolean search(int[] array, int value) {
        return search(array, value, 0, array.length);
    }

    private static boolean search(int[] array, int value, int start, int end) {
        int mid = (start + end) / 2;
        if (array[mid] == value) return true;

        if (start == end) return false;

        if (array[mid] < value) {
            return search(array, value, start, mid);
        }

        return search(array, value, mid, end);

    }
}
