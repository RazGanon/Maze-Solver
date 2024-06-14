package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;


// Define the SearchableMaze class
public class SearchableMaze implements ISearchable {
    protected Maze maze;
    protected MazeState startState;
    protected MazeState goalState;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
        Position startPosition = maze.getStart();
        Position goalPosition = maze.getEnd();
        if (startPosition != null && goalPosition != null) {
            this.startState = new MazeState(startPosition.getRowIndex(), startPosition.getColumnIndex());
            this.goalState = new MazeState(goalPosition.getRowIndex(), goalPosition.getColumnIndex());
        }
    }

    @Override
    public MazeState getStartState() {
        return startState;
    }

    @Override
    public MazeState getGoalState() {
        return goalState;
    }

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
    }}