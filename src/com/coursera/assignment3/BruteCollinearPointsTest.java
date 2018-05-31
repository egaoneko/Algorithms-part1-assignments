package com.coursera.assignment3;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BruteCollinearPointsTest {

    private BruteCollinearPoints bruteCollinearPoints;
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
        this.bruteCollinearPoints = new BruteCollinearPoints(POINTS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBruteCollinearPoints_InvalidNull_ExceptionThrown() {
        new BruteCollinearPoints(null);
    }

    @Test
    public void testNumberOfSegments() {
        assertThat(this.bruteCollinearPoints.numberOfSegments(), is(6));
    }

    @Test
    public void testSegments() {
        LineSegment[] actualSegments = this.bruteCollinearPoints.segments();

        assertThat(actualSegments.length, is(6));
    }
}
