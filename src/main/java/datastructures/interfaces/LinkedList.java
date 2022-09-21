package datastructures.interfaces;

public interface LinkedList<E> {
    void add(E element);
    void add(int index, E element);
    E get(int index);
    void remove(int index);
    int size();
    String toString();
}
