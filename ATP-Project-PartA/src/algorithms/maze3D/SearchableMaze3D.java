package algorithms.maze3D;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import java.util.ArrayList;

/**
 * Represents a searchable 3D maze, implementing the ISearchable interface
 */
public class SearchableMaze3D implements ISearchable {
    private Maze3D maze;
    protected Maze3DState startState;
    protected Maze3DState goalState;

    /**
     * Constructs a SearchableMaze3D with the specified 3D maze (Object Adapter)
     * @param maze the 3D maze to be made searchable
     */
    public SearchableMaze3D(Maze3D maze) {
        this.maze = maze;
        Position3D startPosition = maze.getStartPosition();
        Position3D goalPosition = maze.getGoalPosition();
        this.startState = new Maze3DState(startPosition.getRowIndex(), startPosition.getColumnIndex(), startPosition.getDepthIndex());
        this.goalState = new Maze3DState(goalPosition.getRowIndex(), goalPosition.getColumnIndex(), goalPosition.getDepthIndex());
    }

    /**
     * Returns the starting state of the maze
     * @return the starting state
     */
    @Override
    public Maze3DState getStartState() {
        return new Maze3DState(startState.getPosition().getRowIndex(), startState.getPosition().getColumnIndex(), startState.getPosition().getDepthIndex());
    }

    /**
     * Returns the goal state of the maze
     * @return the goal state
     */
    @Override
    public Maze3DState getGoalState() {
        return new Maze3DState(goalState.getPosition().getRowIndex(), goalState.getPosition().getColumnIndex(), goalState.getPosition().getDepthIndex());
    }

    /**
     * Returns all possible states that can be reached from the given state
     * @param state the current state
     * @return a list of possible states
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        Maze3DState state3D = (Maze3DState) state;
        ArrayList<AState> possibleStates = new ArrayList<>();
        int[][][] mazeArray = maze.getMap();
        Position3D position = state3D.getPosition();
        int depth = position.getDepthIndex();
        int row = position.getRowIndex();
        int col = position.getColumnIndex();

        // Define movements in each axis direction: forward/backward in depth, up/down in rows, left/right in columns
        int[] dDepth = {-1, 1, 0, 0, 0, 0};
        int[] dRow = {0, 0, -1, 1, 0, 0};
        int[] dCol = {0, 0, 0, 0, -1, 1};

        for (int i = 0; i < dDepth.length; i++) {
            int newDepth = depth + dDepth[i];
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];
            if (isValidMove(newRow, newCol, newDepth, mazeArray)) {
                possibleStates.add(new Maze3DState(newRow, newCol, newDepth));
            }
        }
        return possibleStates;
    }

    /**
     * Checks if the move to the specified position is valid within the maze.
     * @param depth the depth index of the position to check
     * @param row the row index of the position to check
     * @param col the column index of the position to check
     * @param mazeArray the 3-dimensional array representing the maze structure
     * @return true if the move is valid, false otherwise
     */
    private boolean isValidMove(int depth, int row, int col, int[][][] mazeArray) {
        if (depth < 0 || depth >= mazeArray.length || row < 0 || row >= mazeArray[0].length || col < 0 || col >= mazeArray[0][0].length) {
            return false;
        }
        return mazeArray[depth][row][col] == 0;
    }
}
