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
        Set<MazeState> visited = new HashSet<>();
        MazeState startState = searchable.getStartState();
        MazeState goalState = searchable.getGoalState();
        this.m = searchable;

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
        return "BestFirstSearch";
    }

    private int calculateHeuristic(MazeState state) {
        if (state instanceof MazeState) {
            MazeState mazeState =  state;
            MazeState goalState = this.getGoalState();
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

    private MazeState getGoalState() {
        return m.getGoalState();
    }}