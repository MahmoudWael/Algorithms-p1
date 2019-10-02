import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;
    private Item[] itemsArray;

    // construct an empty randomized queue
    public RandomizedQueue() {
        N = 0;
        itemsArray = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (N == itemsArray.length) resize(itemsArray.length * 2);
        itemsArray[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (N > 0 && N == itemsArray.length / 4) resize(itemsArray.length / 2);
        int rand = StdRandom.uniform(0, N);
        Item item = itemsArray[rand];
        itemsArray[rand] = itemsArray[--N];
        itemsArray[N] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int rand = StdRandom.uniform(0, N);
        return itemsArray[rand];
    }

    private void resize(int capacity) {
        Item[] copyArray = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copyArray[i] = itemsArray[i];
        }
        itemsArray = copyArray;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int size;
        private int[] randPos;

        public RandomizedQueueIterator() {
            size = N;
            randPos = new int[size];
            for (int i = 0; i < size; i++) {
                randPos[i] = i;
            }
            StdRandom.shuffle(randPos);
        }

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return itemsArray[randPos[--size]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupported Operation!");
        }

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        rq.enqueue(12);
        rq.enqueue(78);
        rq.enqueue(42);
        rq.enqueue(53);
        rq.enqueue(67);
        rq.enqueue(100);
        rq.enqueue(3);
        rq.enqueue(5);
        System.out.println("======= print items===========");

        for (int item : rq) {
            System.out.println(item);
        }
        System.out.println("======= remove  2 items ======");

        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());

        System.out.println("======= print sample =========");
        System.out.println(rq.sample());

        System.out.println("========== print list=========");
        for (int item : rq) {
            System.out.println(item);
        }
        System.out.println("========== list size=========");

        System.out.println(rq.size());
    }

}
