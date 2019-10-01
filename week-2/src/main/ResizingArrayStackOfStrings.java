package main;

public class ResizingArrayStackOfStrings {
    private String[] s;
    private int N = 0;

    public ResizingArrayStackOfStrings() {
        s = new String[1];
    }

    public void push(String item) {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    public String pop() {
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4) resize(s.length / 2);
        return item;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    public void printArrayLength() {
        System.out.println(s.length);
    }

    public static void main(String[] args) {
        ResizingArrayStackOfStrings resArraySt = new ResizingArrayStackOfStrings();
        for (int i = 0; i < 10; i++) {
            resArraySt.push("str" + i);
        }
        System.out.println(resArraySt.pop());
        resArraySt.printArrayLength();
    }

}
