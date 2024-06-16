package algorithms.search;

import java.util.ArrayList;

/**
 * An interface that defines the necessary methods to make a search space usable by search algorithms.
 * This interface ensures that any search space (e.g., mazes, graphs) can be explored by a variety of search algorithms.
 */
public interface ISearchable {

    /**
     * Retrieves the start state of the search space.
     * This method provides the initial state from which the search algorithm begins.
     *
     * @return AState representing the starting point of the search.
     */
    AState getStartState();

    /**
     * Retrieves the goal state of the search space.
     * This method provides the target state that the search algorithm aims to reach.
     *
     * @return AState representing the goal or endpoint of the search.
     */
    AState getGoalState();

    /**
     * Provides all possible states that can be reached from the given state.
     * This method is crucial for determining the search algorithm's path as it provides the neighboring states.
     *
     * @param state The state from which to find all possible subsequent states.
     * @return An ArrayList of AState objects representing all possible states reachable directly from the given state.
     */
    ArrayList<AState> getAllPossibleStates(AState state);
}
