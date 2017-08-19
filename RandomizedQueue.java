import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom; 

public class RandomizedQueue<Item> implements Iterable<Item> { 
    private Item[] rq;
    private int n;
    
    public RandomizedQueue() { // construct an empty randomized queue 
        n = 0;
        rq = (Item[]) new Object[1];
    }
    
    public boolean isEmpty() { // is the queue empty? 
        return n == 0;
    }
    
    public int size() { // return the number of items on the queue 
        return n;
    }
    
    public void enqueue(Item item) { // add the item 
        if (item == null)
            throw new NullPointerException("Item cannot be null");
        if (n == rq.length) resize(2*rq.length);
        rq[n++] = item;
    }
    
    public Item dequeue() { // remove and return a random item 
        if (isEmpty()) 
            throw new NoSuchElementException("Randomized queue underflow");
        int pos = StdRandom.uniform(n);
        Item item = rq[pos];
        rq[pos] = rq[--n];
        rq[n] = null;
        
        // shrink size of array if necessary
        if (n > 0 && n == rq.length/4) resize(rq.length/2);
        return item;
    }
    
    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= size();

        // textbook implementation
//        Item[] temp = (Item[]) new Object[capacity];
//        for (int i = 0; i < n; i++) {
//            temp[i] = rq[i];
//        }
//        rq = temp;

       // alternative implementation
        rq = java.util.Arrays.copyOf(rq, capacity);
    }
    
    public Item sample() { // return (but do not remove) a random item 
        if (isEmpty()) 
            throw new NoSuchElementException("Randomized queue underflow");
        return rq[StdRandom.uniform(n)];
    }
    
    public Iterator<Item> iterator() { // return an independent iterator over items in random order
        return new ArrayIterator();        
    }
    
    private class ArrayIterator implements Iterator<Item> {
        private Item[] shuffledItems;
        private int i;
        
        public ArrayIterator() {
            i = 0;
            shuffledItems = java.util.Arrays.copyOf(rq, n);
            StdRandom.shuffle(shuffledItems);
        }
        
        public boolean hasNext() {
            return i < n;
        }
        
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Reached end of randomized queue");
            return shuffledItems[i++];
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) { // unit testing (optional) 
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        System.out.println(rq.dequeue() + " is dequeued");
        rq.enqueue(6);
        rq.enqueue(7);
        for (int i : rq) {
            System.out.println(i);
            if (i == 1) {
                System.out.println("\r"); // carriage return
                for (int j : rq)
                    System.out.println(j);
                System.out.println("\r");
            }
        }
                
    }
}