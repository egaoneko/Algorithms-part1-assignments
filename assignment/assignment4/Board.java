import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int[][] blocks;
    private final int n;

    public Board(int[][] blocks) { // (where blocks[i][j] = block in row i, column j)
        if (blocks == null) {
            throw new IllegalArgumentException("Can not Board constructor with null");
        }

        this.n = blocks.length;
        this.blocks = this.deepCopy(blocks);
    }

    private int[][] deepCopy(int[][] bs) {
        int[][] copy = new int[this.n][this.n];

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                copy[i][j] = bs[i][j];
            }
        }
        return copy;
    }

    public int dimension() { // board dimension n
        return this.n;
    }

    public int hamming() { // number of blocks out of place
        int hamming = 0;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                int expected = i * n + j + 1;
                int actual = this.blocks[i][j];

                if (actual == 0) {
                    continue;
                }

                if (actual == expected) {
                    continue;
                }
                hamming += 1;
            }
        }
        return hamming;
    }

    public int manhattan() { // sum of Manhattan distances between blocks and goal
        int manhattan = 0;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                int value = this.blocks[i][j];

                if (value == 0) {
                    continue;
                }

                int row = (value - 1) / n;
                int col = value - (n * row) - 1;
                manhattan += Math.abs(i - row);
                manhattan += Math.abs(j - col);
            }
        }
        return manhattan;
    }

    public boolean isGoal() { // is this board the goal board?
        return this.hamming() == 0;
    }

    public Board twin() { // a board that is obtained by exchanging any pair of blocks
        Board twin = new Board(this.deepCopy(this.blocks));

        if (twin.blocks[0][0] != 0 && twin.blocks[0][1] != 0) twin.swapBlock(0, 0);
        else twin.swapBlock(1, 1);
        return twin;
    }

    private void swapBlock(int r1, int r2) {
        int temp = this.blocks[r1][0];
        this.blocks[r1][0] = this.blocks[r2][1];
        this.blocks[r2][1] = temp;
    }

    public boolean equals(Object y) { // does this board equal y?
        if (y == this) return true;
        if (y == null || y.getClass() != this.getClass()) return false;
        if (n != ((Board) y).n) return false;

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (this.blocks[i][j] != ((Board) y).blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() { // all neighboring boards
        List<Board> list = new ArrayList<>();

        int[][] bs = this.deepCopy(this.blocks);
        int row = -1;
        int col = -1;

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                int value = bs[i][j];
                if (value != 0) {
                    continue;
                }
                row = i;
                col = j;
            }
        }

        if (row > 0) {
            exch(bs, row, col, row - 1, col);
            list.add(new Board(bs));
            exch(bs, row, col, row - 1, col);
        }

        if (col > 0) {
            exch(bs, row, col, row, col - 1);
            list.add(new Board(bs));
            exch(bs, row, col, row, col - 1);
        }

        if (col < n - 1) {
            exch(bs, row, col, row, col + 1);
            list.add(new Board(bs));
            exch(bs, row, col, row, col + 1);
        }

        if (row < n - 1) {
            exch(bs, row, col, row + 1, col);
            list.add(new Board(bs));
            exch(bs, row, col, row + 1, col);
        }

        return list;
    }

    private void exch(int[][] bs, int r1, int c1, int r2, int c2) {
        int temp = bs[r1][c1];
        bs[r1][c1] = bs[r2][c2];
        bs[r2][c2] = temp;
    }

    public String toString() { // string representation of this board (in the output format specified below)
        StringBuilder str = new StringBuilder(this.dimension() + "\n");

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                str.append(" ");
                str.append(this.blocks[i][j]);
                str.append(" ");
            }
            str.append("\n");
        }

        return str.toString();
    }

    public static void main(String[] args) { // unit tests (not graded)
    }
}
