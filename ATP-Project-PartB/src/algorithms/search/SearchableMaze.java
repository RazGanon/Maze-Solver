package algorithms.search;

import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    Maze maze;
    MazeState startState;
    MazeState goalState;

    /**
     * Constructs a SearchableMaze based on the given Maze
     * @param maze the maze that this searchable maze is based on
     * @throws IllegalArgumentException if the maze is null
     */
    public SearchableMaze(Maze maze) {
        if (maze == null)
            throw new IllegalArgumentException("Invalid Input");
        this.maze = maze;
        this.startState = new MazeState(maze.getStartPosition(), null, 0);
        this.goalState = new MazeState(maze.getGoalPosition(), null, 0);
    }

    /**
     * Returns the starting state of the maze
     * @return the starting state
     */
    @Override
    public AState getStartState() {
        return startState;
    }

    /**
     * Returns the goal state of the maze
     * @return the goal state
     */
    @Override
    public AState getGoalState() {
        return goalState;
    }

    /**
     * Returns a list of all possible states for the given state
     * @param state the current state
     * @return a list of possible successor states
     * @throws IllegalArgumentException if the state is null
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        if (state == null)
            throw new IllegalArgumentException("Invalid Input");
        ArrayList<AState> possibleMoves = new ArrayList<>();
        Position ms = (Position) state.getObject();

        if (maze.checkUp(ms))
            possibleMoves.add(new MazeState(new Position(ms.getRowIndex() - 1, ms.getColumnIndex()), state, 10));
        if (maze.checkUpRight(ms))
            possibleMoves.add(new MazeState(new Position(ms.getRowIndex() - 1, ms.getColumnIndex() + 1), state, 15));
        if (maze.checkRight(ms))
            possibleMoves.add(new MazeState(new Position(ms.getRowIndex(), ms.getColumnIndex() + 1), state, 10));
        if (maze.checkDownRight(ms))
            possibleMoves.add(new MazeState(new Position(ms.getRowIndex() + 1, ms.getColumnIndex() + 1), state, 15));
        if (maze.checkDown(ms))
            possibleMoves.add(new MazeState(new Position(ms.getRowIndex() + 1, ms.getColumnIndex()), state, 10));
        if (maze.checkDownLeft(ms))
            possibleMoves.add(new MazeState(new Position(ms.getRowIndex() + 1, ms.getColumnIndex() - 1), state, 15));
        if (maze.checkLeft(ms))
            possibleMoves.add(new MazeState(new Position(ms.getRowIndex(), ms.getColumnIndex() - 1), state, 10));
        if (maze.checkUpLeft(ms))
            possibleMoves.add(new MazeState(new Position(ms.getRowIndex() - 1, ms.getColumnIndex() - 1), state, 15));

        return possibleMoves;
    }
}
