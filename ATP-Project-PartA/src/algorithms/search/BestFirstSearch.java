package algorithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch {
    private ISearchable m ;

    public BestFirstSearch() {
        this.queue = new PriorityQueue<>(Comparator.comparingInt(this::calculateHeuristic));
        this.m = null;
    }

    @Override
    public Solution solve(ISearchable searchable) {
        Set<AState> visited = new HashSet<>();
        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();
        this.m = searchable;

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
                return backtrack(currentState);
            }

            for (AState neighbor : searchable.getAllPossibleStates(currentState)) {
                if (!visited.contains(neighbor)) {
                    neighbor.setParentState(currentState);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return new Solution();
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

    private int calculateHeuristic(AState state) {
        if (state instanceof MazeState) {
            MazeState mazeState = (MazeState) state;
            MazeState goalState = (MazeState) this.getGoalState();
            if (goalState instanceof MazeState) {
                MazeState goalMazeState = goalState;
                int rowDifference = Math.abs(mazeState.getRow() - goalMazeState.getRow());
                int columnDifference = Math.abs(mazeState.getColumn() - goalMazeState.getColumn());
                // Assuming diagonal moves cost 15 and normal moves cost 10
                return (rowDifference + columnDifference) * 10 + Math.min(rowDifference, columnDifference) * 5;
            }
        }
        return 0;
    }

    private AState getGoalState() {
        return m.getGoalState();
    }}