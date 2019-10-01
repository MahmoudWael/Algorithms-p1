package main;

import edu.princeton.cs.algs4.LinkedStack;

public class LinkedStackOfStrings {
    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

    public static void main(String[] args) {
        LinkedStackOfStrings lStack = new LinkedStackOfStrings();
        System.out.println(lStack.isEmpty());
        lStack.push("str1");
        lStack.push("str2");
        System.out.println(lStack.pop());
    }

}
