package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private final ArrayList<AState> solutionPath;

    public Solution() {
        this.solutionPath = new ArrayList<>();
    }

    /**
     * Adds a state to the solution path.
     * @param state The state to be added to the path.
     */
    public void addStateToSolutionPath(AState state) {
        if (state != null) {
            solutionPath.add(state);
        } else {
            System.out.println("Attempted to add a null state to the solution path.");
        }
    }

    /**
     * Retrieves the solution path.
     * If the solution path is empty, prints a message and returns an empty list.
     * This method ensures that a null list is never returned.
     * @return An ArrayList of AState, never null.
     */
    public ArrayList<AState> getSolutionPath() {
        if (solutionPath.isEmpty()) {
            return null;
        }
        return new ArrayList<>(solutionPath); // Returns a copy of the solution path
    }
}
