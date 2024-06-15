package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm {

    @Override
    public Solution solve(ISearchable searchable) {
        Stack<AState> stack = new Stack<>();
        Set<AState> visited = new HashSet<>();
        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();

        if (startState != null) {
            stack.add(startState);
            visited.add(startState);
        } else {
            return null;
        }

        while (!stack.isEmpty()) {
            AState currentState = stack.pop();
            numberOfNodesEvaluated++;

            if (currentState.equals(goalState)) {
                return backtrack(currentState);
            }

            for (AState neighbor : searchable.getAllPossibleStates(currentState)) {
                if (!visited.contains(neighbor)) {
                    neighbor.setParentState(currentState);
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return new Solution();
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }}