package algorithms.search;

import java.util.*;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected int visitedStates;
    protected String name;
    protected HashMap<String, AState> visited;

    /**
     * Returns the number of nodes evaluated during the search
     * @return the number of nodes evaluated
     */
    public int getNumberOfNodesEvaluated() {
        return visitedStates;
    }

    /**
     * Returns the name of the searching algorithm
     * @return the name of the algorithm
     */
    public String getName() {
        return name;
    }

    /**
     * Adds possible states to the stack or queue for further exploration.
     * @param possibleStates the list of possible states to be added
     * @param visited the HashMap of visited states
     * @param stack the stack of states (can be null)
     * @param queue the queue of states (can be null)
     * @throws IllegalArgumentException if any of the parameters are null or invalid
     */
    public void checkPossibleStates(ArrayList<AState> possibleStates, HashMap<String, AState> visited, Stack<AState> stack, Queue<AState> queue) {
        if (possibleStates == null || visited == null || (stack == null && queue == null))
            throw new IllegalArgumentException("Invalid Input");
        if (!possibleStates.isEmpty()) {
            for (AState s : possibleStates) {
                if (!visited.containsKey(s.getHashCode())) {
                    s.setCost(s.getCost() + s.cameFrom.getCost());
                    if (stack != null)
                        stack.add(s);
                    if (queue != null)
                        queue.add(s);
                    visited.put(s.getHashCode(), s);
                }
            }
        }
    }

    /**
     * Constructs a solution from the final state reached during the search
     * @param fState the final state in the search
     * @param domain the searchable domain
     * @return the solution constructed from the final state
     * @throws IllegalArgumentException if the final state or domain is null
     */
    protected Solution getSolution(AState fState, ISearchable domain) {
        if (fState == null || domain == null)
            throw new IllegalArgumentException("Invalid Input");
        ArrayList<AState> solution = new ArrayList<>();
        while (!domain.getStartState().equals(fState)) {
            solution.add(fState);
            fState = fState.getCameFrom();
        }
        solution.add(fState);
        Collections.reverse(solution);
        return new Solution(solution);
    }
}
