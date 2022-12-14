package datastructures.stacks;

public interface Stack<E> {
    void push(E element);
    E pop();
    E peek();
    boolean isEmpty();
    void clear();
    String toString();
}
