package algorithms.search;

import java.util.HashMap;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    /**
     * Constructs a BestFirstSearch algorithm instance.
     * This class inherits from BreadthFirstSearch but uses a priority queue
     * to prioritize nodes based on their cost.
     */
    public BestFirstSearch() {
        this.queue = new PriorityQueue<>((o1, o2) -> (int) (o1.getCost() - o2.getCost()));
        this.visitedStates = 0;
        this.name = "BestFirstSearch";
        this.visited = new HashMap<>();
    }
}
