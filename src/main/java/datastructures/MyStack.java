package datastructures;

import datastructures.interfaces.Stack;

/**
 * Sun Kit's implementation of a stack using techniques in java.util.ArrayList
 * for memory management
 * @since 9/4/2022
 * @author Sun Kit Tsui
 * @version 1.0
 * @param <E> type of object the stack will be holding
 */
public class MyStack<E> implements Stack<E> {
    private E[] elementArray;
    private int topIndex;

    private static final int MIN_CAPACITY = 10;
    private int minCapacity = MIN_CAPACITY;

    /**
     * Creates an instance of {@code MyStack} with {@code initialCapacity} of 10
     * and default {@code minimumCapacity} of 10;
     */
    @SuppressWarnings("unchecked")
    public MyStack() {
        elementArray = (E[]) new Object[10];
        topIndex = -1;
    }

    /**
     * Creates an instance of {@code MyStack} with customized {@code initialCapacity}
     * @param initialCapacity custom {@code initialCapacity} desired
     * @untested
     */
    @SuppressWarnings("unchecked")
    public MyStack(int initialCapacity) {
        elementArray = (E[]) new Object[initialCapacity];
        topIndex = -1;
    }

    /**
     * Creates an instance of {@code MyStack} with customized {@code initialCapacity} and {@code minimumCapacity}
     * @param initialCapacity custom {@code initialCapacity} desired
     * @param minimumCapacity custom {@code minimumCapacity} desired
     * @throws IllegalArgumentException if {@code initialCapacity} is less than {@code minimumCapacity} or
     *                                  {@code initialCapacity} is less than 1
     * @untested
     */
    @SuppressWarnings("unchecked")
    public MyStack(int initialCapacity, int minimumCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Initial capacity must be at least 1");
        }
        if (minimumCapacity > initialCapacity) {
            throw new IllegalArgumentException("Minimum capacity can not be greater than initial capacity");
        }
        minCapacity = minimumCapacity;
        elementArray = (E[]) new Object[initialCapacity];
    }

    /**
     * Push the element of type E into the stack
     * @param element object pushing into stack
     */
    public void push(E element) {
        // check if stack is full
        if (topIndex >= elementArray.length - 1) {
            copyArray(elementArray.length * 2);
        }
        // push new element
        elementArray[++topIndex] = element;
    }

    /**
     * Pop element on top of the stack and return it
     * @return element on top of the stack
     */
    public E pop() {
        // throw exception if stack is empty
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        // get the top element
        E result = elementArray[topIndex];
        // throw out top element
        elementArray[topIndex--] = null;
        if ((topIndex + 1) % 10 == 0) trimElementArray();
        return result;
    }

    /**
     * Takes a look at the top element in the stack without popping it
     * @return element on top of the stack
     */
    public E peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return elementArray[topIndex];
    }

    /**
     * Removes all elements in current stack by pointing the reference of
     * {@code elementArray} to a new, empty array with {@code MIN_CAPACITY}
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        elementArray = (E[]) new Object[minCapacity];
        topIndex = -1;
    }

    /**
     * Checks is the stack is empty
     * @return {@code true} if the stack is empty
     */
    public boolean isEmpty() {
        return (topIndex < 0);
    }

    /**
     * Checks the number of elements in the stack
     * @return {@code int} of elements in the stack
     */
    public int size() {
        return topIndex + 1;
    }

    /**
     * Checks the number of elements that can be stored with
     * the current amount of memory allocated
     * @return length of array containing stack elements currently in use
     */
    public int capacity() {
        return elementArray.length;
    }

    /**
     * Copy all none {@code null} element from current {@code elementArray} to a
     * new array with length {@code newArrayLength} and at least 10
     * @param newArrayLength new length for {@code elementArray}
     * @implNote If {@code elementArray} contains more than 10 none {@code null} values
     *           and {@code newArrayLength} is less than or equal to 10, any element with
     *           index of 10 and greater will be ignored.
     */
    @SuppressWarnings("unchecked")
    private void copyArray(int newArrayLength) {
        // copy stack to an array with new length
        // if new length is less than MIN_CAPACITY of 10 then new length is 10
        newArrayLength = Math.max(newArrayLength, minCapacity);
        E[] newElementArray = (E[]) new Object[newArrayLength];

        // copy all none null elements into new stack
        for (int i = 0; i < elementArray.length && i < newElementArray.length; i++) {
            if (i + 1 < elementArray.length && elementArray[i + 1] == null) {
                newElementArray[i] = elementArray[i];
                break;
            }
            newElementArray[i] = elementArray[i];
        }

        // set stack to new stack
        elementArray = newElementArray;
    }

    /**
     * Checks if {@code elementArray} is taking up more than twice of current number of elements
     * if true then trim length of elementArray down to current number of elements * 2
     */
    private void trimElementArray() {
        int threshold = (topIndex + 1) * 2;
        if (elementArray.length > threshold) {
            copyArray(threshold);
        }
    }

    public String toString() {
        StringBuilder string = new StringBuilder("[");
        for (int i = elementArray.length - 1; i >= 0; i--) {
            E element = elementArray[i];
            if (element != null) {
                string.append(element).append(" -> ");
            }
        }
        if (string.length() > 1) string = new StringBuilder(string.substring(0, string.length() - 4));
        string.append("]");
        return string.toString();
    }
}
