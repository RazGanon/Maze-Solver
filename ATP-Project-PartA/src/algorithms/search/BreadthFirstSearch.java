package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<AState> queue = new LinkedList<>();

    @Override
    public Solution solve(ISearchable searchable) {
        Set<AState> visited = new HashSet<>();
        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();

        if (startState != null) {
            queue.add(startState);
            visited.add(startState);
        } else {
            return null;
        }

        while (!queue.isEmpty()) {
            AState currentState = queue.poll();
            numberOfNodesEvaluated++;

            if (currentState.equals(goalState)) {
                return backtrackToStart(currentState);
            }

            List<AState> neighbors = searchable.getAllPossibleStates(currentState);
            for (AState neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    neighbor.setParentState(currentState);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return new Solution(); // Return an empty solution if no path is found
    }

    private Solution backtrackToStart(AState goalState) {
        Solution solution = new Solution();
        AState current = goalState;
        while (current != null) {
            solution.addStateToSolutionPath(current);
            current = current.getParentState();
        }
        Collections.reverse(solution.getSolutionPath()); // Ensure the path is from start to goal
        return solution;
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
