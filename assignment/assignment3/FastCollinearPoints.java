import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) { // finds all line lineSegments containing 4 or more points
        if (points == null) {
            throw new IllegalArgumentException("Can not FastCollinearPoints constructor with null");
        }

        this.lineSegments = this.getSegments(points);
    }

    private LineSegment[] getSegments(Point[] points) {
        List<LineSegment> segmentList = new ArrayList<>();
        int len = points.length;
        Arrays.sort(points);

        for (Point p : points) {
            Point[] copiedPoints = Arrays.copyOf(points, len);
            List<Point> pointList = new ArrayList<>();
            Arrays.sort(copiedPoints, p.slopeOrder());

            double prevSlope = 0.0;
            Point prevPoint = null;
            for (Point q : copiedPoints) {
                double slope = p.slopeTo(q);
                boolean isCollinear = isCollinear(prevSlope, slope);

                if (isCollinear) {
                    if (prevPoint != null && pointList.isEmpty()) {
                        pointList.add(prevPoint);
                    }
                    pointList.add(q);
                }

                if (!isCollinear) {
                    if (pointList.size() >= 3) {
                        pointList.add(p);
                        Point[] segmentPoints = pointList.toArray(new Point[0]);
                        Arrays.sort(segmentPoints);
                        LineSegment lineSegment = new LineSegment(
                                segmentPoints[0],
                                segmentPoints[segmentPoints.length - 1]
                        );
                        segmentList.add(lineSegment);
                    }
                    pointList.clear();
                }
                prevSlope = slope;
                prevPoint = q;
            }
            if (pointList.size() >= 3) {
                pointList.add(p);
                Point[] segmentPoints = pointList.toArray(new Point[0]);
                Arrays.sort(segmentPoints);
                LineSegment lineSegment = new LineSegment(
                        segmentPoints[0],
                        segmentPoints[segmentPoints.length - 1]
                );
                segmentList.add(lineSegment);
            }
        }
        List<String> keys = new ArrayList<>();

        return segmentList
                .stream()
                .filter(lineSegment -> {
                    if (keys.contains(lineSegment.toString())) {
                        return false;
                    }
                    keys.add(lineSegment.toString());
                    return true;
                })
                .toArray(LineSegment[]::new);
    }

    private boolean isCollinear(double prevSlope, double slope) {
        return this.isAbleSlope(slope) && Double.compare(prevSlope, slope) == 0;
    }

    private boolean isAbleSlope(double slope) {
        return slope != Double.NEGATIVE_INFINITY;
    }

    public int numberOfSegments() { // the number of line lineSegments
        return lineSegments.length;
    }

    public LineSegment[] segments() { // the line lineSegments
        return Arrays.copyOf(lineSegments, this.numberOfSegments());
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
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

        // print and draw the line lineSegments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
