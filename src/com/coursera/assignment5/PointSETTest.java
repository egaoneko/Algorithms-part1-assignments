package com.coursera.assignment5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PointSETTest {

    private PointSET pointSET;

    @Before
    public void setUp() {
        this.pointSET = new PointSET();
    }

    @Test
    public void testIsEmpty() {
        assertThat(this.pointSET.isEmpty(), is(true));
    }

    @Test
    public void testSize() {
        assertThat(this.pointSET.size(), is(0));
    }

    @Test
    public void testInsert() {
        assertThat(this.pointSET.size(), is(0));
        this.pointSET.insert(new Point2D(0, 0));
        assertThat(this.pointSET.size(), is(1));
        this.pointSET.insert(new Point2D(0, 0));
        assertThat(this.pointSET.size(), is(1));
        this.pointSET.insert(new Point2D(0, 1));
        assertThat(this.pointSET.size(), is(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsert_InvalidNull_ExceptionThrown() {
        this.pointSET.insert(null);
    }

    @Test
    public void testContains() {
        this.pointSET.insert(new Point2D(0, 0));
        assertThat(this.pointSET.contains(new Point2D(0, 0)), is(true));
        assertThat(this.pointSET.contains(new Point2D(0, 1)), is(false));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testContains_InvalidNull_ExceptionThrown() {
        this.pointSET.contains(null);
    }

    @Test
    public void testRange() {
        this.pointSET.insert(new Point2D(0, 0));
        this.pointSET.insert(new Point2D(1, 0));
        this.pointSET.insert(new Point2D(0, 1));
        this.pointSET.insert(new Point2D(1, 1));
        this.pointSET.insert(new Point2D(2, 2));

        Set<Point2D> expected = new HashSet<>();
        expected.add(new Point2D(0, 0));
        expected.add(new Point2D(1, 0));
        expected.add(new Point2D(0, 1));
        expected.add(new Point2D(1, 1));

        RectHV rect = new RectHV(0, 0, 1, 1);

        Stack<Point2D> actual = (Stack<Point2D>) this.pointSET.range(rect);
        assertThat(actual.size(), is(4));
        for (Point2D point: actual) {
            assertThat(expected.contains(point), is(true));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRange_InvalidNull_ExceptionThrown() {
        this.pointSET.range(null);
    }

    @Test
    public void testNearest() {
        Point2D actual = this.pointSET.nearest(new Point2D(0, 0));
        assertNull(actual);

        this.pointSET.insert(new Point2D(0, 0));
        this.pointSET.insert(new Point2D(1, 0));
        this.pointSET.insert(new Point2D(0, 1));
        this.pointSET.insert(new Point2D(1, 1));
        this.pointSET.insert(new Point2D(2, 2));

        actual = this.pointSET.nearest(new Point2D(0, 0));
        assertThat(actual, is(new Point2D(0, 0)));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNearest_InvalidNull_ExceptionThrown() {
        this.pointSET.nearest(null);
    }
}