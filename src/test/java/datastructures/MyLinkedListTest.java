package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyLinkedListTest {

    MyLinkedList<Integer> underTest;

    @BeforeEach
    void setUp() {
        underTest = new MyLinkedList<>();
    }

    @Test
    void canAddElements() {
        // given
        underTest.add(1);
        underTest.add(2);
        underTest.add(3);
        // then
        assertThat(underTest.)
    }

    @Test
    void testAdd() {
    }

    @Test
    void get() {
    }

    @Test
    void remove() {
    }
}