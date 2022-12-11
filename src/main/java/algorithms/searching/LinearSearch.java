package algorithms.searching;

public class LinearSearch {
    public static boolean search(int[] array, int value) {
        for (int item : array) {
            if (item == value) return true;
        }
        return false;
    }
}
