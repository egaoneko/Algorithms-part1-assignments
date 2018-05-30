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

        int len = points.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    for (int m = k + 1; m < len; m++) {
                        Point p = points[i], q = points[j], r = points[k], s = points[m];

                        double slopePQ = p.slopeTo(q);
                        double slopeRS = r.slopeTo(s);
                        double slopeQR = q.slopeTo(r);

                        if (slopePQ != slopeQR || slopeQR != slopeRS) {
                            continue;
                        }

                        if (map.containsKey(slopePQ)) {
                            continue;
                        }

                        Set<Point> set = new HashSet<>();
                        set.add(p);
                        set.add(q);
                        set.add(r);
                        set.add(s);

                        Point[] segmentPoints = set.toArray(new Point[0]);
                        Arrays.sort(segmentPoints);

                        map.put(slopePQ, new LineSegment(segmentPoints[0], segmentPoints[3]));
                    }
                }
            }
        }

        return map.values().toArray(new LineSegment[0]);
    }

    public int numberOfSegments() { // the number of line segments
        return segments.length;
    }

    public LineSegment[] segments() { // the line segments
        return segments;
    }
}
