package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm {

    private Stack<AState> stack;

    /**
     * Constructs a DepthFirstSearch algorithm instance.
     * This class uses a stack to explore nodes in a depth-first manner.
     */
    public DepthFirstSearch() {
        this.stack = new Stack<>();
        this.visitedStates = 0;
        this.name = "DepthFirstSearch";
        this.visited = new HashMap<>();
    }

    /**
     * Solves the given searchable domain using the Depth-First Search (DFS) algorithm
     * @param domain the searchable domain
     * @return the solution to the domain, or null if no solution is found
     * @throws IllegalArgumentException if the domain is null
     */
    @Override
    public Solution solve(ISearchable domain) {
        if (domain == null)
            throw new IllegalArgumentException("Invalid Input");

        stack.push(domain.getStartState());
        AState state;
        ArrayList<AState> possibleStates;

        while (!stack.isEmpty()) {
            state = stack.pop();
            if (state.getObject().toString().equals(domain.getGoalState().getObject().toString())) {
                return getSolution(state, domain);
            }
            visited.put(state.getHashCode(), state);
            visitedStates++;
            possibleStates = domain.getAllPossibleStates(state);
            checkPossibleStates(possibleStates, visited, stack, null);
        }
        return null; // returns null if no solution
    }
}
