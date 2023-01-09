// Estimates percolation threshold for an N-by-N percolation system.
public class PercolationStats {
    private float [] p;
    // Performs T independent experiments (Monte Carlo simulations) on an 
    // N-by-N grid.
    public PercolationStats(int N, int T) {
        p = new float [T];
        int size = N * N;
        int rand, rand1;
        double cal = 0;
        Percolation perc;
        for (int a = 0; a < T; a++) {
            perc = new Percolation(N);
            do 
            {
                rand = StdRandom.uniform(N);
                rand1 = StdRandom.uniform(N);
                perc.open(rand, rand1);
            } while(!perc.percolates());
            p[a] = (float) perc.numberOfOpenSites() / size;
        }
    }
    
    // Returns sample mean of percolation threshold.
    public double mean() {
        double sum = 0.0;
        for (int c = 0; c < p.length; c++) {
            sum += p[c];
        }
        return sum/p.length;
    }

    // Returns sample standard deviation of percolation threshold.
    public double stddev() {
        double sum1 = 0.0;
        double x = 0.0;
        for (int c = 0; c < p.length; c++) {
            x = p[c] - mean();
            sum1 += x*x;
        }
        return Math.sqrt(sum1)/p.length;
    }

    // Returns low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        return mean() - (1.96 * stddev()/Math.sqrt(p.length));
    }

    // Returns high endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()/Math.sqrt(p.length));
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.printf("mean           = %f\n", stats.mean());
        StdOut.printf("stddev         = %f\n", stats.stddev());
        StdOut.printf("confidenceLow  = %f\n", stats.confidenceLow());
        StdOut.printf("confidenceHigh = %f\n", stats.confidenceHigh());
    }
}
