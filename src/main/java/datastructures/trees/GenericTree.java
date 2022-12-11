package datastructures.trees;

import java.util.Iterator;

public interface GenericTree<E> {
    E getRootElement();
    boolean isEmpty();
    int size();
    boolean contains(E targetElement);
    E find(E targetElement);
    Iterator<E> iteratorInOrder();
    Iterator<E> iteratorPreOrder();
    Iterator<E> iteratorPostOrder();
}
