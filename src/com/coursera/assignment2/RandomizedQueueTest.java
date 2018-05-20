package com.coursera.assignment2;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class RandomizedQueueTest {

    private RandomizedQueue<Integer> randomizedQueue;

    @Before
    public void setup() {
        this.randomizedQueue = new RandomizedQueue<>();
    }

    @Test
    public void testIsEmpty() {
        assertThat(this.randomizedQueue.isEmpty(), is(true));
    }

    @Test
    public void testSize() {
        assertThat(this.randomizedQueue.size(), is(0));
    }

    @Test
    public void testEnqueue() {
        int item = 1;
        int expected = this.randomizedQueue.size() + 1;
        this.randomizedQueue.enqueue(item);
        assertThat(this.randomizedQueue.size(), is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEnqueue_InvalidNull_ExceptionThrown() {
        this.randomizedQueue.enqueue(null);
    }

    @Test
    public void testDequeue() {
        Set<Integer> expected = new HashSet<>();

        for (int i = 0; i < 5; i++) {
            this.randomizedQueue.enqueue(i);
            expected.add(i);
        }

        assertThat(this.randomizedQueue.size(), is(expected.size()));

        for (int i = 0; i < 5; i++) {
            int item = this.randomizedQueue.dequeue();
            assertTrue(expected.contains(item));
            expected.remove(item);
            assertThat(this.randomizedQueue.size(), is(expected.size()));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void  testDequeue_WhenEmpty_ExceptionThrown() {
        this.randomizedQueue.dequeue();
    }

    @Test
    public void testSample() {
        Set<Integer> expected = new HashSet<>();

        for (int i = 0; i < 5; i++) {
            this.randomizedQueue.enqueue(i);
            expected.add(i);
        }

        assertThat(this.randomizedQueue.size(), is(expected.size()));

        for (int i = 0; i < 5; i++) {
            int item = this.randomizedQueue.sample();
            assertTrue(expected.contains(item));
            assertThat(this.randomizedQueue.size(), is(expected.size()));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void  testSample_WhenEmpty_ExceptionThrown() {
        this.randomizedQueue.sample();
    }

    @Test
    public void testIterator() {
        Set<Integer> expected = new HashSet<>();

        for (int i = 0; i < 5; i++) {
            this.randomizedQueue.enqueue(i);
            expected.add(i);
        }

        Iterator<Integer> actualIterator = this.randomizedQueue.iterator();
        assertNotNull(actualIterator);

        int actualItem;

        for (int i = 0; i < 5; i++) {
            assertTrue(actualIterator.hasNext());
            actualItem = actualIterator.next();
            assertTrue(expected.contains(actualItem));
            expected.remove(actualItem);
        }

        assertFalse(actualIterator.hasNext());
        assertThat(expected.size(), is(0));
    }

    @Test(expected = NoSuchElementException.class)
    public void  testIterator_WhenEmpty_ExceptionThrown() {
        Iterator<Integer> actualIterator = this.randomizedQueue.iterator();
        assertNotNull(actualIterator);
        actualIterator.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void  testIterator_UsingRemove_ExceptionThrown() {
        Iterator<Integer> actualIterator = this.randomizedQueue.iterator();
        assertNotNull(actualIterator);
        actualIterator.remove();
    }
}
