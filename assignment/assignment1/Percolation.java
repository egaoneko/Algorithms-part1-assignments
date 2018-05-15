import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF grid;
    private final boolean[] sites;

    private final int gridSize;
    private final int blocks;
    private int openSitesSize = 0;

    public Percolation(int gridSize) { // create gridSize-by-gridSize grid, with all sites blocked
        if (gridSize < 1) {
            throw new IllegalArgumentException("Invalid grid size : " + gridSize);
        }

        this.gridSize = gridSize;
        this.blocks = gridSize * gridSize + 2;
        this.grid = new WeightedQuickUnionUF(this.blocks);
        this.sites = new boolean[this.blocks];
        this.connectTopBottom();
    }

    private void connectTopBottom() {
        int topIndex = this.getTopIndex();
        int bottomIndex = this.getBottomIndex();
        for (int col = 1; col <= this.gridSize; col++) {
            this.grid.union(topIndex, this.getIndex(1, col));
            this.grid.union(bottomIndex, this.getIndex(this.gridSize, col));
        }
    }

    public void open(int row, int col) { // open site (row, col) if it is not open already
        this.validateRowCol(row, col);

        if (this.isOpen(row, col)) {
            return;
        }

        int index = this.getIndex(row, col);
        this.sites[index] = true;
        this.openSitesSize++;

        if (row > 1 && this.isOpen(row - 1, col)) { // up
            this.grid.union(index, this.getIndex(row - 1, col));
        }

        if (col > 1 && this.isOpen(row, col - 1)) { // right
            this.grid.union(index, this.getIndex(row, col - 1));
        }

        if (row < this.gridSize && this.isOpen(row + 1, col)) { // down
            this.grid.union(index, this.getIndex(row + 1, col));
        }

        if (col < this.gridSize && this.isOpen(row, col + 1)) { // right
            this.grid.union(index, this.getIndex(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        this.validateRowCol(row, col);
        return this.sites[this.getIndex(row, col)];
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        this.validateRowCol(row, col);
        return this.isOpen(row, col) && this.connected(this.getTopIndex(), this.getIndex(row, col));
    }

    public int numberOfOpenSites() { // number of open sites
        return this.openSitesSize;
    }

    public boolean percolates() { // does the system percolate?
        if (this.gridSize == 1 && !this.isOpen(1, 1)) {
            return false;
        }
        
        return this.connected(this.getTopIndex(), this.getBottomIndex());
    }

    private int getIndex(int row, int col) {
        this.validateRowCol(row, col);
        return this.gridSize * (row - 1) + (col - 1);
    }

    private int getTopIndex() {
        return this.blocks - 2;
    }

    private int getBottomIndex() {
        return this.blocks - 1;
    }

    private boolean connected(int p, int q) {
        return this.grid.connected(p, q);
    }

    private void validateRowCol(int row, int col) {
        if (row < 1 || row > this.gridSize) {
            throw new IllegalArgumentException("Invalid row : " + row);
        }

        if (col < 1 || col > this.gridSize) {
            throw new IllegalArgumentException("Invalid col : " + col);
        }
    }

    public static void main(String[] args) { // test client (optional)
    }
}
