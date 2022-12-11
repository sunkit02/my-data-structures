package datastructures.lists;

import java.util.Iterator;

public class MyArrayList<E> implements List<E> {
    E[] elements;
    int size;
    int tail;
    int capacity;

    public MyArrayList() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int initialSize) {
        elements = (E[]) new Object[initialSize];
        size = 0;
        tail = -1;
        capacity = initialSize;
    }


    @Override
    public void add(E element) {
        if (tail < capacity - 1) {
            elements[++tail] = element;
        }
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public void set(int index, E element) {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public void remove(E element) {

    }

    @Override
    public int size() {
        return tail + 1;
    }

    @Override
    public boolean isEmpty() {
        return tail == -1;
    }

    @Override
    public boolean contains(E element) {
        for (E e: elements ) {
            if (e.equals(element))
                return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        elements = (E[]) new Object[capacity];
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public E next() {
        return null;
    }
}
