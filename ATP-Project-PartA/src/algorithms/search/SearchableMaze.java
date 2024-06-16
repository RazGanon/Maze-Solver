package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;

/**
 * Adapts a Maze object to the ISearchable interface to enable search algorithms
 * to operate on maze structures. This class encapsulates the maze along with its
 * start and goal states, facilitating the exploration of paths within the maze.
 */
public class SearchableMaze implements ISearchable {
    protected Maze maze;
    protected MazeState startState;
    protected MazeState goalState;

    /**
     * Construct a SearchableMaze from a given Maze object (Object Adapter)
     * Initializes start and goal states based on the start and end positions provided by the Maze
     * @param maze The Maze object that this SearchableMaze will encapsulate
     */
    public SearchableMaze(Maze maze) {
        this.maze = maze;
        Position startPosition = maze.getStart();
        Position goalPosition = maze.getEnd();
        if (startPosition != null && goalPosition != null) {
            this.startState = new MazeState(startPosition.getRowIndex(), startPosition.getColumnIndex());
            this.goalState = new MazeState(goalPosition.getRowIndex(), goalPosition.getColumnIndex());
        }
    }

    /**
     * Returns the start state of the maze as a MazeState
     * @return The start state of the maze
     */
    @Override
    public MazeState getStartState() {
        return startState;
    }

    /**
     * Returns the goal state of the maze as a MazeState
     * @return The goal state of the maze
     */
    @Override
    public MazeState getGoalState() {
        return goalState;
    }

    /**
     * Retrieves all possible states that can be reached from a given state within the maze
     * This method checks for walkable adjacent cells (up, down, left, right) from the given state
     * @param s The current state from which to find possible subsequent states
     * @return An ArrayList of AState containing all possible states reachable from the given state
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        MazeState mazeState = (MazeState) s;
        int row = mazeState.getRow();
        int column = mazeState.getColumn();
        int[][] mazeArray = maze.getMaze();
        ArrayList<AState> possibleStates = new ArrayList<>();

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newRow = row + dx[i];
            int newColumn = column + dy[i];
            if (newRow >= 0 && newRow < mazeArray.length && newColumn >= 0 && newColumn < mazeArray[0].length && mazeArray[newRow][newColumn] == 0) {
                possibleStates.add(new MazeState(newRow, newColumn));
            }
        }

        return possibleStates;
    }
}