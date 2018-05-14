package com.coursera;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PercolationTest {
    private static final int N = 5;
    private static final int BLOCK_SIZE = N * N + 2;
    private Percolation percolation;

    @Before
    public void setup() {
        percolation = new Percolation(N);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPercolation_InvalidGridSize_ExceptionThrown() {
        new Percolation(0);
    }

    @Test
    public void testGetIndex() {
        assertThat(this.percolation.getIndex(1, 1), is(0));
        assertThat(this.percolation.getIndex(N, N), is(BLOCK_SIZE - 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetIndex_UnderRowMin_ExceptionThrown() {
        this.percolation.getIndex(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetIndex_UnderColMin_ExceptionThrown() {
        this.percolation.getIndex(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetIndex_UnderRowMax_ExceptionThrown() {
        this.percolation.getIndex(N + 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetIndex_UnderColMax_ExceptionThrown() {
        this.percolation.getIndex(1, N + 1);
    }

    @Test
    public void testGetTopIndex() {
        assertThat(this.percolation.getTopIndex(), is(BLOCK_SIZE - 2));
    }

    @Test
    public void testGetBottomIndex() {
        assertThat(this.percolation.getBottomIndex(), is(BLOCK_SIZE - 1));
    }

    @Test
    public void testIsOpen() {
        assertThat(this.percolation.isOpen(1, 1), is(false));
        this.percolation.sites[this.percolation.getIndex(1, 1)] = true;
        assertThat(this.percolation.isOpen(1, 1), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpen_UnderRowMin_ExceptionThrown() {
        this.percolation.isOpen(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpen_UnderColMin_ExceptionThrown() {
        this.percolation.isOpen(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpen_UnderRowMax_ExceptionThrown() {
        this.percolation.isOpen(N + 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpen_UnderColMax_ExceptionThrown() {
        this.percolation.isOpen(1, N + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOpen_UnderRowMin_ExceptionThrown() {
        this.percolation.open(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOpen_UnderColMin_ExceptionThrown() {
        this.percolation.open(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOpen_UnderRowMax_ExceptionThrown() {
        this.percolation.open(N + 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOpen_UnderColMax_ExceptionThrown() {
        this.percolation.open(1, N + 1);
    }
}
