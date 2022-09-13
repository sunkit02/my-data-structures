package datastructures;

import datastructures.interfaces.LinkedList;

public class MyDoublyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private long size = 0;

    /**
     * Add new data to the end of the linked list
     * @param data new data
     */
    @Override
    public void add(E data) {
        if (head == null) {
            head = new Node<>(null, data, null);
            tail = head;
        } else {
            tail.next = new Node<>(tail, data, null);
            tail = tail.next;
        }
        size++;
    }

    /**
     * Insert new data at the indicated index and push
     * everything behind it one index down
     *
     * @param index target index
     * @param data  new data
     */
    @Override
    public void add(long index, E data) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(
                    String.format(
                            "Index: %d, Size: %d%n",
                            index, this.size));
        }

        // adding to the front
        if (index == 0) {
            Node<E> newNode = new Node<>(null, data, head);
            head.last = newNode;
            head = newNode;
            this.size++;
        }
        // adding to the end
        else if (index == size) {
            Node<E> newNode = new Node<>(tail, data, null);
            tail.next = newNode;
            tail = newNode;
            this.size++;
        }
        // adding to the middle
        else {
            Node<E> itr = head;
            long i = 0;
            while (itr != null) {
                if (i == index) {
                    Node<E> newNode = new Node<>(itr.last, data, itr);
                    itr.next.last = newNode;
                    itr.next = newNode;
                    this.size++;
                    break;
                }
                itr = itr.next;
                i++;
            }
        }
    }

    @Override
    public E get(long index) {
        return null;
    }

    @Override
    public void remove(long index) {

    }

    @Override
    public long size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        Node<E> itr = head;
        while (itr != null) {
            str.append(itr.data).append(", ");
            itr = itr.next;
        }
        str.deleteCharAt(str.length()-2);
        str.deleteCharAt(str.length()-1);
        str.append(']');
        return str.toString();
    }

    private static class Node<E> {
        E data;
        Node<E> last;
        Node<E> next;

        public Node(Node<E> last, E data, Node<E> next) {
            this.last = last;
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", last=" + last +
                    ", next=" + next +
                    '}';
        }
    }
}
