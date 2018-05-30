package com.coursera.assignment3;

import java.util.*;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        if (points == null) {
            throw new IllegalArgumentException("Can not BruteCollinearPoints constructor with null");
        }

        this.segments = this.getSegments(points);
    }

    private LineSegment[] getSegments(Point[] points) {
        Map<Double, LineSegment> map = new HashMap<>();

        for (Point p : points) {
            for (Point q : points) {
                for (Point r : points) {
                    for (Point s : points) {

                        Set<Point> set = new HashSet<>();
                        set.add(p);
                        set.add(q);
                        set.add(r);
                        set.add(s);

                        if (set.size() != 4) {
                            continue;
                        }

                        double slopePQ = p.slopeTo(q);
                        double slopeRS = r.slopeTo(s);
                        double slopeQR = q.slopeTo(r);

                        if (slopePQ != slopeQR || slopeQR != slopeRS) {
                            continue;
                        }

                        if (!this.isAbleSlope(slopePQ)) {
                            continue;
                        }

                        if (map.containsKey(slopePQ)) {
                            continue;
                        }

                        Point[] segmentPoints = set.toArray(new Point[0]);
                        Arrays.sort(segmentPoints);

                        map.put(slopePQ, new LineSegment(segmentPoints[0], segmentPoints[3]));
                    }
                }
            }
        }

        return map.values().toArray(new LineSegment[0]);
    }

    private boolean isAbleSlope(double slope) {
        if (slope == Double.NEGATIVE_INFINITY) {
            return false;
        }

        if (slope == Double.POSITIVE_INFINITY) {
            return false;
        }

        if (slope == 0.0) {
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
}
