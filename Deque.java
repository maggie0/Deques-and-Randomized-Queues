import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node    // 16 bytes (object overhead)
    {                     // 8 bytes (inner class extra overhead)
        private Item item;        // 8 bytes (reference to Item)
        private Node pre;         // 8 bytes (reference to Node)
        private Node next;        // 8 bytes (reference to Node)
    }
    private Node first = null, last = null; // 16 bytes
    private int length;   // 4 bytes
   
    public Deque() { // construct an empty deque 
        length = 0;
    }
    
    public boolean isEmpty() { // is the deque empty? 
        return length == 0;        
    }
    
    public int size() { // return the number of items on the deque 
        return length;        
    }
    
    public void addFirst(Item item) { // add the item to the front 
        if (item == null)
            throw new NullPointerException("Item cannot be null");
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.pre = null;
        newFirst.next = first;
        if (isEmpty()) last = newFirst;
        else first.pre = newFirst;
        first = newFirst;
        ++length;
    }
    
    public void addLast(Item item) { // add the item to the end
        if (item == null)
            throw new NullPointerException("Item cannot be null");
        Node newLast = new Node();
        newLast.item = item;
        newLast.pre = last;
        newLast.next = null;
        if (isEmpty()) first = newLast;
        else last.next = newLast;
        last = newLast;
        ++length;
    }
    
    public Item removeFirst() { // remove and return the item from the front 
        if (isEmpty())
            throw new NoSuchElementException("Dequeue underflow");
        Item item = first.item;
        first = first.next;
        --length;
        if (isEmpty()) last = null;
        else first.pre = null;
        return item;
    }
    
    public Item removeLast() { // remove and return the item from the end 
        if (isEmpty())
            throw new NoSuchElementException("Dequeue underflow");
        Item item = last.item;
        last = last.pre;
        --length;
        if (isEmpty()) first = null;
        else last.next = null;
        return item;
    }
    
    public Iterator<Item> iterator() { // return an iterator over items in order from front to end
        return new ListIterator();        
    }
    
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Reached end of dequeue");
            Item item = current.item;
            current = current.next;
            return item;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) { // unit testing (optional) 
        Deque<Integer> dq = new Deque<Integer>();
        dq.addFirst(20);
        dq.addFirst(21);
        dq.addLast(22);
        dq.addLast(23);     
        System.out.println(dq.removeFirst() + " is removed from front");
        System.out.println(dq.removeLast() + " is removed from end");
        dq.addLast(24);
        System.out.println("The size of deque is " + dq.size());
        System.out.println("The elements in deque from front to end:");
        for (int i : dq)
            System.out.println(i);
    }
}