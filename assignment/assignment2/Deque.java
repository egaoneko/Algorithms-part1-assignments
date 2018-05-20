import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private final Node first, last;
    private int size;

    private class Node {
        private Item item;
        private Node prev;
        private Node next;

        public Node() {
        }

        Node(Item item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public Deque() { // construct an empty deque
        this.first = new Node();
        this.last = new Node();

        this.first.next = this.last;
        this.last.prev = this.first;
        this.size = 0;
    }

    public boolean isEmpty() { // is the deque empty?
        return this.size() == 0;
    }

    public int size() { // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) { // add the item to the front
        if (item == null) {
            throw new IllegalArgumentException("Can not addFirst with null");
        }

        Node newNode = new Node(item, this.first, this.first.next);
        this.first.next.prev = newNode;
        this.first.next = newNode;
        this.size += 1;
    }

    public void addLast(Item item) { // add the item to the end
        if (item == null) {
            throw new IllegalArgumentException("Can not addLast with null");
        }

        Node newNode = new Node(item, this.last.prev, this.last);
        this.last.prev.next = newNode;
        this.last.prev = newNode;
        this.size += 1;
    }

    public Item removeFirst() { // remove and return the item from the front
        if (this.isEmpty()) {
            throw new NoSuchElementException("Can not removeFirst with empty deque");
        }

        Node removedNode = this.first.next;
        removedNode.next.prev = this.first;
        this.first.next = removedNode.next;
        this.size -= 1;
        return removedNode.item;
    }

    public Item removeLast() { // remove and return the item from the end
        if (this.isEmpty()) {
            throw new NoSuchElementException("Can not removeLast with empty deque");
        }
        Node removedNode = this.last.prev;
        removedNode.prev.next = this.last;
        this.last.prev = removedNode.prev;
        this.size -= 1;
        return removedNode.item;
    }

    public Iterator<Item> iterator() { // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first.next;

        public boolean hasNext() {
            return current != last;
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("Does not have next item");
            }

            Item item = current.item;
            current = current.next;
            return  item;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove is not support");
        }
    }

    public static void main(String[] args) { // unit testing (optional)
    }
}
