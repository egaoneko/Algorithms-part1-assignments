package com.coursera.assignment4;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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

    @Test
    public void testManhattan_4() {
        assertThat(new Board(new int[][]{
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        }).manhattan(), is(4));
    }

    @Test
    public void testManhattan_3() {
        assertThat(new Board(new int[][]{
                {1, 0, 3},
                {4, 2, 5},
                {7, 8, 6}
        }).manhattan(), is(3));
    }

    @Test
    public void testManhattan_5() {
        assertThat(new Board(new int[][]{
                {4, 1, 3},
                {0, 2, 5},
                {7, 8, 6}
        }).manhattan(), is(5));
    }

    @Test
    public void testIsGoal_true() {
        assertTrue(new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        }).isGoal());
    }

    @Test
    public void testIsGoal_false() {
        assertFalse(new Board(new int[][]{
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        }).isGoal());
    }

    @Test
    public void testIsEqual_Equal() {
        assertEquals(
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0}
                }),
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0}
                })
        );
    }

    @Test
    public void testIsEqual_NotEqual() {
        assertNotEquals(
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0}
                }),
                new Board(new int[][]{
                        {0, 2, 3},
                        {4, 5, 6},
                        {7, 8, 1}
                })
        );
    }

    @Test
    public void testTwin_WithoutFirstZero() {
        Board actual = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        }).twin();

        Board expected = new Board(new int[][]{
                {2, 1, 3},
                {4, 5, 6},
                {7, 8, 0}
        });
        assertEquals(
                actual,
                expected
        );
    }

    @Test
    public void testTwin_WithFirstZero() {
        Board actual = new Board(new int[][]{
                {0, 2, 3},
                {4, 5, 6},
                {7, 8, 1}
        }).twin();

        Board expected = new Board(new int[][]{
                {0, 2, 3},
                {5, 4, 6},
                {7, 8, 1}
        });
        assertEquals(
                actual,
                expected
        );
    }

    @Test
    public void testNeighbors() {
        List<Board> neighbors = (List<Board>) new Board(new int[][]{
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 5}
        }).neighbors();

        assertThat(neighbors.size(), is(4));
        assertEquals(
                neighbors.get(0),
                new Board(new int[][]{
                        {1, 0, 3},
                        {4, 2, 6},
                        {7, 8, 5}
                })
        );

        assertEquals(
                neighbors.get(1),
                new Board(new int[][]{
                        {1, 2, 3},
                        {0, 4, 6},
                        {7, 8, 5}
                })
        );

        assertEquals(
                neighbors.get(2),
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 6, 0},
                        {7, 8, 5}
                })
        );

        assertEquals(
                neighbors.get(3),
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 8, 6},
                        {7, 0, 5}
                })
        );
    }

    @Test
    public void testNeighbors_LeftTop() {
        List<Board> neighbors = (List<Board>) new Board(new int[][]{
                {0, 2, 3},
                {4, 5, 6},
                {7, 8, 1}
        }).neighbors();

        assertThat(neighbors.size(), is(2));
        assertEquals(
                neighbors.get(0),
                new Board(new int[][]{
                        {2, 0, 3},
                        {4, 5, 6},
                        {7, 8, 1}
                })
        );

        assertEquals(
                neighbors.get(1),
                new Board(new int[][]{
                        {4, 2, 3},
                        {0, 5, 6},
                        {7, 8, 1}
                })
        );
    }

    @Test
    public void testNeighbors_RightTop() {
        List<Board> neighbors = (List<Board>) new Board(new int[][]{
                {1, 2, 0},
                {4, 5, 6},
                {7, 8, 3}
        }).neighbors();

        assertThat(neighbors.size(), is(2));
        assertEquals(
                neighbors.get(0),
                new Board(new int[][]{
                        {1, 0, 2},
                        {4, 5, 6},
                        {7, 8, 3}
                })
        );

        assertEquals(
                neighbors.get(1),
                new Board(new int[][]{
                        {1, 2, 6},
                        {4, 5, 0},
                        {7, 8, 3}
                })
        );
    }

    @Test
    public void testNeighbors_LeftBottom() {
        List<Board> neighbors = (List<Board>) new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {0, 8, 7}
        }).neighbors();

        assertThat(neighbors.size(), is(2));
        assertEquals(
                neighbors.get(0),
                new Board(new int[][]{
                        {1, 2, 3},
                        {0, 5, 6},
                        {4, 8, 7}
                })
        );

        assertEquals(
                neighbors.get(1),
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {8, 0, 7}
                })
        );
    }

    @Test
    public void testNeighbors_RightBottom() {
        List<Board> neighbors = (List<Board>) new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        }).neighbors();

        assertThat(neighbors.size(), is(2));
        assertEquals(
                neighbors.get(0),
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 0},
                        {7, 8, 6}
                })
        );

        assertEquals(
                neighbors.get(1),
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 0, 8}
                })
        );
    }

    @Test
    public void testToString() {
        String expected = "3\n" +
                " 0  1  3 \n" +
                " 4  2  5 \n" +
                " 7  8  6 \n";
        String actual = new Board(new int[][]{
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        }).toString();

        assertThat(actual, is(expected));
    }
}
