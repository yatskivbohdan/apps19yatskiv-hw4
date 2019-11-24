package ua.edu.ucu.utils;

public class Node {
    private Node next;

    private Object value;

    public Node(Object value) {
        this.value = value;
        next = null;

    }

    public Node(Object value, Node next) {
        this.value = value;
        this.next = next;

    }

    public Object getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setValue(Object val) {
        value = val;
    }

    public void setNext(Node nxt) {
        next = nxt;
    }


}