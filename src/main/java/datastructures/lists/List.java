package datastructures.lists;

import java.util.Iterator;

public interface List<E> extends Iterable<E>, Iterator<E>{
    void add(E element);
    void add(int index, E element);
    void set(int index, E element);
    E get(int index);
    void remove(int index);
    void remove(E element);
    int size();
    boolean isEmpty();
    boolean contains(E element);
    void clear();
}
