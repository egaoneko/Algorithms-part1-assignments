package com.coursera.assignment5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class KdTreeTest {
    private KdTree kdTree;

    @Before
    public void setUp() {
        this.kdTree = new KdTree();
    }

    @Test
    public void testIsEmpty() {
        assertThat(this.kdTree.isEmpty(), is(true));
    }

    @Test
    public void testSize() {
        assertThat(this.kdTree.size(), is(0));
    }

    @Test
    public void testInsert() {
        assertThat(this.kdTree.size(), is(0));
        this.kdTree.insert(new Point2D(0, 0));
        assertThat(this.kdTree.size(), is(1));
        this.kdTree.insert(new Point2D(0, 0));
        assertThat(this.kdTree.size(), is(1));
        this.kdTree.insert(new Point2D(0, 1));
        assertThat(this.kdTree.size(), is(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsert_InvalidNull_ExceptionThrown() {
        this.kdTree.insert(null);
    }

    @Test
    public void testContains() {
        this.kdTree.insert(new Point2D(0.7, 0.2));
        this.kdTree.insert(new Point2D(0.5, 0.4));
        this.kdTree.insert(new Point2D(0.2, 0.3));
        this.kdTree.insert(new Point2D(0.4, 0.7));
        this.kdTree.insert(new Point2D(0.9, 0.6));
        assertThat(this.kdTree.contains(new Point2D(0.9, 0.6)), is(true));
        assertThat(this.kdTree.contains(new Point2D(0, 1)), is(false));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testContains_InvalidNull_ExceptionThrown() {
        this.kdTree.contains(null);
    }

    @Test
    public void testRange() {
        this.kdTree.insert(new Point2D(0.0, 0.0));
        this.kdTree.insert(new Point2D(0.1, 0.0));
        this.kdTree.insert(new Point2D(0.0, 0.1));
        this.kdTree.insert(new Point2D(0.1, 0.1));
        this.kdTree.insert(new Point2D(0.2, 0.2));

        Set<Point2D> expected = new HashSet<>();
        expected.add(new Point2D(0.0, 0.0));
        expected.add(new Point2D(0.1, 0.0));
        expected.add(new Point2D(0.0, 0.1));
        expected.add(new Point2D(0.1, 0.1));

        RectHV rect = new RectHV(0.0, 0.0, 0.1, 0.1);

        Stack<Point2D> actual = (Stack<Point2D>) this.kdTree.range(rect);
        assertThat(actual.size(), is(4));
        for (Point2D point: actual) {
            assertThat(expected.contains(point), is(true));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRange_InvalidNull_ExceptionThrown() {
        this.kdTree.range(null);
    }

    @Test
    public void testNearest() {
        Point2D actual = this.kdTree.nearest(new Point2D(0.0, 0.0));
        assertNull(actual);

        this.kdTree.insert(new Point2D(0.0, 0.0));
        this.kdTree.insert(new Point2D(0.1, 0.0));
        this.kdTree.insert(new Point2D(0.0, 0.1));
        this.kdTree.insert(new Point2D(0.1, 0.1));
        this.kdTree.insert(new Point2D(0.2, 0.2));

        actual = this.kdTree.nearest(new Point2D(0.0, 0.0));
        assertThat(actual, is(new Point2D(0.0, 0.0)));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNearest_InvalidNull_ExceptionThrown() {
        this.kdTree.nearest(null);
    }
}
