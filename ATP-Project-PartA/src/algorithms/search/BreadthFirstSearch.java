package algorithms.search;

import java.util.*;

/**
 * Implements the breadth-first search algorithm for finding the shortest path in a search space
 * This class extends {@link ASearchingAlgorithm} to utilize a queue for managing the frontier nodes
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<AState> queue = new LinkedList<>();

    /**
     * Solves the search problem using the breadth-first search strategy
     * @param searchable The search space as an ISearchable object, providing start and goal states and possible states
     * @return A Solution object representing the path from start to goal, or null if the start state is undefined
     */
    @Override
    public Solution solve(ISearchable searchable) {
        Set<AState> visited = new HashSet<>();
        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();

        if (startState == null) {
            return null; // Ensures there is a start state, returns null if not provided
        }

        queue.add(startState);
        visited.add(startState);

        while (!queue.isEmpty()) {
            AState currentState = queue.poll();
            numberOfNodesEvaluated++;

            if (currentState.equals(goalState)) {
                return backtrackToStart(currentState);
            }

            List<AState> neighbors = searchable.getAllPossibleStates(currentState);
            for (AState neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    neighbor.setParentState(currentState);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return new Solution(); // Return an empty solution if no path is found
    }

    /**
     * Backtracks from the goal state to the start state using the parent links in the states,
     * constructing the solution path
     * @param goalState The final state reached by the search which represents the goal
     * @return A solution consisting of the path from start to goal
     */
    private Solution backtrackToStart(AState goalState) {
        Solution solution = new Solution();
        AState current = goalState;
        while (current != null) {
            solution.addStateToSolutionPath(current);
            current = current.getParentState();
        }
        Collections.reverse(solution.getSolutionPath()); // Ensure the path is from start to goal
        return solution;
    }

    /**
     * Retrieves the name of the algorithm
     * @return A string "BreadthFirstSearch" representing the name of this search algorithm
     */
    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}