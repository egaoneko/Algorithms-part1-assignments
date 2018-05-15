import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;

    private final int n;
    private final int trials;
    private final double[] thresholds;
    private final double mean;
    private final double stddev;

    public PercolationStats(int n, int trials) { // perform trials independent experiments on an n-by-n grid
        if (n < 1) {
            throw new IllegalArgumentException("Invalid n : " + n);
        }

        if (trials < 1) {
            throw new IllegalArgumentException("Invalid trials : " + trials);
        }

        this.n = n;
        this.trials = trials;
        this.thresholds = new double[this.trials];
        this.performPercolations();

        this.mean = StdStats.mean(this.thresholds);
        this.stddev = StdStats.stddev(this.thresholds);
    }

    private void performPercolations() {
        for (int t = 0; t < this.trials; t++) {
            this.thresholds[t] = this.performPercolation();
        }
    }

    private double performPercolation() {
        Percolation percolation = new Percolation(this.n);

        while (!percolation.percolates()) {
            int row = StdRandom.uniform(this.n) + 1;
            int col = StdRandom.uniform(this.n) + 1;

            if (percolation.isOpen(row, col)) {
                continue;
            }
            percolation.open(row, col);
        }

        return ((double) percolation.numberOfOpenSites()) / (this.n * this.n);
    }

    public double mean() { // sample mean of percolation threshold
        return this.mean;
    }

    public double stddev() { // sample standard deviation of percolation threshold
        return this.stddev;
    }

    public double confidenceLo() { // low  endpoint of 95% confidence interval
        return this.mean() - (CONFIDENCE_95 * this.stddev() / Math.sqrt(this.trials));
    }

    public double confidenceHi() { // high endpoint of 95% confidence interval
        return this.mean() + (CONFIDENCE_95 * this.stddev() / Math.sqrt(this.trials));
    }

    public static void main(String[] args) { // test client (described below)
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        PercolationStats percolationStats = new PercolationStats(n, trials);
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println(
                "95% confidence interval = [" +
                        percolationStats.confidenceLo() +
                        ", " +
                        percolationStats.confidenceHi()
                        + "]"
        );
    }
}
