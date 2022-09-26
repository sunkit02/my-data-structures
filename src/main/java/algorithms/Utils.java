package algorithms;

public class Utils {
    public static boolean isSortedAsc(int[] array) {
        for (int i = 0, j = 1; j < array.length; i++, j++) {
            if (array[i] > array[j]) return false;
        }
        return true;
    }
}
