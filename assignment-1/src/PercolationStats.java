import  edu.princeton.cs.algs4.StdRandom;
import  edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    
    private final double[] fractions;
    private final int numbOfTrials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        numbOfTrials = trials;
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        
        fractions = new double[numbOfTrials];

        for (int i = 0; i < numbOfTrials; i++) {
            Percolation p = new Percolation(n);

            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n);
                int col = StdRandom.uniform(1, n);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }
            double fraction = (double) p.numberOfOpenSites() / (n * n);
            fractions[i] = fraction;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(numbOfTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(numbOfTrials));
    }

    // test client (see below)
    public static void main(String[] args) {
        String n = args[0];
        String trials = args[1];

        PercolationStats Ps = new PercolationStats(Integer.parseInt(n), Integer.parseInt(trials));
        System.out.println("Mean =                              " + Ps.mean());
        System.out.println("stddev =                            " + Ps.stddev());
        System.out.println("95% confidence interval =          [" + Ps.confidenceLo() + "," + Ps.confidenceHi() + "]");
    }

}