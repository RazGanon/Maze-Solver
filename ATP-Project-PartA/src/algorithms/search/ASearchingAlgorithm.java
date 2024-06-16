package algorithms.search;

import java.util.Collections;

/**
 * Abstract base class for search algorithms. Provides common functionality
 * required by all search algorithms, including counting nodes and backtracking to find the solution path
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int numberOfNodesEvaluated;

    /**
     * Default constructor that initializes the count of evaluated nodes to zero.
     */
    public ASearchingAlgorithm() {
        this.numberOfNodesEvaluated = 0;
    }

    /**
     * Retrieves the number of nodes that have been evaluated by the search algorithm
     * @return The number of nodes evaluated
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }

    /**
     * Constructs the solution path from the goal state to the start state by following parent links
     * @param goalState The final state reached by the search algorithm that represents the goal
     * @return A solution consisting of the path from the start state to the goal state
     */
    protected Solution backtrack(AState goalState) {
        Solution solution = new Solution();
        AState currentState = goalState;
        while (currentState != null) {
            solution.addStateToSolutionPath(currentState);
            currentState = currentState.getParentState();
        }
        Collections.reverse(solution.getSolutionPath());
        return solution;
    }

    /**
     * Checks if the solution is non-null
     * @param solution The solution to check
     * @return true if the solution is not null, false otherwise
     */
    private boolean checkSolution(Solution solution) {
        return solution != null;
    }

}