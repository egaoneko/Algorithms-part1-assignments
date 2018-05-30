package com.coursera.assignment3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class FastCollinearPoints {
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 or more points
        if (points == null) {
            throw new IllegalArgumentException("Can not FastCollinearPoints constructor with null");
        }

        this.segments = this.getSegments(points);
    }

    private LineSegment[] getSegments(Point[] points) {
        Map<String, LineSegment> map = new HashMap<>();

        int len = points.length;
        for (Point p : points) {
            Point[] copiedPoints = Arrays.copyOf(points, len);
            Set<Point> set = new HashSet<>();
            Arrays.sort(copiedPoints, p.slopeOrder());

            double prevSlope = 0;
            Point prevPoint = null;
            for (Point q : copiedPoints) {
                double slope = p.slopeTo(q);

                if (this.isAbleSlope(slope) && prevSlope == slope) {
                    if (set.size() == 0) {
                        set.add(prevPoint);
                    }
                    set.add(q);
                } else {
                    if (set.size() >= 3) {
                        set.add(p);
                        Point[] segmentPoints = set.toArray(new Point[0]);
                        Arrays.sort(segmentPoints);
                        LineSegment lineSegment = new LineSegment(
                                segmentPoints[0],
                                segmentPoints[segmentPoints.length - 1]
                        );
                        if (!map.containsKey(lineSegment.toString())) {
                            map.put(lineSegment.toString(), lineSegment);
                        }
                    }
                    set.clear();
                }
                prevSlope = slope;
                prevPoint = q;
            }
            if (set.size() >= 3) {
                set.add(p);
                Point[] segmentPoints = set.toArray(new Point[0]);
                Arrays.sort(segmentPoints);
                LineSegment lineSegment = new LineSegment(
                        segmentPoints[0],
                        segmentPoints[segmentPoints.length - 1]
                );
                if (!map.containsKey(lineSegment.toString())) {
                    map.put(lineSegment.toString(), lineSegment);
                }
            }
        }
        return map.values().toArray(new LineSegment[0]);
    }

    private boolean isAbleSlope(double slope) {
        if (slope == Double.NEGATIVE_INFINITY) {
            return false;
        }

        return true;
    }

    public int numberOfSegments() { // the number of line segments
        return segments.length;
    }

    public LineSegment[] segments() { // the line segments
        return segments;
    }

    public static void main(String[] args) {

        // read the n points from a file
//        In in = new In(args[0]);
        In in = new In("/collinear/input6.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
