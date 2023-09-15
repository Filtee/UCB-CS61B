package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Solver {

    class SearchNode implements Comparable<SearchNode>{
        private WorldState worldState;
        private int moves;
        private SearchNode previous;

        private SearchNode(WorldState worldState, int moves, SearchNode previous) {
            this.worldState = worldState;
            this.moves = moves;
            this.previous = previous;
        }

        @Override
        public int compareTo(SearchNode o) {
            int thisTotal = this.worldState.estimatedDistanceToGoal() + this.moves;
            int otherTotal = o.worldState.estimatedDistanceToGoal() + o.moves;
            return thisTotal - otherTotal;
        }
    }

    private MinPQ<SearchNode> priorityQueue;

    private SearchNode goalNode;

    List<WorldState> worldStates;

    public Solver(WorldState initial) {
        /* Create a priority queue of search nodes. */
        priorityQueue = new MinPQ<>();
        /* Insert the initial search node into the priority queue. */
        SearchNode initialNode = new SearchNode(initial, 0, null);
        priorityQueue.insert(initialNode);

        proceedAlgorithm();
    }

    private void proceedAlgorithm() {
        SearchNode nodeRemoved = priorityQueue.delMin();

        /* If the node removed is the goal, return. */
        if (nodeRemoved.worldState.isGoal()) {
            goalNode = nodeRemoved;
            return;
        }

        /* Get the iterator of the neighbors of the newly removed search node.
        * For each neighbor of X’s world state, create a new search node and insert it. */
        for (WorldState eachState : nodeRemoved.worldState.neighbors()) {
            if (nodeRemoved.previous == null || !eachState.equals(nodeRemoved.previous.worldState)) {
                SearchNode eachNode = new SearchNode(eachState, nodeRemoved.moves + 1, nodeRemoved);
                priorityQueue.insert(eachNode);
            }
        }
        /* Repeat the whole process. */
        proceedAlgorithm();
    }

    public int moves() {
        return goalNode.moves;
    }

    /* Add the whole track back into a list. */
    private void solutionHelper(SearchNode searchNode) {
        if (searchNode.previous == null) {
            worldStates.add(searchNode.worldState);
        } else {
            solutionHelper(searchNode.previous);
            worldStates.add(searchNode.worldState);
        }
    }

    public Iterable<WorldState> solution() {
        worldStates = new ArrayList<>();
        solutionHelper(goalNode);

        return worldStates;
    }
}
