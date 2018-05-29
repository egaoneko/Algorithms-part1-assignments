package com.coursera.assignment3;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

        POINTS = points.toArray(new Point[0]);
        this.bruteCollinearPoints = new BruteCollinearPoints(POINTS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBruteCollinearPoints_InvalidNull_ExceptionThrown() {
        new BruteCollinearPoints(null);
    }

    @Test
    public void testNumberOfSegments() {
        assertThat(this.bruteCollinearPoints.numberOfSegments(), is(1));
    }

    @Test
    public void testSegments() {
        LineSegment[] expectedSegments = this.bruteCollinearPoints.segments();

        assertThat(expectedSegments.length, is(1));
        assertThat(expectedSegments[0].toString(), is(new LineSegment(POINTS[0], POINTS[3]).toString()));
    }
}
