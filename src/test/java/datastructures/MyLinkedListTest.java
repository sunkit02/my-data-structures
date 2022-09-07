package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MyLinkedListTest {

    MyLinkedList<Integer> underTest;

    @BeforeEach
    void setUp() {
        underTest = new MyLinkedList<>();
    }

    @Test
    void canAddAndGetElements() {
        // given
        List<Integer> testValues = List.of(1, 2, 3);
        testValues.forEach(num -> underTest.add(num));
        // when
        List<Integer> extractedValues = new ArrayList<>();
        for (int i = 0; i < testValues.size(); i++) {
            extractedValues.add(underTest.get(i));
        }
        // then
        assertThat(extractedValues).isEqualTo(testValues);
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
}