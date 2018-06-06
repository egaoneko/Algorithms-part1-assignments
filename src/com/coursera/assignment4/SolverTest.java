package com.coursera.assignment4;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SolverTest {

    private Solver solvableSolver;
    private Solver unSolvableSolver;

    @Before
    public void setup() {
        this.solvableSolver = new Solver(new Board(new int[][] {
            {0, 1, 3},
            {4, 2, 5},
            {7, 8, 6}
        }));
        this.unSolvableSolver = new Solver(new Board(new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {8, 7, 0}
        }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSolver_InvalidNull_ExceptionThrown() {
        new Solver(null);
    }

    @Test
    public void testIsSolvable_Solvable() {
        assertThat(this.solvableSolver.isSolvable(), is(true));
    }

    @Test
    public void testMoves_Solvable() {
        assertThat(this.solvableSolver.moves(), is(4));
    }

    @Test
    public void testSolution_Solvable() {
        List<Board> solution = (List<Board>) this.solvableSolver.solution();
        assertNotNull(solution);
        assertThat(solution.size(), is(5));
        assertEquals(
                solution.get(0),
                new Board(new int[][]{
                        {0, 1, 3},
                        {4, 2, 5},
                        {7, 8, 6}
                })
        );
        assertEquals(
                solution.get(1),
                new Board(new int[][]{
                        {1, 0, 3},
                        {4, 2, 5},
                        {7, 8, 6}
                })
        );
        assertEquals(
                solution.get(2),
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 0, 5},
                        {7, 8, 6}
                })
        );
        assertEquals(
                solution.get(3),
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 0},
                        {7, 8, 6}
                })
        );
        assertEquals(
                solution.get(4),
                new Board(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 0}
                })
        );
    }

    @Test
    public void testIsSolvable_UnSolvable() {
        assertThat(this.unSolvableSolver.isSolvable(), is(false));
    }

    @Test
    public void testMoves_UnSolvable() {
        assertThat(this.unSolvableSolver.moves(), is(-1));
    }

    @Test
    public void testSolution_UnSolvable() {
        List<Board> solution = (List<Board>) this.unSolvableSolver.solution();
        assertNull(solution);
    }

}
