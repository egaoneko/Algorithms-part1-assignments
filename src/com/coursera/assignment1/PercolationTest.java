package com.coursera.assignment1;

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
        this.percolation = new Percolation(N);
    }

    @Test
    public void testPercolation() {
        // Are top and bottom connected?
        for (int col = 1; col <= N; col++) {
            // top
            assertThat(
                    this.percolation.grid.connected(
                            this.percolation.getTopIndex(),
                            this.percolation.getIndex(1, col)
                    ),
                    is(true)
            );
            // bottom
            assertThat(
                    this.percolation.grid.connected(
                            this.percolation.getBottomIndex(),
                            this.percolation.getIndex(N, col)
                    ),
                    is(true)
            );
        }
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

    @Test()
    public void testOpen() {
        assertThat(this.percolation.isOpen(1, 1), is(false));
        this.percolation.open(1, 1);
        assertThat(this.percolation.isOpen(1, 1), is(true));
    }

    @Test()
    public void testOpen_ConnectedOpenedSites_Middle() {
        this.percolation.open(1, 2);
        this.percolation.open(2, 1);
        this.percolation.open(2, 3);
        this.percolation.open(3, 2);
        this.percolation.open(2, 2);

        assertThat(
                this.percolation.grid.connected(
                        this.percolation.getIndex(2, 2),
                        this.percolation.getIndex(1, 2)
                ),
                is(true)
        );

        assertThat(
                this.percolation.grid.connected(
                        this.percolation.getIndex(2, 2),
                        this.percolation.getIndex(2, 1)
                ),
                is(true)
        );

        assertThat(
                this.percolation.grid.connected(
                        this.percolation.getIndex(2, 2),
                        this.percolation.getIndex(2, 3)
                ),
                is(true)
        );

        assertThat(
                this.percolation.grid.connected(
                        this.percolation.getIndex(2, 2),
                        this.percolation.getIndex(3, 2)
                ),
                is(true)
        );
    }

    @Test()
    public void testOpen_ConnectedOpenedSites_LeftTop() {
        this.percolation.open(1, 2);
        this.percolation.open(2, 1);
        this.percolation.open(1, 1);

        assertThat(
                this.percolation.grid.connected(
                        this.percolation.getIndex(1, 1),
                        this.percolation.getIndex(1, 2)
                ),
                is(true)
        );

        assertThat(
                this.percolation.grid.connected(
                        this.percolation.getIndex(1, 1),
                        this.percolation.getIndex(2, 1)
                ),
                is(true)
        );
    }

    @Test()
    public void testOpen_ConnectedOpenedSites_RightTop() {
        this.percolation.open(1, N - 1);
        this.percolation.open(2, N);
        this.percolation.open(1, N);

        assertThat(
                this.percolation.grid.connected(
                        this.percolation.getIndex(1, N),
                        this.percolation.getIndex(1, N - 1)
                ),
                is(true)
        );

        assertThat(
                this.percolation.grid.connected(
                        this.percolation.getIndex(1, N),
                        this.percolation.getIndex(2, N)
                ),
                is(true)
        );
    }

    @Test()
    public void testOpen_ConnectedOpenedSites_LeftBottom() {
        this.percolation.open(N, 2);
        this.percolation.open(N - 1, 1);
        this.percolation.open(N, 1);

        assertThat(
                this.percolation.grid.connected(
                        this.percolation.getIndex(N, 1),
                        this.percolation.getIndex(N, 2)
                ),
                is(true)
        );

        assertThat(
                this.percolation.grid.connected(
                        this.percolation.getIndex(N, 1),
                        this.percolation.getIndex(N - 1, 1)
                ),
                is(true)
        );
    }

    @Test()
    public void testOpen_ConnectedOpenedSites_RightBottom() {
        this.percolation.open(N, N - 1);
        this.percolation.open(N - 1, N);
        this.percolation.open(N, N);

        assertThat(
                this.percolation.grid.connected(
                        this.percolation.getIndex(N, N),
                        this.percolation.getIndex(N, N - 1)
                ),
                is(true)
        );

        assertThat(
                this.percolation.grid.connected(
                        this.percolation.getIndex(N, N),
                        this.percolation.getIndex(N - 1, N)
                ),
                is(true)
        );
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

    @Test
    public void testIsFull() {
        assertThat(this.percolation.isFull(1, 1), is(false));
        this.percolation.open(1, 1);
        assertThat(this.percolation.isFull(1, 1), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFull_UnderRowMin_ExceptionThrown() {
        this.percolation.isFull(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFull_UnderColMin_ExceptionThrown() {
        this.percolation.isFull(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFull_UnderRowMax_ExceptionThrown() {
        this.percolation.isFull(N + 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFull_UnderColMax_ExceptionThrown() {
        this.percolation.isFull(1, N + 1);
    }

    @Test
    public void testNumberOfOpenSites() {
        assertThat(this.percolation.numberOfOpenSites(), is(0));

        this.percolation.open(1, 1);
        this.percolation.open(2, 1);
        this.percolation.open(3, 1);
        assertThat(this.percolation.numberOfOpenSites(), is(3));

        this.percolation.open(3, 1);
        assertThat(this.percolation.numberOfOpenSites(), is(3));
    }

    @Test
    public void testPercolates() {
        assertThat(this.percolation.percolates(), is(false));

        for (int row = 1; row <= N; row++) {
            this.percolation.open(row, 1);
        }

        assertThat(this.percolation.percolates(), is(true));
    }
}
