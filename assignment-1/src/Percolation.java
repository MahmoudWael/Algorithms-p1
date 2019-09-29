import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private final WeightedQuickUnionUF wQuickUnionF;
    private int openCount;
    private int gSize;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        gSize = n;
        grid = new boolean[n][n];

        wQuickUnionF = new WeightedQuickUnionUF(n * n + 2);
        openCount = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(!isOpen(row, col)){
            grid[row - 1][col - 1] = true;
            openCount++;
        }
        if (row > 0 && isOpen(row - 1, col)) {
            wQuickUnionF.union(getLocation(row, col), getLocation(row - 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            wQuickUnionF.union(getLocation(row, col), getLocation(row, col - 1));
        }
        if (row < gSize && isOpen(row + 1, col)) {
            wQuickUnionF.union(getLocation(row, col), getLocation(row + 1, col));
        }
        if (col < gSize && isOpen(row, col + 1)) {
            wQuickUnionF.union(getLocation(row, col), getLocation(row, col + 1));
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        boolean openG;
        try {
             openG =  grid[row - 1 ][col - 1];
        } catch (IndexOutOfBoundsException e) {
            openG = false;
        }
        return openG;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (isOpen(row, col)) {
            for (int i = 0; i < gSize ; i++) {
                if (wQuickUnionF.connected(getLocation(row, col), i)) {
                    return true;
                }
            }
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < gSize; i++) {
            if (isFull(gSize - 1, i)) {
                return true;
            }
        }
        return false;
    }

    private int getLocation(int row, int col) {
        return (row - 1) * gSize + col;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        p.open(1, 1);
        p.open(1, 2);
        p.open(2, 2);
        p.open(3, 2);

        System.out.println(p.percolates());
    }

}