import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Class that performs a series of computational experiments.
 */
public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        double[] opened = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            do {
                percolation.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            } while (!percolation.percolates());
            opened[i] = ((double) percolation.numberOfOpenSites()) / (n * n);
        }
        mean = StdStats.mean(opened);
        stddev = StdStats.stddev(opened);

        double partCalculation = (1.96 * stddev()) / Math.sqrt(trials);
        confidenceLo = mean - partCalculation;
        confidenceHi = mean + partCalculation;
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    /**
     * Takes two command-line arguments n and T, performs T independent
     * computational experiments on an n-by-n grid, and prints the sample mean,
     * sample standard deviation, and the 95% confidence interval
     * for the percolation threshold.
     */
    public static void main(String[] args) {
        int first = Integer.parseInt(args[0]);
        int second = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(first, second);
        double mean = percolationStats.mean();
        double stddev = percolationStats.stddev();
        double confidenceLo = percolationStats.confidenceLo();
        double confidenceHi = percolationStats.confidenceHi();

        System.out.println(String.format("%% java PercolationStats %d %d", first, second));
        System.out.println(String.format("mean                    = %f", mean));
        System.out.println(String.format("stddev                  = %f", stddev));
        System.out.println(String.format("95%% confidence interval = [%f, %f]", confidenceLo, confidenceHi));
    }
}
