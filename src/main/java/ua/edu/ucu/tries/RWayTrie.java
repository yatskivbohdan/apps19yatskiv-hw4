package ua.edu.ucu.tries;

import ua.edu.ucu.utils.Queue;

import java.util.Arrays;
import java.util.Comparator;

public class RWayTrie implements Trie {
    private static int R = 26;
    private Node root;
    private int size;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
        private boolean isLeaf;

        public Node() {
            isLeaf = false;
        }

        public Node(char val) {
            this.val = val;
            isLeaf = false;
        }
    }


    public RWayTrie(){
        root = new Node();
        size = 0;
    }

    @Override
    public void add(Tuple t) {
        Node root_copy = root;
        int index;
        for (int i = 0; i < t.weight; i++) {
            index = t.term.charAt(i) - 'a';
            if (root_copy.next[index] == null){
                root_copy.next[index] = new Node(t.term.charAt(i));
            }
            root_copy = root_copy.next[index];
        }
        root_copy.isLeaf = true;
        size ++;
    }

    @Override
    public boolean contains(String word) {
        Node node = findNode(word);
        if(node == null) {
            return false;
        }
        else {
            return node.isLeaf;
        }
    }

    private Node findNode(String word){
        if (word == null){
            return null;
        }
        Node root_copy = root;
        int index;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (root_copy.next[index] != null) {
                root_copy = root_copy.next[index];
            }
            else {
                return null;
            }
        }
        if (root_copy == root) {
            return null;
        }
        return root_copy;
    }

    @Override
    public boolean delete(String word) {
        Node node = findNode(word);
        if (node != null && node.val != null){
            node.isLeaf = false;
            size --;
            return true;
        }
        return false;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");

    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Node node = findNode(s);
        Queue queue = new Queue();
        collect(node, s, queue);
        if (!contains(s)){
            queue.dequeue();
        }
        Object[] queueArray = queue.toArray();
        String[] toReturn = Arrays.copyOf(queueArray, queueArray.length, String[].class);
        Arrays.sort(toReturn, Comparator.comparingInt(String::length));
        return Arrays.asList(toReturn);
    }

    private void collect(Node node, String s, Queue queue) {
        if (node.val != null){
            queue.enqueue(s);
        }
        for (char i = 0; i < R; i++) {
            //System.out.println(s);
            //System.out.println(Arrays.toString(queue.toArray()));
            //System.out.println((Arrays.toString(node.next)));
            if (node.next[i] != null)
                collect(node.next[i], s + node.next[i].val, queue);
        }
    }
    @Override
    public int size() {
        return size;
    }

}
