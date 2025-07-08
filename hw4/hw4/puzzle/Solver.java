package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Iterator;

public class Solver {
    private MinPQ<Node> minPQ;
    private Node solution;

    public Solver(WorldState initial) {
        minPQ = new MinPQ<>(new Comparator());
        Node initialNode = new Node(initial, 0, null, 0);
        minPQ.insert(initialNode);
        while (true) {
            Node node = minPQ.delMin();
            if (node.worldState().isGoal()) {
                solution = node;
                break;
            }
            Iterable<WorldState> neighbors = node.worldState().neighbors();
            for (WorldState neighbor : neighbors) {
                if (node.previous() == null || !neighbor.equals(node.previous().worldState())) {
                    Node newNode = new Node(neighbor, node.movesMade + 1, node,
                            neighbor.estimatedDistanceToGoal());
                    minPQ.insert(newNode);
                }
            }
        }
    }

    public int moves() {
        return solution.movesMade();
    }

    private class SolverIterator implements Iterator<WorldState> {
        private WorldState[] states;
        private int pointer;

        public SolverIterator() {
            states = new WorldState[moves() + 1];
            pointer = moves();
            Node currentNode = solution;
            while (currentNode != null) {
                states[pointer--] = currentNode.worldState();
                currentNode = currentNode.previous();
            }
            pointer = 0;
        }

        @Override
        public boolean hasNext() {
            return pointer != states.length;
        }

        @Override
        public WorldState next() {
            return states[pointer++];
        }
    }

    private class Solution implements Iterable<WorldState> {

        @Override
        public Iterator<WorldState> iterator() {
            return new SolverIterator();
        }
    }

    public Iterable<WorldState> solution() {
        return new Solution();
    }

    private class Node {
        private WorldState worldState;
        private int movesMade;
        private Node previous;
        private int movesToMake;

        Node(WorldState worldState, int movesMade, Node previous, int movesToMake) {
            this.worldState = worldState;
            this.movesMade = movesMade;
            this.previous = previous;
            this.movesToMake = movesToMake;
        }

        WorldState worldState() {
            return worldState;
        }

        int movesMade() {
            return movesMade;
        }

        Node previous() {
            return previous;
        }

        int movesToMake() {
            return movesToMake;
        }
    }

    private class Comparator implements java.util.Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return (o1.movesToMake() + o1.movesMade()) - (o2.movesToMake() + o2.movesMade());
        }
    }
}
