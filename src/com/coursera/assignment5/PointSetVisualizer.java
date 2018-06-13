package com.coursera.assignment5;

/******************************************************************************
 *  Compilation:  javac PointSetVisualizer.java
 *  Execution:    java PointSetVisualizer
 *  Dependencies: PointSet.java
 *
 *  Add the points that the user clicks in the standard draw window
 *  to a point-set and draw the resulting point-set.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class PointSetVisualizer {

    public static void main(String[] args) {
        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        StdDraw.enableDoubleBuffering();
        PointSET pointSET = new PointSET();
        while (true) {
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                StdOut.printf("%8.6f %8.6f\n", x, y);
                Point2D p = new Point2D(x, y);
                if (rect.contains(p)) {
                    StdOut.printf("%8.6f %8.6f\n", x, y);
                    pointSET.insert(p);
                    StdDraw.clear();
                    pointSET.draw();
                    StdDraw.show();
                }
            }
            StdDraw.pause(20);
        }

    }
}
