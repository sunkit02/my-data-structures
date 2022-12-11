package datastructures.queues;

import java.util.Arrays;

public class MyArrayQueue<E> implements Queue<E> {

    private int head;
    private int tail;
    private int size;
    private E[] elements;

    @SuppressWarnings("unchecked")
    public MyArrayQueue() {
        head = 0;
        tail = 0;
        size = 0;
        elements = (E[]) new Object[10];
    }

    @Override
    public void add(E element) {
        offer(element);
    }

    @Override
    public void offer(E element) {
        elements[tail++] = element;
        size++;

        if (tail >= elements.length) {
            // when tail pointer is out of bounds
            if (head > 0) {
                tail = 0;
            } else {
                extendArray();
            }
        } else if (head == tail) {
            extendArray();
        }
    }

    @Override
    public E remove() {
        return poll();
    }

    @Override
    public E poll() {
        E element = elements[head];
        elements[head++] = null;
        if (head >= elements.length) {
            head = 0;
        }
        size--;
        return element;
    }

    @Override
    public E element() {
        return peek();
    }

    @Override
    public E peek() {
        return elements[head];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    @SuppressWarnings("unchecked")
    private void extendArray() {
        E[] newArray = (E[]) new Object[elements.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[head++];
            // wrap around to the beginning if there are elements there
            if (head >= elements.length) {
                head = 0;
            }
        }
        head = 0;
        tail = size;
        elements = newArray;
    }
}
