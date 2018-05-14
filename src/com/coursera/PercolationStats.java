package com.coursera;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    public PercolationStats(int n, int trials) { // perform trials independent experiments on an n-by-n grid
    }

    public double mean() { // sample mean of percolation threshold
        return 0;
    }

    public double stddev() { // sample standard deviation of percolation threshold
        return 0;
    }

    public double confidenceLo() { // low  endpoint of 95% confidence interval
        return 0;
    }

    public double confidenceHi() { // high endpoint of 95% confidence interval
        return 0;
    }

    public static void main(String[] args) { // test client (described below)
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        StdOut.println(n + " " + trials);
    }
}
