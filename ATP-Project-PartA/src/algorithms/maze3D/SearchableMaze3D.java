package algorithms.maze3D;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;


import java.util.ArrayList;


public class SearchableMaze3D implements ISearchable {
    private Maze3D maze;
    protected Maze3DState startState;
    protected Maze3DState goalState;

    public SearchableMaze3D(Maze3D maze) {
        this.maze = maze;
        Position3D startPosition = maze.getStartPosition();
        Position3D goalPosition = maze.getGoalPosition();
        this.startState = new Maze3DState(startPosition.getRowIndex(), startPosition.getColumnIndex(),startPosition.getDepthIndex());
        this.goalState = new Maze3DState(goalPosition.getRowIndex(), goalPosition.getColumnIndex(),startPosition.getDepthIndex());
    }

    @Override
    public Maze3DState getStartState() {
        return new Maze3DState(startState.getPosition().getRowIndex(), startState.getPosition().getColumnIndex(),startState.getPosition().getDepthIndex());
    }

    @Override
    public Maze3DState getGoalState() {
        return new Maze3DState(goalState.getPosition().getRowIndex(), goalState.getPosition().getColumnIndex(), goalState.getPosition().getDepthIndex());
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state){
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

    private boolean isValidMove(int depth, int row, int col, int[][][] mazeArray) {
        if (depth < 0 || depth >= mazeArray.length  || row < 0 || row >= mazeArray[0].length || col < 0 || col >= mazeArray[0][0].length) {
            return false;
        }
        return mazeArray[depth][row][col] == 0;
    }
}