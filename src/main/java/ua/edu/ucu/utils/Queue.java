package ua.edu.ucu.utils;


public class Queue {
    private ImmutableLinkedList list;

    public Queue() {
        list = new ImmutableLinkedList();
    }

    public Object peek() {
        return list.getFirst();
    }

    public void enqueue(Object item) {
        list = list.addLast(item);
    }

    public Object dequeue() {
        Object item = list.getFirst();
        list = list.removeFirst();
        return item;
    }

    public Object[] toArray() {
        return list.toArray();

    }

}
