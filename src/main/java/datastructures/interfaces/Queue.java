package datastructures.interfaces;

public interface Queue<E> {
    void enqueue(E element);
    E dequeue();
    E first();
    boolean isEmpty();
    int size();
}
