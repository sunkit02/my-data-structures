package algorithms.sorting;

import algorithms.sorting.SortingAlgorithms;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {

    private static class UnderTest {
        private Integer[] sort(Integer[] array) {
            return SortingAlgorithms.bubbleSort(array);
        }
    }

    private final UnderTest underTest = new UnderTest();



    @Test
    void canSortDistinctIntArray() {
        // given
        Integer[] array = {4, 6, 2, 3, 1, 0, 10};
        // when
        Integer[] sorted = underTest.sort(array);

        // then
        assertThat(sorted).isSorted();
    }

    @Test
    void canSortIntArrayWithDuplicates() {
        // given
        Integer[] array = {4, 2, 2, 3, 10, 1, 10};

        // when
        Integer[] sorted = underTest.sort(array);

        // then
        assertThat(sorted).isSorted();
    }

    @Test
    void canSortIntArrayWithOneElement() {
        // given
        Integer[] array = {1};

        // then
        assertDoesNotThrow(() -> underTest.sort(array));
    }

    @Test
    void canSortIntArrayWithNoElements() {
        // given
        Integer[] array = new Integer[0];

        // then
        assertDoesNotThrow(() -> underTest.sort(array));
    }

}