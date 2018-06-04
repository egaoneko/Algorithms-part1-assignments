package com.coursera.assignment4;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

    private Board board;
    private static int N = 3;
    private static int[][] BLOCKS = new int[][]{
            {0, 1, 3},
            {4, 2, 5},
            {7, 8, 6}
    };

    @Before
    public void setup() {
        this.board = new Board(BLOCKS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBoard_InvalidNull_ExceptionThrown() {
        new Board(null);
    }

    @Test
    public void testDimension() {
        assertThat(this.board.dimension(), is(3));
    }

    @Test
    public void testHamming_4() {
        assertThat(new Board(new int[][]{
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        }).hamming(), is(4));
    }

    @Test
    public void testHamming_0() {
        assertThat(new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        }).hamming(), is(0));
    }

    @Test
    public void testHamming_1() {
        assertThat(new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8}
        }).hamming(), is(1));
    }
}
