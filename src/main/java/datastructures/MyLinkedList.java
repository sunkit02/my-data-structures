package datastructures;

import datastructures.interfaces.LinkedListADT;

public class MyLinkedList<E> implements LinkedListADT<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

    }

    public MyLinkedList() {
    }

    public void add(E data) {
        if (head == null) {
            head = new Node<E>(data, null);
            tail = head;
        } else {
            Node<E> newNode = new Node<>(data, null);
            tail.next = newNode;
            tail = newNode;
        }
        this.size++;
    }

    public void add(int index, E data) {

        if (head == null && index > this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " is greater than length of MyLinkedList " + this.size);
        }

        if (index == 0) {
            head = new Node<>(data, head);
            tail = head;
            return;
        }

        Node<E> itr = this.head;
        int i = 0;
        while (itr != null) {
            if (i == index - 1) {
                Node<E> temp = itr.next;
                itr.next = new Node<>(data, temp);
                return;
            }
            itr = itr.next;
        }
    }

    public E get(int index) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " is greater than length of MyLinkedList " + this.size);
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
        if (index > this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " is greater than length of MyLinkedList " + this.size);
        }

        if (index == 0) {
            head = head.next;
            this.size--;
            return;
        }
        Node<E> itr = head;
        int i = 0;
        while (itr != null) {
            if (i == index - 1) {
                itr.next = itr.next.next;
                this.size--;
                return;
            }
            itr = itr.next;
            i++;
        }
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
}
