import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    public RandomizedQueue() { // construct an empty randomized queue
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() { // is the randomized queue empty?
        return this.size() == 0;
    }

    public int size() { // return the number of items on the randomized queue
        return size;
    }

    public void enqueue(Item item) { // add the item
        if (item == null) {
            throw new IllegalArgumentException("Can not enqueue with null");
        }

        if (size == items.length) {
            resize(2 * items.length);
        }

        items[size] = item;
        size += 1;
    }

    public Item dequeue() { // remove and return a random item
        if (this.isEmpty()) {
            throw new NoSuchElementException("Can not dequeue with empty deque");
        }

        int r = StdRandom.uniform(size);
        Item removedItem = items[r];
        items[r] = items[size - 1];
        size -= 1;

        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }

        return removedItem;
    }

    public Item sample() { // return a random item (but do not remove it)
        if (this.isEmpty()) {
            throw new NoSuchElementException("Can not dequeue with empty sample");
        }

        int r = StdRandom.uniform(size);
        return items[r];
    }

    public Iterator<Item> iterator() { // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] copiedItems;
        private int copiedSize;

        public RandomizedQueueIterator() {
            copiedItems = (Item[]) new Object[size];
            System.arraycopy(items, 0, copiedItems, 0, size);
            copiedSize = size;
        }

        @Override
        public boolean hasNext() {
            return copiedSize > 0;
        }

        @Override
        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("Does not have next item");
            }

            int r = StdRandom.uniform(copiedSize);
            Item removedItem = copiedItems[r];
            copiedItems[r] = copiedItems[copiedSize - 1];
            copiedSize -= 1;

            return removedItem;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is not support");
        }
    }

    public static void main(String[] args) { // unit testing (optional)
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, copy, 0, size);
        items = copy;
    }
}
