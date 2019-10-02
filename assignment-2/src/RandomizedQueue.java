import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

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
        return itemsArray.length;
    }

    // add the item
    public void enqueue(Item item) {
        if (N == size()) resize(size() * 2);
        itemsArray[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (N > 0 && N == size() / 4) resize(size() / 2);
        int rand = StdRandom.uniform(0, N);
        Item item = itemsArray[rand];
        itemsArray[rand] = itemsArray[--N];
        itemsArray[N] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
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


    }

}
