package algorithms.search;

import java.util.*;

/**
 * A search algorithm that implements the best-first search strategy, using a heuristic
 * to prioritize states. It extends BreadthFirstSearch but modifies the queueing mechanism
 * to a priority queue guided by heuristic values.
 */
public class BestFirstSearch extends BreadthFirstSearch {
    private ISearchable m;

    /**
     * Constructs a BestFirstSearch algorithm instance with an empty priority queue.
     * The queue uses a comparator that is based on a heuristic calculation for sorting states.
     */
    public BestFirstSearch() {
        this.queue = new PriorityQueue<>(Comparator.comparingInt(this::calculateHeuristic));
        this.m = null;
    }

    /**
     * Solves the search problem using the best-first search strategy.
     *
     * @param searchable The search space as an ISearchable object, providing start and goal states and possible states.
     * @return A Solution object representing the path from start to goal, or null if the start state is undefined.
     */
    @Override
    public Solution solve(ISearchable searchable) {
        Set<AState> visited = new HashSet<>();
        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();
        this.m = searchable;

        if (startState == null) {
            return null; // Returns null if no start state is defined
        }

        queue.add(startState);
        visited.add(startState);

        while (!queue.isEmpty()) {
            AState currentState = queue.poll();
            numberOfNodesEvaluated++;

            if (currentState.equals(goalState)) {
                return backtrack(currentState);
            }

            for (AState neighbor : searchable.getAllPossibleStates(currentState)) {
                if (!visited.contains(neighbor)) {
                    neighbor.setParentState(currentState);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return new Solution(); // Returns an empty solution if no path is found
    }

    /**
     * Retrieves the name of the algorithm.
     *
     * @return A string "BestFirstSearch" representing the name of this search algorithm.
     */
    @Override
    public String getName() {
        return "BestFirstSearch";
    }

    /**
     * Calculates a heuristic value for a given state based on the estimated cost to reach the goal state.
     *
     * @param state The current state for which to calculate the heuristic.
     * @return An integer representing the heuristic estimate of the cost from the state to the goal.
     */
    private int calculateHeuristic(AState state) {
        if (state instanceof MazeState) {
            MazeState mazeState = (MazeState) state;
            MazeState goalState = (MazeState) this.getGoalState();
            if (goalState instanceof MazeState) {
                int rowDifference = Math.abs(mazeState.getRow() - goalState.getRow());
                int columnDifference = Math.abs(mazeState.getColumn() - goalState.getColumn());
                // Heuristic that assumes diagonal moves cost 15 and normal moves cost 10
                return (rowDifference + columnDifference) * 10 + Math.min(rowDifference, columnDifference) * 5;
            }
        }
        return 0; // Returns 0 if no suitable heuristic calculation can be applied
    }

    /**
     * Retrieves the goal state from the searchable space.
     *
     * @return The goal state as specified in the searchable object.
     */
    private AState getGoalState() {
        return m.getGoalState();
    }
}
