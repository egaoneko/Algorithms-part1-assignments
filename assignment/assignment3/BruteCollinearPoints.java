import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) { // finds all line lineSegments containing 4 points
        if (points == null) {
            throw new IllegalArgumentException("Can not BruteCollinearPoints constructor with null");
        }

        this.lineSegments = this.getSegments(points);
    }

    private LineSegment[] getSegments(Point[] points) {
        List<LineSegment> segmentList = new ArrayList<>();
        int len = points.length;
        Arrays.sort(points);

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    for (int m = k + 1; m < len; m++) {
                        Point p = points[i], q = points[j], r = points[k], s = points[m];

                        double slopePQ = p.slopeTo(q);
                        double slopeRS = r.slopeTo(s);
                        double slopeQR = q.slopeTo(r);

                        if (Double.compare(slopePQ, slopeQR) != 0 || Double.compare(slopeQR, slopeRS) != 0) {
                            continue;
                        }

                        segmentList.add(new LineSegment(p, s));
                    }
                }
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

    public int numberOfSegments() { // the number of line lineSegments
        return lineSegments.length;
    }

    public LineSegment[] segments() { // the line lineSegments
        return Arrays.copyOf(lineSegments, this.numberOfSegments());
    }
}
