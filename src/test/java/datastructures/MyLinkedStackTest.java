package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MyLinkedStackTest {

    private MyLinkedStack<Integer> underTest;

    @BeforeEach
    void setUp() {
        underTest = new MyLinkedStack<>();
    }

    @Test
    void canPushAndPop() {
        // given
        List<Integer> origValues = List.of(1, 2, 3, 4, 5, 6);
        origValues.forEach(underTest::push);

        // when
        List<Integer> extractedValues = new ArrayList<>();
        int size = underTest.size();
        for (int i = 0; i < size; i++) {
            extractedValues.add(underTest.pop());
        }

        // then
        assertThat(extractedValues).isEqualTo(reverse(origValues));
    }

    @Test
    void canPushAndPopAlternating() {
        // given
        List<Integer> origValues = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> expectedValues = new ArrayList<>();

        for (Integer element : origValues) {
            underTest.push(element);
            expectedValues.add(0, element);
        }

        for (int i = 0; i < origValues.size(); i++) {
            underTest.pop();
            expectedValues.remove(0);
        }

        assert underTest.isEmpty() && expectedValues.isEmpty();

        for (Integer origValue : origValues) {
            underTest.push(origValue);
            expectedValues.add(0, origValue);
        }


        // when
        List<Integer> extractedValues = new ArrayList<>();
        int size = underTest.size();
        for (int i = 0; i < size; i++) {
            extractedValues.add(underTest.pop());
        }

        // then
        System.out.println(expectedValues);
        System.out.println(extractedValues);
        assertThat(extractedValues).isEqualTo(expectedValues);
    }


    private <E> List<E> reverse(List<E> list) {
        List<E> newList = new ArrayList<>(list);
        int n = newList.size();
        for (int i = 0, j = (n - 1); i < (n >> 1); i++, j--) {
            E temp = list.get(i);
            newList.set(i, list.get(j));
            newList.set(j, temp);
        }

        return newList;
    }
}