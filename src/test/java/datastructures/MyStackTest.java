package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyStackTest {

    private MyStack<Integer> underTest;

    @BeforeEach
    void setUp() {
        underTest = new MyStack<>();
    }

    @Test
    void canPushAndPop() {
        // given
        List<Integer> origValues =
                new ArrayList<>(List.of(1, 2, 3, 4, 5));

        int numOfElements = origValues.size();
        origValues.forEach(underTest::push);

        // when
        ArrayList<Integer> extractedValues =
                new ArrayList<>(numOfElements);

        for (int i = 0; i < numOfElements; i++) {
            extractedValues.add(underTest.pop());
        }

        // then
        assertThat(extractedValues).isEqualTo(reverse(origValues));
    }

    @Test
    void canPeekTopElement() {
        // given
        List<Integer> values = List.of(0, 1, 2, 3, 4, 5);
        int size = values.size();
        values.forEach(underTest::push);

        // then
        for (int i = (size - 1); i >= 0; i--) {
            // every i corresponds to an element in values
            // i decrementing because stack reversed the
            // values
            assertThat(underTest.peek()).isEqualTo(i);
            underTest.pop();
        }
    }

    @Test
    void canGetSize() {
        //then

        // push and pop entire
        for (int i = 0; i < 10; i++) {
            assertThat(underTest.size()).isEqualTo(i);
            underTest.push(i);
            assertThat(underTest.size()).isEqualTo(i + 1);
        }

        for (int i = 10; i > 0 ; i--) {
            assertThat(underTest.size()).isEqualTo(i);
            underTest.pop();
            assertThat(underTest.size()).isEqualTo(i - 1);
        }
    }

    @Test
    void canClear() {
        // given
        List<Integer> values = List.of(0, 1, 2, 3, 4, 5);
        values.forEach(underTest::push);

        // when
        underTest.clear();
        int clearedSize = underTest.size();

        // then
        assertThat(clearedSize).isEqualTo(0);

    }

    @Test
    void canThrowExceptionWhenPoppingEmptyStack() {
        // given
        int size = underTest.size();
        assert size == 0;
        String errorMessage = "Stack is empty";

        // then
        assertThatRuntimeException()
                .isThrownBy(()-> underTest.pop())
                .withMessageContaining(errorMessage);
    }

    @Test
    void canThrowExceptionWhenPeekingEmptyStack() {
        // given
        int size = underTest.size();
        assert size == 0;
        String errorMessage = "Stack is empty";

        // then
        assertThatRuntimeException()
                .isThrownBy(()-> underTest.peek())
                .withMessageContaining(errorMessage);
    }

    @Test
    void canIncreaseStackCapacity() {
        // checks for the increase in capacity
        // whenever the number of elements equals
        // a value in the sequence given by equation
        // f(x) = 2^n * 10 + 1 with n starting at 0
        int base = 10;
        for (int i = 1; i <= 101; i++) {
            underTest.push(i);
            if (i == base + 1) {
                assertThat(underTest.capacity())
                        .isEqualTo(base * 2);
                base *= 2;
            }
        }
    }

    @Test
    void canDecreaseStackCapacity() {
        // given
        int numOfElements = 101;
        for (int i = 0; i < numOfElements; i++) {
            underTest.push(i);
        }
        for (int i = numOfElements - 1; i > 0; i--) {
            int origCapacity = underTest.capacity();
            if (i >= 10 && origCapacity > underTest.size() * 2) {
                underTest.pop();
                int currentCapacity = underTest.capacity();
                assertThat(currentCapacity)
                        .isEqualTo(origCapacity / 2);
            } else {
                underTest.pop();
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1_000})
    void canInitializeMyStackWithCustomInitialCapacity(int initialCapacity) {
        // given
        underTest = new MyStack<>(initialCapacity);

        // when
        int actualCapacity = underTest.capacity();

        // then
        assertThat(actualCapacity).isEqualTo(initialCapacity);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    void canThrowExceptionWhenInitialCapacityLessThanOne(int initialCapacity) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> underTest = new MyStack<>(initialCapacity))
                .withMessage("Initial capacity must be at least 1");
    }

    @ParameterizedTest
    @CsvSource({
            "5, 7",
            "9, 10",
            "20, 21",
            "100, 101"
    })
    void canThrowExceptionWhenMinCapacityGreaterThanInitialCapacity(
            int initialCapacity, int minCapacity) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> underTest =
                        new MyStack<>(initialCapacity,minCapacity))
                .withMessage("Minimum capacity must be less " +
                             "than initial capacity");
    }

    @Test
    void canToString() {
        // given
        List<Integer> values = List.of(1, 2, 3, 4, 5);
        values.forEach(underTest::push);
        String expectedString = "[5 -> 4 -> 3 -> 2 -> 1]";

        // when
        String actualString = underTest.toString();

        // then
        assertThat(actualString).isEqualTo(expectedString);
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