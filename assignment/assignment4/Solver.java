import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private class Node implements Comparable<Node> {

        private final Board board;
        private final Node previous;
        private final int moves;

        public Node(Board board, Node previous, int moves) {
            this.board = board;
            this.previous = previous;
            this.moves = moves;
        }

        @Override
        public int compareTo(Node other) {
            return (this.board.manhattan() + this.moves) - (other.board.manhattan() + other.moves);
        }
    }

    private final MinPQ<Node> minPQ = new MinPQ<>();
    private final MinPQ<Node> minPQTwin = new MinPQ<>();
    private boolean isSolvable = false;
    private int moves = -1;
    private List<Board> solution = null;

    public Solver(Board initial) { // find a solution to the initial board (using the A* algorithm)
        if (initial == null) {
            throw new IllegalArgumentException("Can not Solver constructor with null");
        }

        // root
        this.minPQ.insert(new Node(initial, null, 0));
        this.minPQTwin.insert(new Node(initial.twin(), null, 0));

        this.solve();
    }

    private void solve() {
        while (true) {
            Node temp = this.minPQ.delMin();

            if (temp.board.isGoal()) {
                this.isSolvable = true;
                this.moves = temp.moves;
                this.solution = new ArrayList<>();

                while (temp != null) {
                    this.solution.add(0, temp.board);
                    temp = temp.previous;
                }

                break;
            }

            for (Board neighbor : temp.board.neighbors()) {
                if (temp.previous != null && neighbor.equals(temp.previous.board)) {
                    continue;
                }
                this.minPQ.insert(new Node(neighbor, temp, temp.moves + 1));
            }

            temp = this.minPQTwin.delMin();

            if (temp.board.isGoal()) {
                this.isSolvable = false;
                this.moves = -1;
                this.solution = null;
                break;
            }

            for (Board neighbor : temp.board.neighbors()) {
                if (temp.previous != null && neighbor.equals(temp.previous.board)) {
                    continue;
                }
                this.minPQTwin.insert(new Node(neighbor, temp, temp.moves + 1));
            }
        }
    }

    public boolean isSolvable() { // is the initial board solvable?
        return this.isSolvable;
    }

    public int moves() { // min number of moves to solve initial board; -1 if unsolvable
        return this.moves;
    }

    public Iterable<Board> solution() { // sequence of boards in a shortest solution; null if unsolvable
        return this.solution;
    }

    public static void main(String[] args) { // solve a slider puzzle (given below)
        // create initial board from file
        In in = new In(args[0]);
//        In in = new In("/8puzzle/puzzle04.txt");
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
