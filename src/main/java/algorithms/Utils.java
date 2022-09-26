package algorithms;

public class Utils {
    public static boolean isSortedAsc(int[] array) {
        for (int i = 0, j = 1; j < array.length; i++, j++) {
            if (array[i] > array[j]) return false;
        }
        return true;
    }

    /**
     * Returns the time it takes to run a runnable in milliseconds
     * @param runnable runnable to be timed
     * @return the time it takes to run the runnable
     */
    public static long timeRunnable(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - start;
    }
}
