package datastructures;

import datastructures.interfaces.Stack;

import java.util.Arrays;

public class MyDropOutStack<E> implements Stack<E> {
    E[] elementArray;
    int topIndex = -1;
    int capacity = 10;

    int size = 0;

    @SuppressWarnings("unchecked")
    public MyDropOutStack() {
        this.elementArray = (E[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public MyDropOutStack(int capacity) {
        this.capacity = capacity;
        this.elementArray = (E[]) new Object[this.capacity];
    }

    @Override
    @SuppressWarnings("unchecked")
    public void push(E element) {
        if (topIndex == capacity - 1) {
            // drop first element
            E[] newArray = (E[]) new Object[capacity];
            for (int i = 1; i < elementArray.length; i++) {
                newArray[i-1] = elementArray[i];
            }
            elementArray = newArray;
            // push new element
            elementArray[topIndex] = element;
        } else {
            elementArray[++topIndex] = element;
            size++;
        }
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");

        E result = elementArray[topIndex];
        elementArray[topIndex--] = null;
        size--;
        return result;
    }

    @Override
    public E peek() {
        return elementArray[topIndex];
    }

    @Override
    public boolean isEmpty() {
        return (topIndex == -1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        elementArray = (E[]) new Object[capacity];
        topIndex = -1;
        size = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementArray);
    }
}
