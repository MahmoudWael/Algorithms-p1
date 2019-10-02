import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rQ = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            rQ.enqueue(StdIn.readString());
        }
        for (String s : rQ) {
            if (k-- == 0) {
                return;
            }
            System.out.println(rQ.dequeue());
        }
    }
}