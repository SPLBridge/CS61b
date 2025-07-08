package hw4.puzzle;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Board implements WorldState {
    private int[][] board;

    public Board(int[][] tiles) {
        int N = tiles.length;
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i + ", " + j);
        }
        return board[i][j];
    }

    public int size() {
        return board.length;
    }

    private int[] numToPosition(int num) {
        num--;
        int[] rtn = new int[2];
        rtn[1] = num % size();
        rtn[0] = num / size();
        return rtn;
    }

    private int positionToInt(int x, int y) {
        return x * size() + y + 1; // +1 because tiles are 1-indexed
    }

    public int hamming() {
        int rtn = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                int tile = tileAt(i, j);
                if (tile != positionToInt(i, j)) {
                    rtn++;
                }
            }
        }
        return rtn - 1;
    }

    public int manhattan() {
        int rtn = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) == 0) {
                    continue; // Skip the empty tile
                }
                int[] position = numToPosition(tileAt(i, j));
                rtn += abs(position[0] - i) + abs(position[1] - j);
            }
        }
        return rtn;
    }

    public boolean equals(Object y) {
        if (this == y) {
            return true;
        } else if (y == null || getClass() != y.getClass()) {
            return false;
        }
        Board other = (Board) y;
        if (size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) != other.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                hash = 31 * hash + tileAt(i, j);
            }
        }
        return hash;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public Iterable<WorldState> neighbors() {
        int N = size();
        int[] zeroPosition = new int[2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == 0) {
                    zeroPosition[0] = i;
                    zeroPosition[1] = j;
                    break;
                }
            }
        }

        // Possible moves: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<WorldState> neighbors = new ArrayList<>();

        for (int[] dir : directions) {
            int newX = zeroPosition[0] + dir[0];
            int newY = zeroPosition[1] + dir[1];
            if (newX >= 0 && newX < N && newY >= 0 && newY < N) {
                int[][] newTiles = new int[N][N];
                for (int i = 0; i < N; i++) {
                    System.arraycopy(board[i], 0, newTiles[i], 0, N);
                }
                // Swap the zero tile with the adjacent tile
                newTiles[zeroPosition[0]][zeroPosition[1]] = tileAt(newX, newY);
                newTiles[newX][newY] = 0;
                neighbors.add(new Board(newTiles));
            }
        }
        return neighbors;
    }

    /** Returns the string representation of the board. 
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
