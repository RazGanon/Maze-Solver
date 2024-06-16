package algorithms.search;

import java.util.ArrayList;

/**
 * Represents a solution in the form of a path found by a search algorithm
 * This class stores a sequence of states that form a path from the start to the goal state
 * as discovered by a search algorithm.
 */
public class Solution {
    private final ArrayList<AState> solutionPath;

    /**
     * Constructs an empty Solution.
     * Initializes an empty list to hold the states comprising the solution path.
     */
    public Solution() {
        this.solutionPath = new ArrayList<>();
    }

    /**
     * Adds a state to the solution path if the state is not null
     * This method ensures that only valid states are added to the solution path
     * If the state is null, a message is printed to indicate the attempt to add an invalid state
     * @param state The state to be added to the path; should not be null
     */
    public void addStateToSolutionPath(AState state) {
        if (state != null) {
            solutionPath.add(state);
        } else {
            System.out.println("Attempted to add a null state to the solution path.");
        }
    }

    /**
     * Retrieves the solution path
     * If the solution path is empty, it returns an empty list to ensure that a null value is never returned
     * This method returns a copy of the solution path to prevent external modifications to the internal path list
     * @return An ArrayList of AState representing the solution path; ensures that the list is never null
     */
    public ArrayList<AState> getSolutionPath() {
        if (solutionPath.isEmpty()) {
            System.out.println("The solution path is currently empty.");
            return new ArrayList<>();
        }
        return new ArrayList<>(solutionPath); // Returns a copy of the solution path to ensure encapsulation.
    }
}