import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size = 0;


    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Deque() {
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (size() > 0) {
            oldFirst.prev = first;
        } else {
            last = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        if (size() > 0) {
            oldLast.next = last;
        } else {
            first = last;

        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item = first.item;
        if (size() == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item item = last.item;
        if (size() == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return item;
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupported Operation!");
        }

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);

        for (int item : deque) {
            StdOut.println(item);
        }

        StdOut.println("=========remove first then last================");

        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());

        StdOut.println("==========print list===============");

        for (int item : deque) {
            StdOut.println(item);
        }

        StdOut.println("=========remove last================");

        StdOut.println(deque.removeLast());

        StdOut.println("is empty? " + deque.isEmpty());

        StdOut.println("==========print list===============");
        Iterator item = deque.iterator();
        int i;
        while (item.hasNext()) {
            i = (int) item.next();
            StdOut.println(i);
//            item.remove();

        }
        StdOut.println("=========remove last * 2 ================");

        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println("is empty? " + deque.isEmpty());

    }

}