package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm {

    @Override
    public Solution solve(ISearchable searchable) {
        Stack<MazeState> stack = new Stack<>();
        Set<MazeState> visited = new HashSet<>();
        MazeState startState = searchable.getStartState();
        MazeState goalState = searchable.getGoalState();

        stack.push(startState);
        visited.add(startState);

        while (!stack.isEmpty()) {
            MazeState currentState = stack.pop();
            numberOfNodesEvaluated++;

            if (currentState.equals(goalState)) {
                return backtrack(currentState);
            }

            for (MazeState neighbor : searchable.getAllPossibleStates(currentState)) {
                if (!visited.contains(neighbor)) {
                    neighbor.setParentState(currentState);
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return null;
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }}