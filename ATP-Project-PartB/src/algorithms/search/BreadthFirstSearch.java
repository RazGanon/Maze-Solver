package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    protected Queue<AState> queue;

    /**
     * Constructs a BreadthFirstSearch algorithm instance.
     * This class uses a queue to explore nodes in a breadth-first manner.
     */
    public BreadthFirstSearch() {
        this.queue = new LinkedList<>();
        this.visitedStates = 0;
        this.name = "BreadthFirstSearch";
        this.visited = new HashMap<>();
    }

    /**
     * Solves the given searchable domain using the Breadth-First Search (BFS) algorithm
     * @param domain the searchable domain
     * @return the solution to the domain, or null if no solution is found
     * @throws IllegalArgumentException if the domain is null
     */
    @Override
    public Solution solve(ISearchable domain) {
        if (domain == null)
            throw new IllegalArgumentException("Invalid Input");

        AState state;
        visited.put(domain.getStartState().getHashCode(), domain.getStartState());
        this.queue.add(domain.getStartState());
        ArrayList<AState> possibleStates;

        while (!this.queue.isEmpty()) {
            state = this.queue.poll();
            visitedStates++;
            if (state.getObject().toString().equals(domain.getGoalState().getObject().toString())) {
                return getSolution(state, domain);
            }
            possibleStates = domain.getAllPossibleStates(state);
            checkPossibleStates(possibleStates, visited, null, queue);
        }
        return null; // returns null if no solution
    }
}
