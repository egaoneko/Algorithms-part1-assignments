package com.coursera.assignment5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Stack;
import java.util.TreeSet;

public class PointSET {
    private final TreeSet<Point2D> pointSet;

    public PointSET() { // construct an empty set of points
        this.pointSet = new TreeSet<>();
    }

    public boolean isEmpty() { // is the set empty?
        return this.pointSet.isEmpty();
    }

    public int size() { // number of points in the set
        return this.pointSet.size();
    }

    public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
        if (p == null) {
            throw new IllegalArgumentException("Can not Point2D insert with null");
        }
        this.pointSet.add(p);
    }

    public boolean contains(Point2D p) { // does the set contain point p?
        if (p == null) {
            throw new IllegalArgumentException("Can not Point2D contains with null");
        }
        return this.pointSet.contains(p);
    }

    public void draw() { // draw all points to standard draw
        this.pointSet.forEach(Point2D::draw);
    }

    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary)
        if (rect == null) {
            throw new IllegalArgumentException("Can not RectHV range with null");
        }
        Stack<Point2D> result = new Stack<>();
        for (Point2D point : this.pointSet) {
            if (!rect.contains(point)) {
                continue;
            }
            result.push(point);
        }
        return result;
    }

    public Point2D nearest(Point2D p) { // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) {
            throw new IllegalArgumentException("Can not Point2D nearest with null");
        }
        Point2D minPoint = null;
        double minDistance = 0;
        for (Point2D point : this.pointSet) {
            double distance = point.distanceSquaredTo(p);
            if (minPoint == null || distance < minDistance) {
                minPoint = point;
                minDistance = distance;
            }
        }
        return minPoint;
    }

    public static void main(String[] args) { // unit testing of the methods (optional)

    }
}
