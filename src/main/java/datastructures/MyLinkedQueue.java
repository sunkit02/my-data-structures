package datastructures;

import datastructures.interfaces.LinkedList;
import datastructures.interfaces.Queue;

import java.util.Collection;

public class MyLinkedQueue<E> implements Queue<E> {

    LinkedList<E> linkedList;

    public MyLinkedQueue() {
        linkedList = new MyDoublyLinkedList<>();
    }

    public MyLinkedQueue(Collection<? extends E> collection) {
        this();
        enqueueAll(collection);
    }

    public MyLinkedQueue(E[] array) {
        this();
        enqueueAll(array);
    }

    @Override
    public void enqueue(E element) {
        linkedList.add(linkedList.size(), element);
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        E item = linkedList.get(0);
        linkedList.remove(0);
        return item;
    }

    @Override
    public E first() {
        return linkedList.get(0);
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public int size() {
        return linkedList.size();
    }


    public void enqueueAll(Collection< ? extends E> collection) {
        Object[] objects = collection.toArray();
        for (Object obj : objects) {
            @SuppressWarnings("unchecked")
            E e = (E) obj;
            enqueue(e);
        }
    }

    public void enqueueAll(E[] array) {
        for (E item : array) {
            enqueue(item);
        }
    }

    @Override
    public String toString() {
        return linkedList.toString()
                .replace(",", " <-");
    }
}
