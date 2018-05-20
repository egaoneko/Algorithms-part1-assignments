import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Empty input");
        }

        int k = Integer.parseInt(args[0]);         // n is the number of string on standard input.

        RandomizedQueue<String> queue = new RandomizedQueue<>();

        for (int i = 0; i < k; i++) {
            queue.enqueue(StdIn.readString());
        }

        for (String s: queue) {
            StdOut.println(s);
        }
    }
}
