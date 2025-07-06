package hw2;

public class PercolationStats {
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException(
                    "both N and T are supposed to be positive");
        }
    }

    public double mean() {
        return 0;
    }

    public double stddev() {
        return 0;
    }

    public double confidenceLow() {
        return 0;
    }

    public double confidenceHigh() {
        return 0;
    }
}
