package com.coursera.assignment5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class KdTree {
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;

    private Node root;
    private int size;
    private Point2D minPoint;
    private double minDistance;

    private class Node {
        private final Point2D point;
        private Node left;
        private Node right;
        private final boolean division;

        private Node(Point2D point, Node left, Node right, boolean division) {
            this.point = point;
            this.left = left;
            this.right = right;
            this.division = division;
        }
    }

    public KdTree() { // construct an empty set of points
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() { // is the set empty?
        return this.size == 0;
    }

    public int size() { // number of points in the set
        return this.size;
    }

    public void insert(Point2D p) { // add the point to the set (if it is not already in the set)
        if (p == null) {
            throw new IllegalArgumentException("Can not Point2D insert with null");
        }

        this.root = this.insert(root, p, VERTICAL);
    }

    private Node insert(Node node, Point2D p, boolean division) {
        if (node == null) {
            this.size += 1;
            return new Node(p, null, null, division);
        }

        if (node.point.equals(p)) {
            return node;
        }

        double cmp;
        if (division == VERTICAL)
            cmp = p.x() - node.point.x();
        else
            cmp = p.y() - node.point.y();

        if (cmp < 0)
            node.left = insert(node.left, p, !division);
        else
            node.right = insert(node.right, p, !division);

        return node;
    }

    public boolean contains(Point2D p) { // does the set contain point p?
        if (p == null) {
            throw new IllegalArgumentException("Can not Point2D contains with null");
        }
        return this.contains(root, p) != null;
    }

    private Node contains(Node node, Point2D p) {
        if (node == null) {
            return null;
        }

        if (node.point.equals(p)) {
            return node;
        }

        double cmp;
        if (node.division == VERTICAL)
            cmp = p.x() - node.point.x();
        else
            cmp = p.y() - node.point.y();

        if (cmp < 0)
            return contains(node.left, p);
        else
            return contains(node.right, p);
    }

    public void draw() { // draw all points to standard draw
        this.draw(this.root, new RectHV(0, 0, 1, 1));
    }

    private void draw(Node node, RectHV rect) {
        if (node == null) {
            return;
        }

        StdDraw.setPenColor(StdDraw.BOOK_RED);
        node.point.draw();

        if (node.division == VERTICAL) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.x(), rect.ymin(), node.point.x(), rect.ymax());
            this.draw(node.left, new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax()));
            this.draw(node.right, new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax()));
        }

        if (node.division == HORIZONTAL) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(rect.xmin(), node.point.y(), rect.xmax(), node.point.y());
            this.draw(node.left, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y()));
            this.draw(node.right, new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax()));
        }
    }


    public Iterable<Point2D> range(RectHV rect) { // all points that are inside the rectangle (or on the boundary)
        if (rect == null) {
            throw new IllegalArgumentException("Can not RectHV range with null");
        }

        Stack<Point2D> result = new Stack<>();
        this.range(this.root, new RectHV(0, 0, 1, 1), rect, result);
        return result;
    }

    private void range(Node node, RectHV nodeRect, RectHV rect, Stack<Point2D> result) {
        if (node == null) {
            return;
        }

        if (rect.contains(node.point)) {
            result.push(node.point);
        }

        RectHV leftRect = null;
        RectHV rightRect = null;

        if (node.division == VERTICAL) {
            leftRect = new RectHV(nodeRect.xmin(), nodeRect.ymin(), node.point.x(), nodeRect.ymax());
            rightRect = new RectHV(node.point.x(), nodeRect.ymin(), nodeRect.xmax(), nodeRect.ymax());
        }

        if (node.division == HORIZONTAL) {
            leftRect = new RectHV(nodeRect.xmin(), nodeRect.ymin(), nodeRect.xmax(), node.point.y());
            rightRect = new RectHV(nodeRect.xmin(), node.point.y(), nodeRect.xmax(), nodeRect.ymax());
        }

        if (rect.intersects(leftRect)) {
            this.range(node.left, leftRect, rect, result);
        }

        if (rect.intersects(rightRect)) {
            this.range(node.right, rightRect, rect, result);
        }
    }

    public Point2D nearest(Point2D p) { // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) {
            throw new IllegalArgumentException("Can not Point2D nearest with null");
        }

        this.minPoint = null;
        this.nearest(this.root, new RectHV(0, 0, 1, 1), p);
        return this.minPoint;
    }

    private void nearest(Node node, RectHV rect, Point2D p) {
        if (node == null) {
            return;
        }

        double distance = node.point.distanceSquaredTo(p);

        if (this.minPoint == null || distance < this.minDistance) {
            this.minPoint = node.point;
            this.minDistance = distance;
        }

        RectHV leftRect = null;
        RectHV rightRect = null;

        if (node.division == VERTICAL) {
            leftRect = new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax());
            rightRect = new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax());
        }

        if (node.division == HORIZONTAL) {
            leftRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
            rightRect = new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());
        }

        if (rect.intersects(leftRect)) {
            this.nearest(node.left, leftRect, p);
        }

        if (rect.intersects(rightRect)) {
            this.nearest(node.right, rightRect, p);
        }
    }

    public static void main(String[] args) { // unit testing of the methods (optional)

    }
}
