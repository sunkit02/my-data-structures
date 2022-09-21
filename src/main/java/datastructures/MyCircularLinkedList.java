package datastructures;

import datastructures.interfaces.CircularIterator;
import datastructures.interfaces.LinkedList;

/**
 * Sun Kit's implementation of a circular doubly linked list
 * @param <E>
 */
public class MyCircularLinkedList<E> implements LinkedList<E> {

    private Node<E> head;
    private int size = 0;

    public MyCircularLinkedList() {}

    /**
     * Created the first node in the circular doubly linked list that
     * points to itself in both directions
     * @param element new element
     */
    private void createFirstNode(E element) {
        head = new Node<>(null, element, null);
        head.prev = head;
        head.next = head;
        size++;
    }

    /**
     * Links a new node to the arbitrary beginning of the circular linked list
     * determined by the {@link #head} node by inserting the new node right
     * before the current {@link #head}
     * @param element new element
     */
    private void linkFirst(E element) {
        final Node<E> succ = head;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, element, succ);
        succ.prev = newNode;
        pred.next = newNode;
        head = newNode;
        size++;
    }

    /**
     * Links a new node before the given node {@code succ}
     * @param element new element
     * @param succ the node to link the new node before
     * @implNote Does not work when inserting to the beginning of
     *           linked list to replace the current {@link #head} node.
     */
    private void linkBefore(E element, Node<E> succ) {
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, element, succ);
        succ.prev = newNode;
        pred.next = newNode;
        size++;
    }

    /**
     * Unlinks the current {@link #head} node
     */
    private void unlinkFirst() {
        if (size <= 1) {
            head.next = null;
            head.prev = null;
            head.data = null;
            head = null;

            size = 0;
        } else {
            Node<E> prev = head.prev;
            Node<E> next = head.next;

            prev.next = head.next;
            next.prev = prev;

            head.prev = null;
            head.next = null;
            head.data = null;

            head = next;

            size--;
        }
    }

    /**
     * Unlinks the arbitrary current last node by unlinking the
     * node previous to the current {@link #head} node
     */
    private void unlinkLast() {
        // assert not removing last remaining element
        Node<E> prev = head.prev.prev;
        Node<E> node = head.prev;

        prev.next = head;
        head.prev = prev;

        node.prev = null;
        node.next = null;
        node.data = null;

        size--;
    }

    /**
     * Unlinks the given node
     * @param node node to unlink
     * @implNote Does not work for the {@link #head} node.
     *           Use the {@link #unlinkFirst()} method to do so.
     */
    private void unlink(Node<E> node) {
        final Node<E> prev = node.prev;
        final Node<E> next = node.next;
        // alter linked list
        prev.next = next;
        next.prev = prev;

        // clear all pointers to node
        node.data = null;
        node.prev = null;
        node.next = null;

        // decrement size
        size--;
    }

    /**
     * Iterates through the linked list and fetch the node at the
     * indicated index
     * @param index index of node
     * @return node at index
     */
    private Node<E> iterateUntil(int index) {
        Node<E> itr = head;
        int i = 0;
        do {
            if (i == index) {
                break;
            }
            itr = itr.next;
            i++;
        } while (itr != head);

        return itr;
    }

    /**
     * Adds a new node to the end of the linked list
     * @param element new element
     */
    public void add(E element) {
        if (head == null) {
            createFirstNode(element);
        } else {
            linkBefore(element, head);
        }
    }

    /**
     * Adds a new node to the indicated index
     * @param index target index
     * @param element new element
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    String.format("Index: %d, Size: %d%n",
                                    index, size));
        }

        if (head == null) {
            add(element);
        } else if (index == 0) {
            linkFirst(element);
        } else {
            Node<E> succ = iterateUntil(index);
            linkBefore(element, succ);
        }
    }

    /**
     * Get the element from the indicated index
     * @param index target index
     * @return the element at the target index
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    String.format("Index: %d, Size: %d%n",
                            index, size));
        }
        Node<E> targetNode = iterateUntil(index);
        return targetNode.data;
    }

    /**
     * Removes the element from the indicated index by unlinking
     * the Node containing the element
     * @param index target index
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    String.format("Index: %d, Size: %d%n",
                            index, size));
        }

        if (index == 0) {
            unlinkFirst();
        } else if (index == size - 1) {
            unlinkLast();
        } else {
            Node<E> targetNode = iterateUntil(index);
            unlink(targetNode);
        }
    }

    /**
     * Returns {@code true} if this linked list contains no elements
     * @return returns {@code true} if this linked list contains no elements and
     *         returns {@code false} if this linked list contains one or more elements
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Return the number of elements in this linked list
     * @return number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Return node in linked list at the indicated index
     * @param index index of node
     * @return node at index
     */
    Node<E> node(int index) {
        return new Node<>(null, null, null);
    }

    private void isValidIndex(int index) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        Node<E> itr = head;
        if (itr != null) {
            do {
                str.append(itr.data);
                if (itr.next != head) str.append(", ");
                itr = itr.next;
            } while (itr != head);
        }
        str.append(']');
        return str.toString();
    }

    public CircularIterator<E> listIterator(int index) {
        return new ListItr(0);
    }

    private class ListItr implements CircularIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;

        ListItr(int index) {

        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }
    }

    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
