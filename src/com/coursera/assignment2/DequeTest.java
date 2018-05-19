package com.coursera.assignment2;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class DequeTest {

    private Deque<Integer> deque;

    @Before
    public void setup() {
        this.deque = new Deque<>();
    }

    @Test
    public void testIsEmpty() {
        assertThat(this.deque.isEmpty(), is(true));
    }

    @Test
    public void testSize() {
        assertThat(this.deque.size(), is(0));
    }

    @Test
    public void testAddFirst() {
        int item = 1;
        int expected = this.deque.size() + 1;
        this.deque.addFirst(item);
        assertThat(this.deque.size(), is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFirst_InvalidNull_ExceptionThrown() {
        this.deque.addFirst(null);
    }

    @Test
    public void testAddLast() {
        int item = 1;
        int expected = this.deque.size() + 1;
        this.deque.addLast(item);
        assertThat(this.deque.size(), is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddLast_InvalidNull_ExceptionThrown() {
        this.deque.addLast(null);
    }

    @Test
    public void testRemoveFirst() {
        this.deque.addFirst(1);

        int expectedItem = 2;
        int expectedSize = this.deque.size() + 1;
        this.deque.addFirst(expectedItem);
        assertThat(this.deque.size(), is(expectedSize));

        int actualItem = this.deque.removeFirst();
        assertThat(actualItem, is(expectedItem));
        assertThat(this.deque.size(), is(expectedSize  - 1));
    }

    @Test(expected = NoSuchElementException.class)
    public void  removeFirst_WhenEmpty_ExceptionThrown() {
        this.deque.removeFirst();
    }

    @Test
    public void testRemoveLast() {
        this.deque.addLast(1);

        int expectedItem = 2;
        int expectedSize = this.deque.size() + 1;
        this.deque.addLast(expectedItem);
        assertThat(this.deque.size(), is(expectedSize));

        int actualItem = this.deque.removeLast();
        assertThat(actualItem, is(expectedItem));
        assertThat(this.deque.size(), is(expectedSize  - 1));
    }

    @Test(expected = NoSuchElementException.class)
    public void  removeLast_WhenEmpty_ExceptionThrown() {
        this.deque.removeLast();
    }

    @Test
    public void testIterator() {
        this.deque.addFirst(2);
        this.deque.addLast(3);
        this.deque.addFirst(1);

        Iterator<Integer> actualIterator = this.deque.iterator();
        assertNotNull(actualIterator);

        int actualItem;

        assertTrue(actualIterator.hasNext());
        actualItem = actualIterator.next();
        assertThat(actualItem, is(1));

        assertTrue(actualIterator.hasNext());
        actualItem = actualIterator.next();
        assertThat(actualItem, is(2));

        assertTrue(actualIterator.hasNext());
        actualItem = actualIterator.next();
        assertThat(actualItem, is(3));

        assertFalse(actualIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void  testIterator_WhenEmpty_ExceptionThrown() {
        Iterator<Integer> actualIterator = this.deque.iterator();
        assertNotNull(actualIterator);
        actualIterator.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void  testIterator_UsingRemove_ExceptionThrown() {
        Iterator<Integer> actualIterator = this.deque.iterator();
        assertNotNull(actualIterator);
        actualIterator.remove();
    }
}
