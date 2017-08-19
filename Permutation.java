import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation { 
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
//        while (!StdIn.isEmpty()) 
//            randomizedQueue.enqueue(StdIn.readString());
//        for (int i = 0; i < k; i++)
//            StdOut.println(randomizedQueue.dequeue());
        
        // Reservoir Sampling, O(k) space complexity
        int n = 0;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (n++ < k) {
                randomizedQueue.enqueue(s);
            }
            else {
                int p = StdRandom.uniform(n);
                if (p < k) {
                    randomizedQueue.dequeue();
                    randomizedQueue.enqueue(s);
                }
            }
        }
        for (String i : randomizedQueue)
            StdOut.println(i);
    }
}