package algorithms.search;

import java.util.*;


/**
 * Implements the depth-first search (DFS) algorithm to explore and solve search problems
 * DFS uses a stack to manage the nodes during the search, diving deep into one branch before backtracking
 */
public class DepthFirstSearch extends ASearchingAlgorithm {

    /**
     * Solves the search problem using a depth-first search approach. This method explores as far as possible
     * along each branch before backtracking
     * @param searchable The search space as an ISearchable object, providing start and goal states and possible states
     * @return A Solution object representing the path from start to goal. Returns null if start state is not defined
     */
    @Override
    public Solution solve(ISearchable searchable) {
        Stack<AState> stack = new Stack<>();
        Set<AState> visited = new HashSet<>();
        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();

        if (startState == null) {
            return null; // Returns null if no start state is defined
        }

        stack.add(startState);
        visited.add(startState);

        while (!stack.isEmpty()) {
            AState currentState = stack.pop();
            numberOfNodesEvaluated++;

            if (currentState.equals(goalState)) {
                return backtrack(currentState);
            }

            List<AState> neighbors = searchable.getAllPossibleStates(currentState);
            for (AState neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    neighbor.setParentState(currentState);
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return new Solution(); // Return an empty solution if no path is found
    }

    /**
     * Retrieves the name of the algorithm
     * @return A string "DepthFirstSearch" representing the name of this search algorithm
     */
    @Override
    public String getName() {
        return "DepthFirstSearch";
    }
}