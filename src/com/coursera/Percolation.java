package com.coursera;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int gridSize;
    private int blocks;
    WeightedQuickUnionUF grid;
    boolean[] sites;

    Percolation(int gridSize) { // create gridSize-by-gridSize grid, with all sites blocked
        if (gridSize < 1) {
            throw new IllegalArgumentException("Invalid grid size : " + gridSize);
        }

        this.gridSize = gridSize;
        this.blocks = gridSize * gridSize + 2;
        this.grid = new WeightedQuickUnionUF(this.blocks);
        this.sites = new boolean[this.blocks];
    }

    public void open(int row, int col) { // open site (row, col) if it is not open already
        this.validateRowCol(row, col);
        // Todo: make open method using isOpen method
    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        this.validateRowCol(row, col);
        return this.sites[this.getIndex(row, col)];
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        return false;
    }

    public int numberOfOpenSites() { // number of open sites
        return 0;
    }

    public boolean percolates() { // does the system percolate?
        return false;
    }

    int getIndex(int row, int col) {
        this.validateRowCol(row, col);
        return this.gridSize * (row - 1) + (col - 1);
    }

    int getTopIndex() {
        return this.blocks - 2;
    }

    int getBottomIndex() {
        return this.blocks - 1;
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
