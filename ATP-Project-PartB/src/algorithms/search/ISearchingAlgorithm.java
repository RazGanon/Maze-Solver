package algorithms.search;

/**
 * Interface defining the required methods for search algorithms
 * This interface ensures that all search algorithms provide a common structure
 * for solving search problems, retrieving the algorithm's name, and reporting the
 * number of nodes evaluated during the search process.
 */
public interface ISearchingAlgorithm {

    /**
     * Solves the search problem for the given searchable space
     * This method executes the search algorithm to find a path from the start state
     * to the goal state within the provided searchable space.
     * @param searchable The search space containing the states and their connections
     * @return A Solution object representing the path from the start to the goal state,
     * or an empty solution if no path is found.
     */
    Solution solve(ISearchable searchable);

    /**
     * Retrieves the name of the search algorithm
     * This method allows identification of the algorithm, useful for logging, analysis,
     * and user interface representation
     * @return A String representing the name of the search algorithm.
     */
    String getName();

    /**
     * Returns the number of nodes that the algorithm evaluated during the search
     * This count can be used to evaluate the performance and efficiency of the algorithm
     * @return The number of nodes evaluated by the algorithm.
     */
    int getNumberOfNodesEvaluated();
}