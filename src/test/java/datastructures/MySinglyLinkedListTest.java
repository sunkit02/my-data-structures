package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MySinglyLinkedListTest {

    MySinglyLinkedList<Integer> underTest;

    @BeforeEach
    void setUp() {
        underTest = new MySinglyLinkedList<>();
    }

    @Test
    void canAddAndGetElements() {
        // given
        List<Integer> origVals = List.of(1, 2, 3);
        origVals.forEach(val -> underTest.add(val));
        // when
        List<Integer> extractedValues = new ArrayList<>();
        for (int i = 0; i < origVals.size(); i++) {
            extractedValues.add(underTest.get(i));
        }
        // then
        assertThat(extractedValues).isEqualTo(origVals);
    }

    @Test
    void canAddElement() {

    }

    @Test
    @Disabled
    void canAddElementToEmptyLinkedList() {

    }

    @Test
    @Disabled
    void canAddElementAtHeadByIndex() {

    }

    @Test
    @Disabled
    void canAddElementAtTailByIndex() {

    }

    @Test
    void canGetIsEmpty() {
        // given
        boolean isEmptyBefore = underTest.isEmpty();
        underTest.add(1);
        boolean isNotEmptyAfter = underTest.isEmpty();
        // then
        assertThat(isEmptyBefore).isTrue();
        assertThat(isNotEmptyAfter).isFalse();
    }

    @Test
    void canAddElementInMiddleByIndex() {
        // given
        int testValue = 10;
        int testIndex = 3;
        List<Integer> testValues = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        testValues.forEach(num -> underTest.add(num));
        testValues.add(testIndex, testValue);
        underTest.add(testIndex, testValue);
        // when
        List<Integer> extractedValues = new ArrayList<>();
        for (int i = 0; i < testValues.size(); i++) {
            extractedValues.add(underTest.get(i));
        }
        Integer extractedValue = underTest.get(testIndex);
        // then
        assertThat(extractedValues).isEqualTo(testValues);
        assertThat(extractedValue).isEqualTo(testValue);
    }

    @Test
    void canRemoveFromEndThenAddToEnd() {
        // given
        Integer endValue = 4;
        List<Integer> testValues = new ArrayList<>(List.of(1, 2, 3));
        testValues.forEach(num -> underTest.add(num));
        // when
        underTest.remove(underTest.size() - 1); // remove last element
        underTest.add(endValue);

        List<Integer> extractedValues = new ArrayList<>(); // extract all values in MyLinkedList to a list
        for (int i = 0; i < testValues.size(); i++) {
            extractedValues.add(underTest.get(i));
        }

        testValues.remove(testValues.size() - 1); // also remove last element from testValues
        testValues.add(endValue); // add endValue to testValues

        // then
        assertThat(extractedValues).isEqualTo(testValues);
    }

    @Test
    void canThrowIndexOutOfBoundsExceptionWhenAdding() {
        // given
        List<Integer> values = List.of(1, 2, 3, 4, 5);
        values.forEach(val -> underTest.add(val));

        // when
        int maxIndex = underTest.size();
        int minIndex = 0;
        Integer randomElement = 6;

        // then
        assertThrows(IndexOutOfBoundsException.class,
                () -> underTest.add(maxIndex + 1, randomElement)
        );
        assertThrows(IndexOutOfBoundsException.class,
                () -> underTest.add(minIndex - 1, randomElement)
        );
    }

    @Test
    void canNotThrowIndexOutOfBoundsExceptionWhenAdding() {
        // given
        List<Integer> values = List.of(1, 2, 3, 4, 5);
        values.forEach(val -> underTest.add(val));

        // when
        int maxIndex = underTest.size();
        int minIndex = 0;
        Integer randomElement = 6;

        // then

        assertDoesNotThrow(() -> underTest.add(maxIndex, randomElement)
        );
        assertDoesNotThrow(() -> underTest.add(minIndex, randomElement));
    }

    @Test
    void canThrowIndexOutOfBoundsExceptionWhenGetting() {
        // given
        List<Integer> values = List.of(1, 2, 3, 4, 5);
        values.forEach(val -> underTest.add(val));

        // when
        int maxIndex = underTest.size() - 1;
        int minIndex = 0;

        // then
        assertThrows(IndexOutOfBoundsException.class,
                () -> underTest.get(maxIndex + 1)
        );
        assertThrows(IndexOutOfBoundsException.class,
                () -> underTest.get(minIndex - 1)
        );
    }

    @Test
    void canNotThrowIndexOutOfBoundsExceptionWhenGetting() {
        // given
        List<Integer> values = List.of(1, 2, 3, 4, 5);
        values.forEach(val -> underTest.add(val));

        // when
        int maxIndex = underTest.size() - 1;
        int minIndex = 0;

        // then

        assertDoesNotThrow(() -> underTest.get(maxIndex)
        );
        assertDoesNotThrow(() -> underTest.get(minIndex));
    }

    @Test
    void canThrowIndexOutOfBoundsExceptionWhenRemoving() {
        // given
        List<Integer> values = List.of(1, 2, 3, 4, 5);
        values.forEach(val -> underTest.add(val));

        // when
        int maxIndex = underTest.size() - 1;
        int minIndex = 0;

        // then
        assertThrows(IndexOutOfBoundsException.class,
                () -> underTest.remove(maxIndex + 1)
        );
        assertThrows(IndexOutOfBoundsException.class,
                () -> underTest.remove(minIndex - 1)
        );
    }

    @Test
    void canNotThrowIndexOutOfBoundsExceptionWhenRemoving() {
        // given
        List<Integer> values = List.of(1, 2, 3, 4, 5);
        values.forEach(val -> underTest.add(val));

        // when
        int maxIndex = underTest.size() - 1;
        int minIndex = 0;

        // then
        assertDoesNotThrow(() -> underTest.remove(maxIndex)
        );
        assertDoesNotThrow(() -> underTest.remove(minIndex)
        );
    }
}