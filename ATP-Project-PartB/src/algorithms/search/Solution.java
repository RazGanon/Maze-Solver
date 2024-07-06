package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
    private ArrayList<AState> path;

    /**
     * Constructs a Solution with the specified path
     * @param path the path of the solution
     * @throws IllegalArgumentException if the path is null
     */
    public Solution(ArrayList<AState> path) {
        if (path == null)
            throw new IllegalArgumentException("Invalid Input");
        this.path = path;
    }

    /**
     * Returns the path of the solution
     * @return the path of the solution
     */
    public ArrayList<AState> getSolutionPath() {
        return path;
    }

    /**
     * Returns a string representation of the solution
     * @return the string representation of the solution
     */
    @Override
    public String toString() {
        return "Solution: " + path;
    }
}
