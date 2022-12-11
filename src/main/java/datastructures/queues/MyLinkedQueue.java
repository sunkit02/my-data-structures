package datastructures.queues;

import datastructures.linkedlists.LinkedList;
import datastructures.linkedlists.MyDoublyLinkedList;

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
    public void add(E element) {
        // todo: throw exception if cannot perform operation
        offer(element);
    }

    @Override
    public void offer(E element) {
        linkedList.add(linkedList.size(), element);
    }

    @Override
    public E remove() {
        // todo: throw exception if cannot perform operation
        return poll();
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        E item = linkedList.get(0);
        linkedList.remove(0);
        return item;
    }

    @Override
    public E element() {
        // todo: throw exception if cannot perform operation
        return peek();
    }

    @Override
    public E peek() {
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
            add(e);
        }
    }

    public void enqueueAll(E[] array) {
        for (E item : array) {
            add(item);
        }
    }

    @Override
    public String toString() {
        return linkedList.toString()
                .replace(",", " <-");
    }
}
