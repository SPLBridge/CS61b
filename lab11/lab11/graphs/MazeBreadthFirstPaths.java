package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int sourceX;
    private int sourceY;
    private int targetX;
    private int targetY;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        int source = maze.xyTo1D(sourceX, sourceY);
        distTo[source] = 0;
        edgeTo[source] = source; // Start from the source itself
        marked[source] = true;
        announce();
        // Initialize a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            int currentX = maze.toX(current);
            int currentY = maze.toY(current);

            // Check if we reached the target
            if (currentX == targetX && currentY == targetY) {
                return; // Exit if target is found
            }

            // Explore neighbors
            for (int neighbor : maze.adj(current)) {
                if (!marked[neighbor]) {
                    marked[neighbor] = true;
                    distTo[neighbor] = distTo[current] + 1;
                    edgeTo[neighbor] = current;
                    queue.add(neighbor);
                    announce(); // Notify observers of the change
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

