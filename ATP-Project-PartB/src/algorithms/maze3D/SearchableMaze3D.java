package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {

    Maze3D maze;
    Maze3DState startState;
    Maze3DState goalState;

    /**
     * Constructs a SearchableMaze3D based on the given 3D maze
     * @param maze the 3D maze that this searchable maze is based on
     * @throws IllegalArgumentException if the maze is null
     */
    public SearchableMaze3D(Maze3D maze) {
        if (maze == null)
            throw new IllegalArgumentException("Invalid Input");
        this.maze = maze;
        this.startState = new Maze3DState(maze.getStartPosition(), null, 0);
        this.goalState = new Maze3DState(maze.getGoalPosition(), null, 0);
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
     * Returns a list of all possible successors for the given state
     * @param state the current state
     * @return a list of possible successor states
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        ArrayList<AState> possibleStates = new ArrayList<>();
        Position3D ms = (Position3D) state.getObject();

        if (maze.checkUp(ms))
            possibleStates.add(new Maze3DState(new Position3D(ms.getDepthIndex(), ms.getRowIndex() - 1, ms.getColumnIndex()), state, 10));
        if (maze.checkRight(ms))
            possibleStates.add(new Maze3DState(new Position3D(ms.getDepthIndex(), ms.getRowIndex(), ms.getColumnIndex() + 1), state, 10));
        if (maze.checkDown(ms))
            possibleStates.add(new Maze3DState(new Position3D(ms.getDepthIndex(), ms.getRowIndex() + 1, ms.getColumnIndex()), state, 10));
        if (maze.checkLeft(ms))
            possibleStates.add(new Maze3DState(new Position3D(ms.getDepthIndex(), ms.getRowIndex(), ms.getColumnIndex() - 1), state, 10));
        if (maze.checkTop(ms))
            possibleStates.add(new Maze3DState(new Position3D(ms.getDepthIndex() + 1, ms.getRowIndex(), ms.getColumnIndex()), state, 10));
        if (maze.checkBottom(ms))
            possibleStates.add(new Maze3DState(new Position3D(ms.getDepthIndex() - 1, ms.getRowIndex(), ms.getColumnIndex()), state, 10));

        return possibleStates;
    }
}
