package datastructures.interfaces;

public interface Queue<E> {
    void add(E element);
    void offer(E element);
    E remove();
    E poll();
    E element();
    E peek();
    boolean isEmpty();
    int size();
}
