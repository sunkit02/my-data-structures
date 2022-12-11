package datastructures.stacks;

public class MyLinkedStack<E> implements Stack<E> {

    private Node<E> head;
    private int size = 0;

    @Override
    public void push(E element) {
        head = new Node<>(element, head);
        size++;
    }

    @Override
    public E pop() {
        E data = head.data;
        head = head.next;
        size--;
        return data;
    }

    @Override
    public E peek() {
        return head.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
    @Override
    public void clear() {
        head.next = null;
        head = null;
        size = 0;
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
