package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<MazeState> queue;

    public BreadthFirstSearch() {
        this.queue = new LinkedList<>();
    }

    @Override
    public Solution solve(ISearchable searchable) {
        Set<MazeState> visited = new HashSet<>();
        MazeState startState = searchable.getStartState();
        MazeState goalState = searchable.getGoalState();

        queue.add(startState);
        visited.add(startState);

        while (!queue.isEmpty()) {
            MazeState currentState = queue.poll();
            numberOfNodesEvaluated++;

            if (currentState.equals(goalState)) {
                return backtrack(currentState);
            }

            for (MazeState neighbor : searchable.getAllPossibleStates(currentState)) {
                if (!visited.contains(neighbor)) {
                    neighbor.setParentState(currentState);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return null;
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }}
