package datastructures.interfaces;

public interface LinkedList<E> {
    void add(E element);
    void add(long index, E element);
    E get(long index);
    void remove(long index);
    long size();
    String toString();
}
