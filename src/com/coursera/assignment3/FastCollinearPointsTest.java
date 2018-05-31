package com.coursera.assignment3;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FastCollinearPointsTest {
    private FastCollinearPoints fastCollinearPoints;
    private static Point[] POINTS;

    @Before
    public void setup() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));
        points.add(new Point(5, 5));
        points.add(new Point(1, 5));
        points.add(new Point(2, 4));
        points.add(new Point(4, 2));
        points.add(new Point(5, 1));

        POINTS = points.toArray(new Point[0]);
        this.fastCollinearPoints = new FastCollinearPoints(POINTS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFastCollinearPoints_InvalidNull_ExceptionThrown() {
        new FastCollinearPoints(null);
    }

    @Test
    public void testNumberOfSegments() {
        assertThat(this.fastCollinearPoints.numberOfSegments(), is(2));
    }

    @Test
    public void testSegments() {
        LineSegment[] actualSegments = this.fastCollinearPoints.segments();

        assertThat(actualSegments.length, is(2));
        assertThat(actualSegments[0].toString(), is("(1, 1) -> (5, 5)"));
        assertThat(actualSegments[1].toString(), is("(5, 1) -> (1, 5)"));
    }
}
