package com.coursera.assignment3;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PointTest {

    private Point point;

    @Before
    public void setup() {
        this.point = new Point(0, 0);
    }

    @Test
    public void testSlopeTo() {
        assertThat(this.point.slopeTo(new Point(2, 2)), is(1.0));
    }

    @Test
    public void testSlopeTo_HorizontalLine() {
        assertThat(this.point.slopeTo(new Point(2, 0)), is(+0.0));
    }

    @Test
    public void testSlopeTo_VerticalLine() {
        assertThat(this.point.slopeTo(new Point(0, 2)), is(Double.POSITIVE_INFINITY));
    }

    @Test
    public void testSlopeTo_Point() {
        assertThat(this.point.slopeTo(new Point(0, 0)), is(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void testCompareTo_XYMore() {
        assertThat(this.point.compareTo(new Point(2, 2)), is(-1));
    }

    @Test
    public void testCompareTo_YMoreXLess() {
        assertThat(this.point.compareTo(new Point(-2, 2)), is(-1));
    }

    @Test
    public void testCompareTo_XMoreYEqual() {
        assertThat(this.point.compareTo(new Point(2, 0)), is(-1));
    }

    @Test
    public void testCompareTo_YMoreXEqual() {
        assertThat(this.point.compareTo(new Point(0, 2)), is(-1));
    }

    @Test
    public void testCompareTo_Equal() {
        assertThat(this.point.compareTo(new Point(0, 0)), is(0));
    }

    @Test
    public void testCompareTo_XLessYEqual() {
        assertThat(this.point.compareTo(new Point(-2, 0)), is(1));
    }

    @Test
    public void testCompareTo_YLessXEqual() {
        assertThat(this.point.compareTo(new Point(0, -2)), is(1));
    }

    @Test
    public void testCompareTo_MoreLeft() {
        assertThat(this.point.compareTo(new Point(-2, -2)), is(1));
    }

    @Test
    public void testCompareTo_MoreRight() {
        assertThat(this.point.compareTo(new Point(2, -2)), is(1));
    }

    @Test
    public void testSlopeOrder() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(2, 4));
        points.add(new Point(4, 2));
        points.add(new Point(2, 2));
        points.add(new Point(2, 2));

        Collections.sort(points);

        assertThat(points.get(0).compareTo(new Point(2, 2)), is(0));
        assertThat(points.get(1).compareTo(new Point(2, 2)), is(0));
        assertThat(points.get(2).compareTo(new Point(4, 2)), is(0));
        assertThat(points.get(3).compareTo(new Point(2, 4)), is(0));
    }
}
