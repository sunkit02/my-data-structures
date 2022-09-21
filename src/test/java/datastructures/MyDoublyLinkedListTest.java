package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MyDoublyLinkedListTest {

    MyDoublyLinkedList<Integer> underTest;

    @BeforeEach
    void setUp() {
        underTest = new MyDoublyLinkedList<>();
    }

    @Test
    void canAddAndGetElements() {
        // given
        List<Integer> origVals = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        origVals.forEach(val -> underTest.add(val));

        // when
        testPointer(origVals, underTest); // test pointer
        List<Integer> extractedVals = extractValues(origVals);

        // then
        assertThat(extractedVals).isEqualTo(origVals);

        // display results
        System.out.println("Test: canAddAndGetElements");
        System.out.println("origVals: " + origVals);
        System.out.println("extractedVals: " + extractedVals);
        System.out.println();
    }

    // adding to the front by index
    @Test
    void canAddToFrontByIndex() {
        // given
        List<Integer> origVals = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        origVals.forEach(val -> underTest.add(val));
        int startIndex = 0;
        Integer newVal = 10;
        origVals.add(startIndex, newVal);
        underTest.add(startIndex, newVal);

        // when
        testPointer(origVals, underTest); // test pointer
        List<Integer> extractedVals = extractValues(origVals);

        // then
        assertThat(extractedVals).isEqualTo(origVals);

        // display end states
        System.out.println("Test: canAddToFrontByIndex");
        System.out.println("origVals: " + origVals);
        System.out.println("extractedVals: " + extractedVals);
        System.out.println();
    }

    // adding to the end by index
    @Test
    void canAddToEndByIndex() {
        // given
        List<Integer> origVals = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        origVals.forEach(val -> underTest.add(val));
        int endIndex = origVals.size();
        Integer newVal = 10;
        origVals.add(endIndex, newVal);
        underTest.add(endIndex, newVal);

        // when
        testPointer(origVals, underTest); // test pointer
        List<Integer> extractedVals = extractValues(origVals);

        // then
        assertThat(extractedVals).isEqualTo(origVals);

        // display end states
        System.out.println("Test: canAddToEndByIndex");
        System.out.println("origVals: " + origVals);
        System.out.println("extractedVals: " + extractedVals);
        System.out.println();
    }

    // adding to the middle by index
    @Test
    void canAddToMiddleByIndex() {
        // given
        List<Integer> origVals = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        origVals.forEach(val -> underTest.add(val));
        int middleIndex = origVals.size() / 2;
        Integer newVal = 10;
        origVals.add(middleIndex, newVal);
        underTest.add(middleIndex, newVal);

        // when
        testPointer(origVals, underTest); // test pointer
        List<Integer> extractedVals = extractValues(origVals);


        // then
        assertThat(extractedVals).isEqualTo(origVals);

        // display end states
        System.out.println("Test: canAddToMiddleByIndex");
        System.out.println("origVals: " + origVals);
        System.out.println("extractedVals: " + extractedVals);
        System.out.println();
    }

    // adding to an empty linked list by index
    @Test
    void canAddToEmptyListByIndex() {
        // given
        int origData = 1;
        List<Integer> origVals = new ArrayList<>();
        origVals.add(origData);
        // when
        underTest.add(0, origData);
        testPointer(origVals, underTest);
        List<Integer> extractedVals = extractValues(origVals);

        // then
        assertThat(extractedVals).isEqualTo(origVals);

        // display end states
        System.out.println("Test: canAddToEmptyListByIndex");
        System.out.println("origVals: " + origVals);
        System.out.println("extractedVals: " + extractedVals);
        System.out.println();
    }

    // removing the front by index
    @Test
    void canRemoveFromFrontByIndex() {
        // given
        List<Integer> origVals = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        origVals.forEach(val -> underTest.add(val));

        // when
        for (int i = 0; i < 5; i++) {
            // delete all the elements from the front
            underTest.remove(0);
            origVals.remove(0);
        }

        testPointer(origVals, underTest); // test pointer
        List<Integer> extractedVals = extractValues(origVals);

        // then
        assertThat(extractedVals).isEqualTo(origVals);

        // display end states
        System.out.println("Test: canRemoveFromFrontByIndex");
        System.out.println("origVals: " + origVals);
        System.out.println("extractedVals: " + extractedVals);
        System.out.println();
    }

    // removing the back by index
    @Test
    void canRemoveFromBackByIndex() {
        // given
        List<Integer> origVals = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        origVals.forEach(val -> underTest.add(val));

        // when
        for (int i = 0; i < 5; i++) {
            // delete all the elements from the back
            underTest.remove(underTest.size() - 1);
            origVals.remove(origVals.size() - 1);
        }

        testPointer(origVals, underTest); // test pointer
        List<Integer> extractedVals = extractValues(origVals);

        // then
        assertThat(extractedVals).isEqualTo(origVals);

        // display end states
        System.out.println("Test: canRemoveFromBackByIndex");
        System.out.println("origVals: " + origVals);
        System.out.println("extractedVals: " + extractedVals);
        System.out.println();
    }

    // removing the middle by index
    @Test
    void canRemoveFromMiddleByIndex() {
        // given
        List<Integer> origVals = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        origVals.forEach(val -> underTest.add(val));

        // when
        for (int i = 0; i < 3; i++) {
            // delete all three middle elements from the original list
            underTest.remove(1);
            origVals.remove(1);
        }

        testPointer(origVals, underTest); // test pointer
        List<Integer> extractedVals = extractValues(origVals);

        // then
        assertThat(extractedVals).isEqualTo(origVals);

        // display end states
        System.out.println("Test: canRemoveFromMiddleByIndex");
        System.out.println("origVals: " + origVals);
        System.out.println("extractedVals: " + extractedVals);
        System.out.println();
    }

    // TODO: can throw IndexOutOfBoundsException for add method
    // TODO: can throw IndexOutOfBoundsException for get method
    // TODO: can throw IndexOutOfBoundsException for remove method

    // management of the size field
    @Test
    void canGetSize() {
        // given
        List<Integer> vals = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        vals.forEach(val -> underTest.add(val));
        // add values then delete values to check if size method will
        // update as expected
        int numberOfValuesToAdd = 5;
        for (int i = 0; i < numberOfValuesToAdd; i++) {
            vals.add(i);
            underTest.add(i);
        }
        int expectedSizeAfterAdding = vals.size();
        int actualSizeAfterAdding = underTest.size();

        int numberOfValuesToRemove = 5;
        for (int i = 0; i < numberOfValuesToRemove; i++) {
            vals.remove(0);
            underTest.remove(0);
        }
        int expectedSizeAfterRemoving = vals.size();
        int actualSizeAfterRemoving = underTest.size();

        // then
        assertThat(actualSizeAfterAdding).isEqualTo(expectedSizeAfterAdding);
        assertThat(actualSizeAfterRemoving).isEqualTo(expectedSizeAfterRemoving);
    }
    private void testPointer(
            List<Integer> origVals,
            MyDoublyLinkedList<Integer> underTest) {
        // track original end of lists
        int origValsLastIndex = origVals.size();
        int underTestLastIndex = underTest.size();
        // add elements
        for (int i = 10; i < 20; i++) {
            origVals.add(i);
            underTest.add(i);
        }
        // remove half of the added elements
        for (int i = 0; i < 5; i++) {
            origVals.remove(origValsLastIndex);
            underTest.remove(underTestLastIndex);
        }
        // add five other elements
        for (int i = 100; i < 105; i++) {
            origVals.add(i);
            underTest.add(i);
        }

        // TODO: iterate through entire linked list (implement in future)
    }

    private List<Integer> extractValues(List<Integer> origVals) {
        List<Integer> extractedVals = new ArrayList<>();
        for (int i = 0; i < origVals.size(); i++) {
            extractedVals.add(underTest.get(i));
        }
        return extractedVals;
    }

}