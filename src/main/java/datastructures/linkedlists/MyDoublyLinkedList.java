package datastructures.linkedlists;

public class MyDoublyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    /**
     * Add new data to the end of the linked list
     * @param data new data
     */
    @Override
    public void add(E data) {
        if (head == null) {
            head = new Node<>(null, data, null);
            tail = head;
        } else {
            tail.next = new Node<>(tail, data, null);
            tail = tail.next;
        }
        size++;
    }

    /**
     * Insert new element at the indicated index and push
     * everything behind it one index down
     *
     * @param index target index
     * @param element  new element
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(
                    String.format(
                            "Index: %d, Size: %d%n",
                            index, this.size));
        }

        // adding to the front
        if (index == 0) {
            if (head == null) {
                add(element);
            } else {
                Node<E> newNode = new Node<>(null, element, head);
                head.last = newNode;
                head = newNode;
                this.size++;
            }
        }
        // adding to the end
        else if (index == size) {
            Node<E> newNode = new Node<>(tail, element, null);
            tail.next = newNode;
            tail = newNode;
            this.size++;
        }
        // adding to the middle
        else {
            Node<E> itr = head;
            long i = 0;
            while (itr != null) {
                if (i == index - 1) {
                    Node<E> newNode = new Node<>(itr, element, itr.next);
                    itr.next.last = newNode;
                    itr.next = newNode;
                    this.size++;
                    break;
                }
                itr = itr.next;
                i++;
            }
        }
    }

    @Override
    public E get(int index) {
        // TODO: implement backwards search for index > size >> 1 (size/2)
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format(
                    "Index: %d, Size: %d%n",
                    index, this.size
            ));
        }
        E data = null;
        // get head
        if (index == 0) {
            data = head.data;
        }
        // get tail
        else if (index == this.size - 1) {
            data = tail.data;
        }
        // get middle nodes
        else {
            Node<E> itr = head;
            long i = 0;
            while (itr != null) {
                if (i == index) {
                    data = itr.data;
                    break;
                }
                itr = itr.next;
                i++;
            }
        }
        return data;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format(
                    "Index: %d, Size: %d%n",
                    index, this.size
            ));
        }

        Node<E> itr = head;
        int i = 0;
        while (itr != null) {
            if (i == index) {
                if (itr.last != null) {
                    itr.last.next = itr.next;
                }
                if (itr.next != null) {
                    itr.next.last = itr.last;
                }
                // removing head
                if (i == 0) {
                    head = itr.next;
                }

                itr.next = null;
                itr.last = null;
                this.size--;
                break;
            }
            itr = itr.next;
            i++;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        Node<E> itr = head;
        while (itr != null) {
            str.append(itr.data);
            if (itr.next != null) str.append(", ");
            itr = itr.next;
        }
        str.append(']');
        return str.toString();
    }

    private static class Node<E> {
        E data;
        Node<E> last;
        Node<E> next;

        public Node(Node<E> last, E data, Node<E> next) {
            this.last = last;
            this.data = data;
            this.next = next;
        }
    }
}
