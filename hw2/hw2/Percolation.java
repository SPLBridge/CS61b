package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private class Grid {
        private class Site {
            private boolean isOpen;

            Site() {
                isOpen = false;
            }

            boolean isOpen() {
                return isOpen;
            }

            void set(boolean condition) {
                isOpen = condition;
            }
        }

        private final Site[][] sites;
        WeightedQuickUnionUF selector;
        private int sideLength;

        Grid(int N) {
            sites = new Site[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sites[i][j] = new Site();
                }
            }
            selector = new WeightedQuickUnionUF(N * N);
            sideLength = N;
        }

        int positionToNum(int x, int y) {
            return x * sideLength + y;
        }

        void set(boolean condition, int x, int y) {
            sites[x][y].set(condition);
        }

        boolean isOpen(int x, int y) {
            return sites[x][y].isOpen();
        }
    }

    private Grid grid;
    private int sideLength;
    private int[] fullSitesY;
    private int fullSitesYPointer;
    private int openSites;
    private int[] bottomSitesY;
    private int bottomSitesYpointer;
    private WeightedQuickUnionUF ufPercolates;
    private WeightedQuickUnionUF ufFull;
    private int virtualTop;
    private int virtualBottom;

    /** create N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N is supposed to be positive");
        }
        sideLength = N;
        grid = new Grid(N);
        openSites = 0;
        int totalSites = N * N;
        virtualTop = totalSites;
        virtualBottom = totalSites + 1;
        ufPercolates = new WeightedQuickUnionUF(totalSites + 2); // +2 for virtual top/bottom
        ufFull = new WeightedQuickUnionUF(totalSites + 1); // +1 for virtual top only
    }

    // Helper to convert (row, col) to 1D index
    private int xyTo1D(int row, int col) {
        return row * sideLength + col;
    }

    /** open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        if (row < 0 || row > sideLength - 1 || col < 0 || col > sideLength) {
            throw new java.lang.IndexOutOfBoundsException(
                    "both row and col are supposed to be in [0, sideLength)");
        }

        if (isOpen(row, col)) {
            return;
        }

        openSites++;
        grid.set(true, row, col);
        int idx = xyTo1D(row, col);

        if (row == 0) {
            ufPercolates.union(idx, virtualTop);
            ufFull.union(idx, virtualTop);
        }
        if (row == sideLength - 1) {
            ufPercolates.union(idx, virtualBottom);
        }
        // Connect to open neighbors in both structures
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : dirs) {
            int nr = row + d[0], nc = col + d[1];
            if (nr >= 0 && nr < sideLength && nc >= 0 && nc < sideLength && isOpen(nr, nc)) {
                int nIdx = xyTo1D(nr, nc);
                ufPercolates.union(idx, nIdx);
                ufFull.union(idx, nIdx);
            }
        }
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        return grid.isOpen(row, col);
    }

    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        int idx = xyTo1D(row, col);
        return isOpen(row, col) && ufFull.connected(idx, virtualTop);
    }

    /** number of open sites */
    public int numberOfOpenSites() {
        return openSites;
    }

    /** does the system percolate? */
    public boolean percolates() {
        return ufPercolates.connected(virtualTop, virtualBottom);
    }

    public static void main(String[] args) {

    }
}
