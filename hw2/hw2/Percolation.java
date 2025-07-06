package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private class Grid {
        private class Site {
            private boolean isOpen;
            private int num;

            Site() {
                isOpen = false;
            }

            Site(int number) {
                isOpen = false;
                num = number;
            }

            boolean isOpen() {
                return isOpen;
            }

            int num() {
                return num;
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
                    int num = positionToNum(i, j);
                    sites[i][j] = new Site(num);
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

        boolean connected(int x1, int y1, int x2, int y2) {
            int num1 = positionToNum(x1, y1);
            int num2 = positionToNum(x2, y2);
            return selector.connected(num1, num2);
        }

        void union(int x1, int y1, int x2, int y2) {
            int num1 = positionToNum(x1, y1);
            int num2 = positionToNum(x2, y2);
            selector.union(num1, num2);
        }
    }

    private Grid grid;
    private int sideLength;
    private int[] fullSitesY;
    private int fullSitesYPointer;
    private int openSites;
    private int[] bottomSitesY;
    private int bottomSitesYpointer;

    /** create N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N is supposed to be positive");
        }
        sideLength = N;
        grid = new Grid(N);
        fullSitesY = new int[N];
        fullSitesYPointer = 0;
        openSites = 0;
        bottomSitesY = new int[N];
        fullSitesYPointer = 0;
    }

    void addFullSite(int y) {
        fullSitesY[fullSitesYPointer] = y;
        fullSitesYPointer++;
    }

    void addBottomSite(int y) {
        bottomSitesY[bottomSitesYpointer] = y;
        bottomSitesYpointer++;
    }

    /** open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        if (row < 0 || row > sideLength - 1 || col < 0 || col > sideLength) {
            throw new java.lang.IndexOutOfBoundsException("both row and col are supposed to be in [0, sideLength)");
        }

        if (isOpen(row, col)) {
            return;
        }

        openSites++;
        grid.set(true, row, col);

        if (row == 0) {
            addFullSite(col);
        }
        if (row == sideLength - 1) {
            addBottomSite(col);
        }
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            grid.union(row, col, row - 1, col);
        }
        if (row + 1 < sideLength && isOpen(row + 1, col)) {
            grid.union(row, col, row + 1, col);
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            grid.union(row, col, row, col - 1);
        }
        if (col + 1 < sideLength && isOpen(row, col + 1)) {
            grid.union(row, col, row, col + 1);
        }
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        return grid.isOpen(row, col);
    }

    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        for (int i = 0; i < fullSitesYPointer; i++) {
            if (grid.connected(0, fullSitesY[i], row, col)) {
                return true;
            }
        }
        return false;
    }

    /** number of open sites */
    public int numberOfOpenSites() {
        return openSites;
    }

    /** does the system percolate? */
    public boolean percolates() {
        for (int i = 0; i < bottomSitesYpointer; i++) {
            if (isFull(sideLength - 1, bottomSitesY[i])) {
                return true;
            }
        }
        return false;
    }
}
