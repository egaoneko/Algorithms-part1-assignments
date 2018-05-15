package com.coursera.assignment1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class PercolationStatsTest {

    private static final int N = 200;
    private static final int TRIALS = 100;
    private PercolationStats percolationStats;

    @Before
    public void setup() {
        this.percolationStats = new PercolationStats(N, TRIALS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPercolationStats_InvalidN_ExceptionThrown() {
        new PercolationStats(0, TRIALS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPercolationStats_InvalidTrial_ExceptionThrown() {
        new PercolationStats(N, 0);
    }

    @Test
    public void testMean() {
        double epsilon = 0.001;
        double expected = 0.5929934999999997;
        double actual = this.percolationStats.mean();
        assertTrue(Math.abs(expected - actual) < epsilon);
    }

    @Test
    public void testStddev() {
        double epsilon = 0.002;
        double expected = 0.00876990421552567;
        double actual = this.percolationStats.stddev();
        assertTrue(Math.abs(expected - actual) < epsilon);
    }

    @Test
    public void testConfidenceLo() {
        double epsilon = 0.001;
        double expected = 0.5912745987737567;
        double actual = this.percolationStats.confidenceLo();
        assertTrue(Math.abs(expected - actual) < epsilon);
    }

    @Test
    public void testConfidenceHi() {
        double epsilon = 0.001;
        double expected = 0.5947124012262428;
        double actual = this.percolationStats.confidenceHi();
        assertTrue(Math.abs(expected - actual) < epsilon);
    }
}
