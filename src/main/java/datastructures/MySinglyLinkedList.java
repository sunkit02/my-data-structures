package datastructures;

import datastructures.interfaces.LinkedList;

/**
 * Sun Kit's personal implementation of a singly linked list
 *
 * @param <E> the type of elements stored in linked list
 * @implNote By storing a pointer to the tail node, adding to and removing tail of
 * linked list has O(1) time complexity
 */
public class MySinglyLinkedList<E> implements LinkedList<E> {

    private Node<E> head;

    private Node<E> tail;
    private int size = 0;

    public MySinglyLinkedList() {
    }

    public void add(E element) {
        if (head == null) {
            head = new Node<>(element, null);
            tail = head;
        } else {
            tail.next = new Node<>(element, null);
            tail = tail.next;
        }
        this.size++;
    }

    public void add(int index, E element) {

        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(String.format(
                    "Index: %d, LastIndex: %d%n",
                    index, this.size-1
            ));
        }

        // fix logic for adding to the head of linked list
        if (index == 0) {
            head = new Node<>(element, head);
            tail = head;
            this.size++;
            return;
        }

        Node<E> itr = this.head;
        int i = 0;
        while (itr != null) {
            if (i == index - 1) {
                Node<E> temp = itr.next;
                itr.next = new Node<>(element, temp);
                this.size++;
                return;
            }
            itr = itr.next;
            i++;
        }
    }

    public E get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(String.format(
                    "Index: %d, LastIndex: %d%n",
                    index, this.size-1
            ));
        }

        Node<E> itr = head;
        int i = 0;
        while (itr != null) {
            if (i == index) {
                return itr.data;
            }
            itr = itr.next;
            i++;
        }
        return null;
    }

    public void remove(int index) {
        // TODO: fix memory leak
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(String.format(
                    "Index: %d, LastIndex: %d%n",
                    index, this.size-1
            ));
        }

        // case for removing head node
        if (index == 0) {
            head = head.next;
            this.size--;
            return;
        }

        // iterate through linked list until target index
        Node<E> itr = head;
        int i = 0;
        while (itr != null) {
            if (i == index - 1) {
                // remove node at target index
                itr.next = itr.next.next;
                // check if removing tail node
                if (index == this.size - 1) {
                    // reassign tail node
                    tail = itr;
                }
                // decrement size field accordingly
                this.size--;
                return;
            }
            itr = itr.next;
            i++;
        }
    }

    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> itr = head;
        while (itr != null) {
            result.append(itr.data).append(" -> ");
            itr = itr.next;
        }
        result.append("]");
        return result.toString();
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "{Node: data=" + this.data + ", next=" + this.next + "}";
        }
    }
}
