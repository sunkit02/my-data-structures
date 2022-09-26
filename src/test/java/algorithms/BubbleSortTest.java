package algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {

    private static class UnderTest {
        private int[] sort(int[] array) {
            return SortingAlgorithms.bubbleSort(array);
        }
    }

    private final UnderTest underTest = new UnderTest();



    @Test
    void canSortDistinctIntArray() {
        // given
        int[] array = {4, 6, 2, 3, 1, 0, 10};
        // when
        int[] sorted = underTest.sort(array);

        // then
        assertThat(sorted).isSorted();
    }

    @Test
    void canSortIntArrayWithDuplicates() {
        // given
        int[] array = {4, 2, 2, 3, 10, 1, 10};

        // when
        int[] sorted = underTest.sort(array);

        // then
        assertThat(sorted).isSorted();
    }

    @Test
    void canSortIntArrayWithOneElement() {
        // given
        int[] array = {1};

        // then
        assertDoesNotThrow(() -> underTest.sort(array));
    }

    @Test
    void canSortIntArrayWithNoElements() {
        // given
        int[] array = new int[0];

        // then
        assertDoesNotThrow(() -> underTest.sort(array));
    }

}